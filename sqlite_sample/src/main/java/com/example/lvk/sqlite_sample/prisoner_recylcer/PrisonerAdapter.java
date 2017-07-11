package com.example.lvk.sqlite_sample.prisoner_recylcer;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lvk.sqlite_sample.Prisoner;
import com.example.lvk.sqlite_sample.R;
import com.example.lvk.sqlite_sample.ViewPrisonerActivity;

import java.util.List;

/**
 * Created by lavith.edam on 6/22/2017.
 */

public class PrisonerAdapter extends RecyclerView.Adapter<PrisonerViewHolder> {

    private List<Prisoner> prisoners;
    private Context ctx;


    protected PrisonerAdapter(List<Prisoner> prisoners, Context ctx){
        this.prisoners = prisoners;
        this.ctx = ctx;
    }

    @Override
    public PrisonerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PrisonerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_prisoner_item,parent,false));
    }

    @Override
    public void onBindViewHolder(PrisonerViewHolder holder,int position) {
        final Prisoner prisoner = prisoners.get(position);
        holder.id.setText(String.valueOf(prisoner.getId()));
        holder.name.setText(prisoner.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            PrisonerListFragment pl =new PrisonerListFragment();
            @Override
            public void onClick(View v) {
                ((ViewPrisonerActivity)ctx).openPrisonerContent(prisoner);
            }
        });
    }

    @Override
    public int getItemCount() {
        return prisoners.size();
    }
}
