package com.example.lvk.sqlite_sample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lavith.edam on 6/16/2017.
 */

class PrisonDBHelper extends SQLiteOpenHelper {
    private static final String table_users="ShawshankPrison";
    private static final String id="Id";
    private static final String name ="Name";
    private static final String age="Age";
    private static final String crime="Crime";
    private static final String isSentenced="IsSentenced";
    SQLiteDatabase mydb;
    private Cursor mCursor;

    PrisonDBHelper(Context context, int version) {
        super(context, table_users, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table ="CREATE TABLE " + table_users + "("
                + id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + name + " TEXT,"
                + age +" INT,"+crime +" TEXT,"+isSentenced+ " BOOLEAN"
                + ")";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_users);
        onCreate(db);
    }

    public List<Prisoner> getAllPrisoners() {
        String[] columns=new String[]{id, name,age,crime,isSentenced/*,KEY_IMAGE_ATTACHMENT*/};
//        String sortOrder=KEY_EMAIL+" ASC";
        ArrayList<Prisoner> prisoners = new ArrayList<>();
        mydb =this.getReadableDatabase();

        mCursor = mydb.query(table_users,columns,null,null,null,null,null);
        mCursor.moveToFirst();
        while(mCursor.isAfterLast()==false){
            Prisoner prisoner=new Prisoner();
            prisoner.setId(Integer.parseInt(mCursor.getString(mCursor.getColumnIndex("Id"))));
            prisoner.setName(mCursor.getString(mCursor.getColumnIndex("Name")));
            prisoner.setAge(Integer.parseInt(mCursor.getString(mCursor.getColumnIndex("Age"))));
            prisoner.setCrime(mCursor.getString(mCursor.getColumnIndex("Crime")));
            prisoner.setSentenced(Boolean.parseBoolean(mCursor.getString(mCursor.getColumnIndex("IsSentenced"))));
            prisoners.add(prisoner);
            mCursor.moveToNext();
        }
        closeDB();
        return prisoners;
    }

    protected long insertPrisoner(Prisoner prisoner){
        mydb=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Name",prisoner.getName());
        values.put("Age",prisoner.getAge());
        values.put("Crime",prisoner.getCrime());
        values.put("IsSentenced",prisoner.getSentenced());
        return mydb.insert(table_users,null,values);
    }

    public Prisoner getPrisoner(int id){
        mydb =this.getReadableDatabase();
        Prisoner prisoner=new Prisoner();
        prisoner = (Prisoner) mydb.rawQuery("select * from "+table_users+" where id ="+id,null);
        if(mCursor.moveToNext()){

        }
        closeDB();
        return prisoner;
    }

    public void updatePrisoner(Prisoner prisoner){
        mydb =this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Name",prisoner.getName());
        values.put("Age",prisoner.getAge());
        values.put("Crime",prisoner.getCrime());
        values.put("IsSentenced",prisoner.getSentenced());
        mydb.update(table_users,values,null,new String []{Integer.toString(prisoner.getId())});
    }

    public void dropTable(){
        mydb=this.getWritableDatabase();
        mydb.execSQL("DROP TABLE IF EXISTS "+table_users);
    }

    void closeDB(){
        mCursor.close();
        mydb.close();
    }


}
