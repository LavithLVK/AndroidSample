package com.example.lvk.fragments1;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by lavith.edam on 6/9/2017.
 */

public class FragmentList extends Fragment {

    private String[] array ={"first option","second option","third option"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("List onCreate : ","FragmentList");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d("List onCreateView : ","FragmentList");
        View view = inflater.inflate(R.layout.list_items,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("List onViewCreated: ","FragmentList");
        ListView listView =(ListView)view.findViewById(R.id.listitems);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,array);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity)getActivity()).addFragment(array[position]);
                FragmentManager fm=getFragmentManager();

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("List onAttach : ","FragmentList");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("onActivityCreated: ","FragmentList");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStart: ","FragmentList");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("onStop: ","FragmentList");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy: ","FragmentList");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("onDetach: ","FragmentList");
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//    }
}
