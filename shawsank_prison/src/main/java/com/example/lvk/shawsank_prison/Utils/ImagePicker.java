package com.example.lvk.shawsank_prison.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lvk.shawsank_prison.Activities.AddPrisonerActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lavith.edam on 7/15/2017.
 */

public class ImagePicker extends Activity{

    ImageView imageView;
    Context ctx;
    String image_Path;
    private static int TAKE_PHOTO_REQUESTCODE = 1116;
    private static int CHOOSE_PHOTO_REQUESTCODE = 1150;

    public ImagePicker(ImageView imageView,Context ctx){
        this.imageView=imageView;
        this.ctx=ctx;
    }

//    public void selectImage() {
//        final CharSequence[] items = { "Take Photo", "Choose from Library",
//                "Cancel" };
//        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
//        builder.setTitle("Add Photo!");
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int item) {
//                boolean result= Utility.checkPermission(ctx);
//                if (items[item].equals("Take Photo")) {
//                    if(result)
//                        cameraIntent();
//                } else if (items[item].equals("Choose from Library")) {
//                    if(result)
//                        galleryIntent();
//                } else if (items[item].equals("Cancel")) {
//                    dialog.dismiss();
//                }
//            }
//        });
//        builder.show();
//    }

    @SuppressWarnings("deprecation")
    public String onSelectFromGalleryResult(Intent data) {
        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(ctx.getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                "Shawsank_Prison/media");
        try {
            if(!destination.exists()){
                destination.mkdir();
            }
            destination = new File(Environment.getExternalStorageDirectory(),
                    "Shawsank_Prison/media/"+System.currentTimeMillis() + ".jpg");
            FileOutputStream fo;
            destination.createNewFile();
            image_Path=destination.getAbsolutePath();
            Toast.makeText(ctx,image_Path,Toast.LENGTH_SHORT).show();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setImageView(bm);
        return image_Path;
    }

    public String onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                "Shawsank_Prison/media");
        try {
            if(!destination.exists()){
                destination.mkdir();
            }
            destination = new File(Environment.getExternalStorageDirectory(),
                    "Shawsank_Prison/media/"+System.currentTimeMillis() + ".jpg");
            FileOutputStream fo;
            destination.createNewFile();
            image_Path=destination.getAbsolutePath();
//            Log.d("Image Path",image_Path);
            Toast.makeText(ctx,image_Path,Toast.LENGTH_SHORT).show();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setImageView(thumbnail);
        return image_Path;
    }

    private void setImageView(Bitmap bitmap){
        if(bitmap!=null) {
            imageView.setImageBitmap(bitmap);
            imageView.setVisibility(View.VISIBLE);
        }
    }

}
