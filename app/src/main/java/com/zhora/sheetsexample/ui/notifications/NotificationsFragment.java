package com.zhora.sheetsexample.ui.notifications;

import android.os.Bundle;
import android.util.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.*;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.zhora.sheetsexample.*;
import com.zhora.sheetsexample.databinding.FragmentNotificationsBinding;
import com.zhora.sheetsexample.ui.blank.*;

import org.json.*;

import java.util.*;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        container.removeAllViews();

        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        ListView listView = root.findViewById(R.id.listViewChild);


        Bundle bundle = getArguments();

        JSONArray array = ReadJson.ReadThing(getActivity(), bundle.getString("CALIBER"));
        String[] pulkiNames = new String[array.length()];
        for (int i = 0; i <= array.length(); i++) {
                try {
                    pulkiNames[i] = array.getJSONObject(i).getString("Ammo Type");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, pulkiNames);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String itemName= listView.getItemAtPosition(position).toString();
            ReadJson.ReadThing(getActivity(), itemName);
            Log.v("нажатие", "я нажал на " + itemName);

            try {
                bundle.putBundle("JSONObj", jsonToBundle(array.getJSONObject(position)));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Fragment blank = new BlankFragment();
            blank.setArguments(bundle);
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, blank);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
        );



        return root;
    }

    public static Bundle jsonToBundle(JSONObject jsonObject) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator iter = jsonObject.keys();
        while(iter.hasNext()){
            String key = (String)iter.next();
            String value = jsonObject.getString(key);
            bundle.putString(key, value);
        }
        return bundle;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

