package com.example.lvk.shawsank_prison.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.ImageReader;
import android.media.ImageWriter;

import com.example.lvk.shawsank_prison.recylcer.PrisonerModel;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lavith.edam on 6/30/2017.
 */

public class PrisonerDBHeleper extends SQLiteOpenHelper {

    private static final String table_users="Shawshank_Prison";
    private static final String id="Id";
    private static final String name ="Name";
    private static final String email ="Email";
    private static final String mobile_No ="Mobile_No";
    private static final String profile_Photo_local ="Profile_Photo_Local";
    private static final String profile_Photo_online ="Profile_Photo_Online";
    private static final String dob="DOB";
    private static final String crime="Crime";
    private static final String isSentenced="IsSentenced";
    private static final String created_at ="Created_at";
    private static final String updated_at ="Updated_at";

    SQLiteDatabase mydb;
    private Cursor mCursor;

    public PrisonerDBHeleper(Context context, int version) {
        super(context, table_users, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table ="CREATE TABLE " + table_users + "("
                + id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + name + " TEXT,"
                + email +" TEXT,"+mobile_No +" TEXT,"+dob+ " DATE,"+ profile_Photo_local +" BLOB,"
                + profile_Photo_online + " TEXT," + crime+" TEXT,"+ isSentenced +" BOOLEAN, "
                +created_at +" DATETIME DEFAULT CURRENT_TIMESTAMP," +updated_at+" DATETIME"
                + ")";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_users);
        onCreate(db);
    }

    public List<PrisonerModel> getAllPrisoners() {
        String[] columns=new String[]{id, name,email,mobile_No, profile_Photo_local,dob,crime,isSentenced,created_at,updated_at/*,KEY_IMAGE_ATTACHMENT*/};
        ArrayList<PrisonerModel> prisoners = new ArrayList<>();
        mydb =this.getReadableDatabase();

        mCursor = mydb.query(table_users,columns,null,null,null,null,null);
        mCursor.moveToFirst();
        while(mCursor.isAfterLast()==false){
            PrisonerModel prisoner=new PrisonerModel();
            prisoner.setId(Integer.parseInt(mCursor.getString(mCursor.getColumnIndex(id))));
            prisoner.setName(mCursor.getString(mCursor.getColumnIndex(name)));
            prisoner.setEmail(mCursor.getString(mCursor.getColumnIndex(email)));
            prisoner.setMobile(mCursor.getString(mCursor.getColumnIndex(mobile_No)));
            prisoner.setPhotoPath(mCursor.getString(mCursor.getColumnIndex(profile_Photo_local)));
            prisoner.setDob(mCursor.getString(mCursor.getColumnIndex(dob)));
            prisoner.setCrime(mCursor.getString(mCursor.getColumnIndex(crime)));
            prisoner.setSentenced(Boolean.parseBoolean(mCursor.getString(mCursor.getColumnIndex(isSentenced))));
            prisoner.setCreated_at(mCursor.getString(mCursor.getColumnIndex(created_at)));
            prisoner.setUpadated_at(mCursor.getString(mCursor.getColumnIndex(updated_at)));
            prisoners.add(prisoner);
            mCursor.moveToNext();
        }
        closeDB();
        return prisoners;
    }

    public long insertPrisoner(PrisonerModel prisoner){
        mydb=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(name,prisoner.getName());
        values.put(email,prisoner.getEmail());
        values.put(mobile_No,prisoner.getMobile());
        values.put(dob,prisoner.getDob());
        values.put(profile_Photo_local,prisoner.getPhotoPath());
        values.put(crime,prisoner.getCrime());
        values.put(isSentenced,prisoner.getSentenced());
        return mydb.insert(table_users,null,values);
    }

    public PrisonerModel getPrisoner(int id){//TODO
        mydb =this.getReadableDatabase();
        PrisonerModel prisoner=new PrisonerModel();
        mCursor= mydb.rawQuery("select * from "+table_users+" where id ="+id+"",null);
        if(mCursor!=null) {
            if (mCursor.moveToFirst()) {
                prisoner.setId(id);
                prisoner.setName(mCursor.getString(mCursor.getColumnIndex(name)));
                prisoner.setEmail(mCursor.getString(mCursor.getColumnIndex(email)));
                prisoner.setMobile(mCursor.getString(mCursor.getColumnIndex(mobile_No)));
                prisoner.setPhotoPath(mCursor.getString(mCursor.getColumnIndex(profile_Photo_local)));
                prisoner.setDob(mCursor.getString(mCursor.getColumnIndex(dob)));
                prisoner.setCrime(mCursor.getString(mCursor.getColumnIndex(crime)));
                prisoner.setSentenced(Boolean.parseBoolean(mCursor.getString(mCursor.getColumnIndex(isSentenced))));
                prisoner.setCreated_at(mCursor.getString(mCursor.getColumnIndex(created_at)));
                prisoner.setUpadated_at(mCursor.getString(mCursor.getColumnIndex(updated_at)));
            }
        }
        else
        {
            return prisoner;
        }
        closeDB();
        return prisoner;
    }

    public void updatePrisoner(PrisonerModel prisoner){//TODO
        mydb =this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(name,prisoner.getName());
        values.put(email,prisoner.getEmail());
        values.put(mobile_No,prisoner.getMobile());
//        values.put(pro,prisoner.getName());
        values.put(name,prisoner.getName());
        values.put(dob,prisoner.getDob().toString());
        values.put(crime,prisoner.getCrime());
        values.put(isSentenced,prisoner.getSentenced());
        mydb.update(table_users,values,null,new String []{Integer.toString(prisoner.getId())});
    }

    void closeDB(){
        mCursor.close();
        mydb.close();
    }


}
