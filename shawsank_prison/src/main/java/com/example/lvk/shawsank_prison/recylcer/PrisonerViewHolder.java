package com.example.lvk.shawsank_prison.recylcer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lvk.shawsank_prison.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lavith.edam on 6/29/2017.
 */

public class PrisonerViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView name;
    TextView id;
    public PrisonerViewHolder(View itemView) {
        super(itemView);
        imageView=(CircleImageView)itemView.findViewById(R.id.circle_image_item);
        name=(TextView)itemView.findViewById(R.id.prisoner_name_item);
        id=(TextView)itemView.findViewById(R.id.prisoner_id_item);
    }

}
