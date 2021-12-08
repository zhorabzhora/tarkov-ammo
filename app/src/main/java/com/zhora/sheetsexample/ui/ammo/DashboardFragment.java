package com.zhora.sheetsexample.ui.ammo;

import android.os.*;
import android.util.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.*;
import androidx.fragment.app.*;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.zhora.sheetsexample.*;
import com.zhora.sheetsexample.databinding.FragmentDashboardBinding;
import com.zhora.sheetsexample.ui.notifications.*;

import org.json.*;

public class DashboardFragment extends Fragment {

    private AmmoViewModel ammoViewModel;
    private FragmentDashboardBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        container.removeAllViews(); // костыль

        ammoViewModel = new ViewModelProvider(this).get(AmmoViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        String jsonString = "ammo.json";
        JSONObject jsonObject = null;
        JSONArray jsonArray = null;
        jsonArray = ReadJson.ReadThing(getActivity(), "12 Gauge Shot");


        String _12gauge = "12 Gauge Shot";
        String _12slugs = "12 Gauge Slugs";
        String _20gauge = "20 Gauge";
        String _23x75 = "23x75 mm";
        String _46x30 = "4.6x30 mm";
        String _9x18 = "9x18mm";
        String _762x25 = "7.62x25mm";
        String _9x19 = "9x19mm";
        String _45 = ".45";
        String _9x21 = "9x21mm";
        String _57x28 = "5.7x28 mm";
        String _9x39 = "9x39mm";
        String _366 = ".366";
        String _545 = "5.45x39 mm";
        String _556 = "5.56x45 mm";
        String _762x39 = "7.62x39 mm";
        String _300blk = "300 BLK";
        String _762x51 = "7.62x51 mm";
        String _762x54 = "7.62x54R";
        String _127x55 = "12.7x55 mm";
        String lapuaMagnum = ".338 Lapua Magnum";
        String mountedW = "Mounted Weapons";
        String others = "Other";

        final String[] pulkiNames = new String[] {
                _12gauge, _12slugs, _20gauge, _23x75, _46x30, _9x18, _762x25, _9x19, _45, _9x21, _57x28, _9x39,
                _366, _545, _556, _762x39, _300blk, _762x51, _762x54, _127x55, lapuaMagnum, mountedW, others
        };

        Log.v("негры", "меседж");

        ListView listView = root.findViewById(R.id.listview);


        Bundle bundle = new Bundle();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemName= listView.getItemAtPosition(position).toString();
                ReadJson.ReadThing(getActivity(), itemName);
                Log.v("нажатие", "я нажал на " + itemName);

                bundle.putString("CALIBER", itemName);

                Fragment notification = new NotificationsFragment();
                notification.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, notification);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }
        );





        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, pulkiNames);

        listView.setAdapter(adapter);

        final TextView textView = binding.textDashboard;
        String finalOthers = others;
        ammoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(finalOthers);
            }
        });



        return root;
    }



    private String JsonDataFromAsset(String filename){
        String json = null;

        return null;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}