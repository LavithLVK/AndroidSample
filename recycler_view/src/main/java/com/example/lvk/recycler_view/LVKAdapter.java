package com.example.lvk.recycler_view;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.view.LayoutInflater.*;

/**
 * Created by lavith.edam on 6/8/2017.
 */

public class LVKAdapter extends RecyclerView.Adapter<LVKViewHolder>{

    List<DataModel> models;
    static int count=0;

    LVKAdapter(ArrayList<DataModel> models){
        if (models == null) {
            throw new IllegalArgumentException(
                    "modelData must not be null");
        }
        this.models = models;
    }

    @Override
    public LVKViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_item,parent,false);
        return new LVKViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LVKViewHolder holder, int position) {
        DataModel model = models.get(position);
        holder.first.setText(model.getFirst());
        holder.second.setText(model.getSecond());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
