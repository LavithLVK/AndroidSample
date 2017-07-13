package com.example.lvk.shawsank_prison.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lvk.shawsank_prison.R;
import com.example.lvk.shawsank_prison.recylcer.PrisonerModel;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ViewPrisonerActivity extends AppCompatActivity {


    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private TextView prisonerNameView;
    private TextView prisonerCrimeView;
    private TextView prisonerSentenceView;
    private TextView prisonerAgeView;
    private TextView prisonerEmailView;
    private TextView prisonerMobileView;
    private TextView prisonerCaughtView;
    private ImageView prisonerImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        Gson gson=new Gson();
        setContentView(R.layout.activity_view_prisoner);
        PrisonerModel prisoner= gson.fromJson(intent.getStringExtra("Prisoner_JSON"),PrisonerModel.class);
        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);


        prisonerNameView = (TextView) findViewById(R.id.prisoner_name_view);
        prisonerNameView.setText(prisoner.getName());
        prisonerCrimeView = (TextView) findViewById(R.id.prisoner_crime_view);
        prisonerCrimeView.setText(prisoner.getCrime());
        prisonerSentenceView= (TextView) findViewById(R.id.prisoner_sentenced_view);
        String sentenced=prisoner.getSentenced().toString();
        if(sentenced.equals("false")){
            prisonerSentenceView.setText("NOT YET");
        }
        else
        {
            prisonerSentenceView.setText("Yes");
        }
        prisonerAgeView = (TextView) findViewById(R.id.prisoner_age_view);
        prisonerAgeView.setText(Integer.toString(getAge(prisoner.getDob())));
        prisonerEmailView = (TextView) findViewById(R.id.prisoner_email_view);
        prisonerEmailView.setText(prisoner.getEmail());
        prisonerMobileView = (TextView) findViewById(R.id.prisoner_mobile_view);
        prisonerMobileView.setText(prisoner.getMobile());
        prisonerCaughtView = (TextView) findViewById(R.id.prisoner_caught_view);
        prisonerCaughtView.setText(prisoner.getCreated_at());
        prisonerImageView=(ImageView)findViewById(R.id.prisoner_image_view);
        prisonerImageView.setImageBitmap(BitmapFactory.decodeFile(prisoner.getPhotoPath()));
    }

    private int getAge(String date){
        int age=0;
        int length=date.length();
        int birthYear=Integer.parseInt(date.substring(length-4));
        int birthMonth=Integer.parseInt(date.substring(4,5));
        DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date currentDate=new Date();
        int currentYear=Integer.parseInt((dateFormat.format(currentDate)).substring(0,4));
        int currentMonth=Integer.parseInt((dateFormat.format(currentDate)).substring(6,7));
        age=currentYear-birthYear;
        if(birthMonth<=currentMonth){
            return age;
        }
        else
        {
            return age-1;
        }
    }


    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            prisonerNameView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mVisible = false;
        mControlsView.setVisibility(View.GONE);

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        prisonerNameView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}