package com.example.lvk.shawsank_prison.Utils;

import android.widget.TextView;

/**
 * Created by lavith.edam on 7/17/2017.
 */

public class Validate {

    public Boolean validateName(TextView name){
        int length=name.getText().length();
        if(length==0){
            name.setError( "Name is required!" );
            return false;
        }
        else if(length==1){
            name.setError( "Name Should contain more than one character." );
            return false;
        }
        else
        {
            return true;
        }
    }

    public Boolean validateEmail(TextView email){
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if(!email.getText().toString().trim().matches(emailPattern)){
            email.setError("Enter valid email address");
            return false;
        }
        else
        {
            return true;
        }
    }

    public Boolean validateMobile(TextView mobile){
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
            return false;
        }
        else if(length-zero_count<10||length-zero_count>10){
            mobile.setError("Enter valid mobile no.");
            return false;
        }
        else {
            return true;
        }
    }

    public String generateTextFromDate(int year, int month, int dayOfMonth){
        String monthTemp=(month<=9)?("0"+month):(month+"");
        String dayTemp=(dayOfMonth<=9)?("0"+dayOfMonth):(dayOfMonth+"");
        return dayTemp+"/"+monthTemp+"/"+year;
    }
}
