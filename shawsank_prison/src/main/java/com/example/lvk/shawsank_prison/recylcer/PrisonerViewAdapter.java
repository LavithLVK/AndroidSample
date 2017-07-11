package com.example.lvk.shawsank_prison.recylcer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lvk.shawsank_prison.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by lavith.edam on 6/29/2017.
 */

public class PrisonerViewAdapter extends RecyclerView.Adapter<PrisonerViewHolder> {
    private ArrayList<PrisonerModel> prisoners;


    @Override
    public PrisonerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PrisonerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_fragment_item,parent,false));
    }

    @Override
    public void onBindViewHolder(PrisonerViewHolder holder, int position) {
        final PrisonerModel prisoner = prisoners.get(position);
        holder.id.setText(String.valueOf(prisoner.getId()));
        holder.name.setText(prisoner.getName());
        File imgFile = new  File(prisoner.getPhotoPath());
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            holder.imageView.setImageBitmap(myBitmap);
        }
        else
        {
            holder.imageView.setImageResource(R.drawable.prisoner);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            PrisonerListFragment pl =new PrisonerListFragment();
            @Override
            public void onClick(View v) {
//                ((ViewPrisonerActivity)ctx).openPrisonerContent(prisoner);
            }
        });
    }

    @Override
    public int getItemCount() {
        return prisoners.size();
    }
}
