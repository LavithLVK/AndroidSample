package com.example.lvk.sqlite_sample.prisoner_recylcer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lvk.sqlite_sample.R;

/**
 * Created by lavith.edam on 6/22/2017.
 */

public class PrisonerViewHolder extends RecyclerView.ViewHolder{
    public TextView id;
    public TextView name;

    public PrisonerViewHolder(View itemView) {
        super(itemView);
        id=(TextView)itemView.findViewById(R.id.prisoner_id_item);
        name=(TextView)itemView.findViewById(R.id.prisoner_name_item);
    }



}
