package com.zhora.sheetsexample.ui.blank;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.zhora.sheetsexample.*;
import com.zhora.sheetsexample.databinding.*;

public class BlankFragment extends Fragment {

    private BlankViewModel mViewModel;
    private BlankFragmentBinding binding;

    public static BlankFragment newInstance() {
        return new BlankFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        container.removeAllViews();

        binding = BlankFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        Bundle bundle = getArguments();

        bundle.getBundle("JSONObj");
        bundle = bundle.getBundle("JSONObj");

        String[] cartridgeInfo = new String[bundle.size()];

        cartridgeInfo[0] = "Ammo Type - "+ bundle.getString("Ammo Type");
        cartridgeInfo[1] = "Damage - "+ bundle.getString("Damage");
        cartridgeInfo[2] = "Pen Value - "+ bundle.getString("Pen Value");
        cartridgeInfo[3] = "Armor Damage % - "+ bundle.getString("Armor Damage %");
        cartridgeInfo[4] = "Frag. Chance* - "+ bundle.getString("Frag. Chance*");
        cartridgeInfo[5] = "Class 1 - "+ bundle.getString("Class 1");
        cartridgeInfo[6] = "Class 2 - "+ bundle.getString("Class 2");
        cartridgeInfo[7] = "Class 3 - "+ bundle.getString("Class 3");
        cartridgeInfo[8] = "Class 4 - "+ bundle.getString("Class 4");
        cartridgeInfo[9] = "Class 5 - "+ bundle.getString("Class 5");
        cartridgeInfo[10] = "Class 6 - "+ bundle.getString("Class 6");


        ListView listView = root.findViewById(R.id.listViewChild123);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, cartridgeInfo);
        listView.setAdapter(adapter);



        return root;
    }

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BlankViewModel.class);
        // TODO: Use the ViewModel
    }*/

}