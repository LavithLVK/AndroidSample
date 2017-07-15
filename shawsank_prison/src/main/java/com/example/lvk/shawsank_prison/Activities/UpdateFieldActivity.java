package com.example.lvk.shawsank_prison.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.lvk.shawsank_prison.R;
import com.example.lvk.shawsank_prison.Utils.ImagePicker;
import com.example.lvk.shawsank_prison.Utils.Utility;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateFieldActivity extends AppCompatActivity {

    private static int TAKE_PHOTO_REQUESTCODE = 1116;
    private static int CHOOSE_PHOTO_REQUESTCODE = 1150;
    EditText edtName;
    EditText edtEmail;
    EditText edtMobile;
    Spinner spinCrime;
    EditText edtDob;
    Spinner spinSentence;
    ImageView imageView;
    CircleImageView circleImageDob;
    Button btnImage;
    String image_path;
    ImagePicker imgPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_field);
        edtName=(EditText)findViewById(R.id.field_name);
        edtEmail=(EditText)findViewById(R.id.field_email);
        edtMobile=(EditText)findViewById(R.id.field_mobile);
        spinCrime =(Spinner) findViewById(R.id.field_crime);
        edtDob=(EditText)findViewById(R.id.field_edit_dob);
        spinSentence =(Spinner) findViewById(R.id.field_sentence);
        imageView =(ImageView) findViewById(R.id.field_image_view);
        circleImageDob =(CircleImageView) findViewById(R.id.field_btn_dob);
        btnImage=(Button) findViewById(R.id.field_image);
        imgPicker=new ImagePicker(imageView,this);
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        Intent intent=getIntent();
        enableField(intent.getStringExtra("EnableField"));

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

    private void enableField(String field){
        switch(field)
        {
            case "Name":
                edtName.setVisibility(View.VISIBLE);
                break;
            case "Email":
                edtEmail.setVisibility(View.VISIBLE);
                break;
            case "Mobile":
                edtMobile.setVisibility(View.VISIBLE);
                break;
            case "DOB":
                circleImageDob.setVisibility(View.VISIBLE);
                edtDob.setVisibility(View.VISIBLE);
                break;
            case "Crime":
                spinCrime.setVisibility(View.VISIBLE);
                break;
            case "Image":
                btnImage.setVisibility(View.VISIBLE);
                break;
            case "sentence":
                spinSentence.setVisibility(View.VISIBLE);
                break;
            default:
        }


    }
}
