package com.example.lvk.shawsank_prison.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lvk.shawsank_prison.R;
import com.example.lvk.shawsank_prison.Utils.Utility;
import com.example.lvk.shawsank_prison.database.PrisonerDBHeleper;
import com.example.lvk.shawsank_prison.recylcer.PrisonerModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddPrisonerActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{


    private static int TAKE_PHOTO_REQUESTCODE = 1116;
    private static int CHOOSE_PHOTO_REQUESTCODE = 1150;
    EditText name;
    EditText email;
    EditText mobile;
    Spinner crime;
    Spinner sentenced;
    EditText dob;
    Button btnBack;
    Button btnUpload;
    String image_Path;
    Button btnAddPrisoner;
    ImageView imageView;
    CircleImageView circleImageView;
    Boolean valid_String;
    PrisonerModel prisonerModel;
    PrisonerDBHeleper prisonerDBHeleper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prisoner);
        valid_String =false;
        Calendar c=Calendar.getInstance();
        name=(EditText)findViewById(R.id.edit_prisoner_name);
        email=(EditText)findViewById(R.id.edit_prisoner_email);
        mobile=(EditText)findViewById(R.id.edit_prisoner_mobile);
        dob=(EditText) findViewById(R.id.edit_prisoner_dob);
        crime=(Spinner) findViewById(R.id.edit_prisoner_crime);
        sentenced=(Spinner) findViewById(R.id.edit_prisoner_sentenced);
        btnUpload=(Button)findViewById(R.id.upload_photo);
        btnAddPrisoner=(Button)findViewById(R.id.add_prisoner_button);
        btnBack =(Button)findViewById(R.id.back_button);
        circleImageView=(CircleImageView)findViewById(R.id.calender_icon);
        imageView=(ImageView)findViewById(R.id.img_form);
        final DatePickerDialog datePickerDialog=new DatePickerDialog(this,this, c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnAddPrisoner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPrisoner(v);
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    int length=name.getText().length();
                    if(length==0){
                        name.setError( "Name is required!" );
                        valid_String =false;
                    }
                    else if(length==1){
                        name.setError( "Name Should contain more than one character." );
                        valid_String =false;
                    }
                    else
                    {
                        valid_String=true;
                    }
                }
            }

        });
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
              if(!hasFocus){
                  String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                  if(!email.getText().toString().trim().matches(emailPattern)){
                        email.setError("Enter valid email address");
                      valid_String=false;
                  }
              }
            }
        });
        mobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {//TODO
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String mobile_string=mobile.getText().toString();
                    int length=mobile_string.length();
                    int zero_count=0;
                    if(mobile_string.startsWith("0")){
                        zero_count=1;
                        for(int i=1;i<length;i++){
                            if(mobile_string.charAt(i)=='0'){
                                zero_count++;
                            }
                            else{
                                break;
                            }
                        }
                    }
                    if(length<10){
                        mobile.setError("mobile no. should contain 10 digits");
                        valid_String=false;
                    }
                    else if(length-zero_count<10||length-zero_count>10){
                        mobile.setError("Enter valid mobile no.");
                        valid_String=false;
                    }
                    else {
                        valid_String=true;
                    }
                }
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month++;
        String temp=(month<9)?("0"+month):(month+"");
        dob.setText(dayOfMonth+"/"+temp+"/"+year);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CHOOSE_PHOTO_REQUESTCODE)
                onSelectFromGalleryResult(data);
            else if (requestCode == TAKE_PHOTO_REQUESTCODE)
                onCaptureImageResult(data);
        }
    }

    private void galleryIntent(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),CHOOSE_PHOTO_REQUESTCODE);
    }

    private void cameraIntent(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKE_PHOTO_REQUESTCODE);
    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result= Utility.checkPermission(AddPrisonerActivity.this);
                if (items[item].equals("Take Photo")) {
                    if(result)
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    if(result)
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
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
            Toast.makeText(this,image_Path,Toast.LENGTH_SHORT).show();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setImageView(bm);
}

    private void onCaptureImageResult(Intent data) {
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
            Toast.makeText(this,image_Path,Toast.LENGTH_SHORT).show();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setImageView(thumbnail);
    }

    private void setImageView(Bitmap bitmap){
        if(bitmap!=null) {
            imageView.setImageBitmap(bitmap);
            imageView.setVisibility(View.VISIBLE);
        }
    }

    private void addPrisoner(View v){
        if(valid_String&&!name.getText().toString().isEmpty()&&!email.getText().toString().isEmpty()
                &&!mobile.getText().toString().isEmpty()&&!image_Path.isEmpty()&&!dob.getText().toString().isEmpty()
                &&!crime.getSelectedItem().toString().isEmpty()&&!sentenced.getSelectedItem().toString().isEmpty()){
            prisonerDBHeleper=new PrisonerDBHeleper(getApplicationContext(),1);
            prisonerModel=new PrisonerModel();
            prisonerModel.setName(name.getText().toString());
            prisonerModel.setEmail(email.getText().toString());
            prisonerModel.setMobile(mobile.getText().toString());
            prisonerModel.setDob(dob.getText().toString());
            prisonerModel.setPhotoPath(image_Path);
            prisonerModel.setCrime(crime.getSelectedItem().toString());
            prisonerModel.setSentenced(Boolean.parseBoolean(sentenced.getSelectedItem().toString()));
            try{
                prisonerDBHeleper.insertPrisoner(prisonerModel);
                Toast.makeText(this,"Record Added Successfully",Toast.LENGTH_LONG).show();
            }catch (Exception ex){
                ex.getStackTrace();
            }
        }
        else
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Enter valid details..").setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            Toast.makeText(getApplicationContext(),"Dekhle baba phirseee....",Toast.LENGTH_LONG).show();
        }
//        Toast.makeText(this,"Functionality development in progress",Toast.LENGTH_SHORT).show();
    }

}
