package com.example.lvk.recycler_view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by lavith.edam on 6/8/2017.
 */

public class LVKViewHolder extends RecyclerView.ViewHolder {
    public TextView first;
    public TextView second;
    public LVKViewHolder(View itemView) {
        super(itemView);
        first =(TextView) itemView.findViewById(R.id.first);
        second =(TextView) itemView.findViewById(R.id.second);
    }
}
