package com.example.lvk.fragments1;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by lavith.edam on 6/12/2017.
 */

public class ContentFragment extends Fragment {
    TextView option;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("List onCreate : ","ContentFragment ");

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("List onViewCreated: ","ContentFragment ");

    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
//        args =getArguments();
//        option=(TextView) getView().findViewById(R.id.option);
//        if(args!=null){
//            option.setText(args.getString("data"));
//        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d("List onCreateView : ","ContentFragment ");
        View view=inflater.inflate(R.layout.contentfragment,container,false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("List onAttach : ","ContentFragment ");
    }

//    @NonNull
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("onActivityCreated: ","ContentFragment ");
//        option=(TextView)getView().findViewById(R.id.option);
        Bundle bundle=getArguments();
        if(bundle!=null){
            setText(bundle.getString("data"));
        }
    }

//    @Nullable
    private void setText(String data){
        TextView view=(TextView)getView().findViewById(R.id.option1);
        view.setText(data);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStart: ","ContentFragment ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("onStop: ","ContentFragment ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy: ","ContentFragment ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("onDetach: ","ContentFragment ");
    }

}
