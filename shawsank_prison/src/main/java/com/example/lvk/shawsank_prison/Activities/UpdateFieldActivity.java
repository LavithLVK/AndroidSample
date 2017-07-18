package com.example.lvk.shawsank_prison.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.lvk.shawsank_prison.R;
import com.example.lvk.shawsank_prison.Utils.ImagePicker;
import com.example.lvk.shawsank_prison.Utils.Utility;
import com.example.lvk.shawsank_prison.Utils.Validate;
import com.example.lvk.shawsank_prison.database.PrisonerDBHeleper;
import com.example.lvk.shawsank_prison.recylcer.PrisonerModel;

import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateFieldActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private ArrayList<String> crimes=new ArrayList<String>();
    String [] crimeStrings={"Theft","Murder","Kidnap","Rape","Smuggling","Eve-teasing","Gangster","Driving on FootPath","Killing Deer","Getting Married","Arguing with girls"};
    private enum FieldType {
        fieldEnalbe,
        fieldUpdate
    }
    private Validate validate=new Validate();
    private Boolean isIdValid =false;
    private static int TAKE_PHOTO_REQUESTCODE = 1116;
    private static int CHOOSE_PHOTO_REQUESTCODE = 1150;
    RelativeLayout relativeLayout;
    String editableStringField;
    PrisonerDBHeleper prisonerDBHeleper;
    PrisonerModel prisoner;
    EditText edtId;
    EditText mEditText;
    Spinner mSpinner;
    EditText edtName;
    EditText edtEmail;
    EditText edtMobile;
    Spinner spinCrime;
    EditText edtDob;
    Spinner spinSentence;
    ImageView imageView;
    CircleImageView circleImageDob;
    Button btnImage;
    Button btnUpdatePrisoner;
    Button btnBack;
    String image_path;
    ImagePicker imgPicker;
    private String activeField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_field);
        Intent intent=getIntent();
        relativeLayout=(RelativeLayout)findViewById(R.id.relative_layout);
        activeField = intent.getStringExtra("EnableField");
        prisonerDBHeleper=new PrisonerDBHeleper(getApplicationContext(),2);
        edtId=(EditText)findViewById(R.id.field_id);
        edtName=(EditText)findViewById(R.id.field_name);
        edtEmail=(EditText)findViewById(R.id.field_email);
        edtMobile=(EditText)findViewById(R.id.field_mobile);
        spinCrime =(Spinner) findViewById(R.id.field_crime);
        edtDob=(EditText)findViewById(R.id.field_edit_dob);
        spinSentence =(Spinner) findViewById(R.id.field_sentence);
        imageView =(ImageView) findViewById(R.id.field_image_view);
        circleImageDob =(CircleImageView) findViewById(R.id.field_btn_dob);
        Calendar c=Calendar.getInstance();
        final DatePickerDialog datePickerDialog=new DatePickerDialog(this,this, c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        circleImageDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        btnImage=(Button) findViewById(R.id.field_image);
        imgPicker=new ImagePicker(imageView,this);
        btnUpdatePrisoner=(Button)findViewById(R.id.btn_update_prisoner);
        btnBack=(Button)findViewById(R.id.btn_go_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
                builder.setMessage("Are you Sure to Go back").setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        finish();
                    }
                }).setNegativeButton(android.R.string.cancel,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Rect outRect = new Rect();
                    View view;
                    if ((mEditText!=null && mEditText.isFocused()) || (mSpinner!=null && mSpinner.isFocused())) {
                        if(mEditText!=null)
                            view = mEditText;
                        else
                            view = mSpinner;
                    }
                    else
                        view = edtId;
                    view.getGlobalVisibleRect(outRect);
                    if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                        view.clearFocus();
                        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
                return false;
            }
        });
        enableField(activeField, FieldType.fieldEnalbe);
        for(int i=0;i<crimeStrings.length;i++) {
            crimes.add(crimeStrings[0]);
        }
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        edtId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!edtId.getText().toString().equals("")){
                        prisoner = prisonerDBHeleper.getPrisoner(Integer.parseInt(edtId.getText().toString()));
                        if(prisoner.getName()!=null){
                            isIdValid =true;
                            enableField(activeField, FieldType.fieldUpdate);
                            btnUpdatePrisoner.setEnabled(false);
                        }
                        else {
                            edtId.setError("Enter Valid Id.");
                            isIdValid =false;
                        }
                    }
                    else {
                        edtId.setError("Enter Valid Id.");
                        isIdValid =false;
                    }
                }
            }
        });
        btnUpdatePrisoner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePrisoner(prisoner);
            }
        });
        edtName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && isIdValid && edtName.getText()!=null && !edtName.equals(editableStringField))
                {
                    prisoner.setName(edtName.getText().toString());
                    btnUpdatePrisoner.setEnabled(validate.validateName(edtName));
                }
            }
        });
        edtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && isIdValid && edtEmail.getText()!=null && !edtEmail.equals(editableStringField))
                {
                    prisoner.setEmail(edtEmail.getText().toString());
                    btnUpdatePrisoner.setEnabled(validate.validateEmail(edtEmail));
                }
            }
        });
        edtMobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && isIdValid && edtMobile.getText()!=null && !edtMobile.equals(editableStringField))
                {
                    prisoner.setMobile(edtMobile.getText().toString());
                    btnUpdatePrisoner.setEnabled(validate.validateMobile(edtMobile));
                }
            }
        });
        spinCrime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && isIdValid && !spinCrime.getSelectedItem().equals(editableStringField)) {
                    prisoner.setCrime(spinCrime.getSelectedItem().toString());
                    btnUpdatePrisoner.setEnabled(true);
                }
            }
        });
        spinSentence.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && isIdValid && !spinSentence.getSelectedItem().equals(editableStringField)) {
                    prisoner.setSentenced(Boolean.parseBoolean(spinSentence.getSelectedItem().toString()));
                    btnUpdatePrisoner.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        edtDob.setText(validate.generateTextFromDate(year,++month,dayOfMonth));
        if(isIdValid && !edtDob.getText().equals(editableStringField)){
            prisoner.setDob(edtDob.getText().toString());
            btnUpdatePrisoner.setEnabled(true);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CHOOSE_PHOTO_REQUESTCODE){
                image_path = imgPicker.onSelectFromGalleryResult(data);
            }
            else if (requestCode == TAKE_PHOTO_REQUESTCODE){
                image_path = imgPicker.onCaptureImageResult(data);
            }
            if(isIdValid && !editableStringField.equals(image_path)){
                btnUpdatePrisoner.setEnabled(true);
            }
            else{
                btnUpdatePrisoner.setEnabled(false);
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
                boolean result= Utility.checkPermission(UpdateFieldActivity.this);
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

    private void enableField(String field, FieldType fieldType){
        switch(field)
        {
            case "Name":
                if(fieldType== FieldType.fieldEnalbe) {
                    edtName.setVisibility(View.VISIBLE);
                    mEditText=edtName;
                }
                else {
                    edtName.setText(prisoner.getName());
                    editableStringField=prisoner.getName();
                }
                break;
            case "Email":
                if(fieldType== FieldType.fieldEnalbe){
                    edtEmail.setVisibility(View.VISIBLE);
                    mEditText=edtEmail;
                }
                else {
                    edtEmail.setText(prisoner.getEmail());
                    editableStringField=prisoner.getEmail();
                }
                break;
            case "Mobile":
                if(fieldType== FieldType.fieldEnalbe) {
                    edtMobile.setVisibility(View.VISIBLE);
                    mEditText=edtMobile;
                }
                else {
                    edtMobile.setText(prisoner.getMobile());
                    editableStringField=prisoner.getMobile();
                }
                break;
            case "DOB":
                if(fieldType== FieldType.fieldEnalbe){
                    circleImageDob.setVisibility(View.VISIBLE);
                    edtDob.setVisibility(View.VISIBLE);
                }
                else {
                    edtDob.setText(prisoner.getDob());
                    editableStringField=prisoner.getDob();
                }
                break;
            case "Crime":
                if(fieldType== FieldType.fieldEnalbe)
                    spinCrime.setVisibility(View.VISIBLE);
                else{
                    spinCrime.setSelection(crimes.indexOf(prisoner.getCrime()));
                    editableStringField=prisoner.getCrime();
                }
                break;
            case "Image":
                if(fieldType== FieldType.fieldEnalbe)
                    btnImage.setVisibility(View.VISIBLE);
                else {
                    imageView.setImageBitmap(BitmapFactory.decodeFile(prisoner.getPhotoPath()));
                    editableStringField=prisoner.getPhotoPath();
                }
                break;
            case "sentence":
                if(fieldType== FieldType.fieldEnalbe)
                    spinSentence.setVisibility(View.VISIBLE);
                else {
                    if(prisoner.getSentenced())
                        spinSentence.setSelection(0);
                    else
                        spinSentence.setSelection(1);
                    editableStringField=prisoner.getSentenced().toString();
                }
                break;
            default:
        }
    }

    private void updatePrisoner(PrisonerModel prisoner){//TODO

    }
}
