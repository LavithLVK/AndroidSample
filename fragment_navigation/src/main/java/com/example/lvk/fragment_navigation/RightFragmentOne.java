package com.example.lvk.fragment_navigation;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lavith.edam on 6/13/2017.
 */

public class RightFragmentOne extends Fragment {

    private static int count2=1;
    private static int count3=1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rightfragment1,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView txt=(TextView)getView().findViewById(R.id.right1);
        Bundle args=getArguments();
        txt.setText(args.getString("right1"));
        ImageView img2=(ImageView)getView().findViewById(R.id.r1image1);
        ImageView img3=(ImageView)getView().findViewById(R.id.r1image2);
//        URL url = null;
//        Bitmap bmp = null;
//        try {
//            url = new URL("https://maxcdn.icons8.com/Share/icon/Alphabet//2_circle_c1600.png");
//            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Bitmap bmp=BitmapFactory.decodeResource(getResources(),R.mipmap.two);
        img2.setImageBitmap(bmp);
//        img2.setImageURI(Uri.parse("https://maxcdn.icons8.com/Share/icon/Alphabet//2_circle_c1600.png"));
//        try {
//            url = new URL("https://image.flaticon.com/icons/svg/56/56587.svg");
//            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        bmp=BitmapFactory.decodeResource(getResources(),R.mipmap.three);
        img3.setImageBitmap(bmp);
//        img3.setImageURI(Uri.parse("https://image.flaticon.com/icons/svg/56/56587.svg"));
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).switchToSecondFragment("right",count2++);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).switchToThirdFragment("right",count3++);
            }
        });
    }
}
