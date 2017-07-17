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
import com.example.lvk.shawsank_prison.Utils.ImagePicker;
import com.example.lvk.shawsank_prison.Utils.Utility;
import com.example.lvk.shawsank_prison.Utils.Validate;
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
    Validate validate=new Validate();
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
    ImagePicker imgPicker;


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
        imgPicker=new ImagePicker(imageView,this);
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
                    valid_String=validate.validateName(name);
                }
            }

        });
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
              if(!hasFocus){
                  valid_String=validate.validateEmail(email);
              }
            }
        });
        mobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {//TODO
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                   valid_String=validate.validateMobile(mobile);
                }
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dob.setText(validate.generateTextFromDate(year,++month,dayOfMonth));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CHOOSE_PHOTO_REQUESTCODE){
                image_Path = imgPicker.onSelectFromGalleryResult(data);
            }
            else if (requestCode == TAKE_PHOTO_REQUESTCODE){
                image_Path = imgPicker.onCaptureImageResult(data);
            }
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

    public void selectImage() {
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

    private void addPrisoner(View v){
        if(valid_String&&!name.getText().toString().isEmpty()&&!email.getText().toString().isEmpty()
                &&!mobile.getText().toString().isEmpty()&&!image_Path.isEmpty()&&!dob.getText().toString().isEmpty()
                &&!crime.getSelectedItem().toString().isEmpty()&&!sentenced.getSelectedItem().toString().isEmpty()){
            prisonerDBHeleper=new PrisonerDBHeleper(getApplicationContext(),2);
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
                finish();
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
