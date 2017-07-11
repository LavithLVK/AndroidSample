package com.example.lvk.shawsank_prison;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lvk.shawsank_prison.Activities.AddPrisonerActivity;

/**
 * Created by lavith.edam on 6/28/2017.
 */

public class ManipualteFragment extends Fragment {

    Button btnAdd;
    Button btnDelete;
    Button btnUpdate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.manipulate_fragment,container,false);
        btnAdd=(Button)view.findViewById(R.id.add_button);
        btnUpdate =(Button)view.findViewById(R.id.update_button);
        btnDelete =(Button)view.findViewById(R.id.delete_button);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButtonClick(v);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButtonClick(v);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteButtonClick(v);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void addButtonClick(View view){
        startActivity(new Intent(getActivity(),AddPrisonerActivity.class));
    }

    public void updateButtonClick(View view){

    }

    public void deleteButtonClick(View view){

    }

}
