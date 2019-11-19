package com.example.ritvik.a1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

/**
 * Created by Ritvik on 09-Mar-18.
 */

public class DB_Controller extends SQLiteOpenHelper {
    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "TEST.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE STUDENTS(ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT UNIQUE,LASTNAME TEXT,PACE TEXT,EXAMDATE TEXT,STARTDATE TEXT,COMPLETEDPERCENT TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS STUDENTS;");
            onCreate(sqLiteDatabase);

    }

    public void insert_student(String firstname,String lastname,String pace,String date,String start,String completion){
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRSTNAME",firstname);
        contentValues.put("LASTNAME",lastname);
        contentValues.put("PACE",pace);
        contentValues.put("EXAMDATE",date);
        contentValues.put("STARTDATE",start);
        contentValues.put("COMPLETEDPERCENT",completion);
        this.getWritableDatabase().insertOrThrow("STUDENTS","",contentValues);
    }

    public void delete_student(String firstname){
        this.getWritableDatabase().delete("STUDENTS","FIRSTNAME='"+firstname+"'",null);
    }

    public void update_student(String old_firstname,String new_completition){
        this.getWritableDatabase().execSQL("UPDATE STUDENTS SET COMPLETEDPERCENT='"+new_completition+"'WHERE FIRSTNAME='"+old_firstname+"'");
    }

    Cursor cursor;

    public void list_all_students(TextView textView1,TextView textView2,TextView textView3,TextView textView4,TextView textView5){
        cursor = this.getReadableDatabase().rawQuery("SELECT * FROM STUDENTS",null);
        textView1.setText("");
        textView2.setText("");
        textView3.setText("");
        textView4.setText("");
        textView5.setText("");
        while(cursor.moveToNext()){
            textView1.append(cursor.getString(1)+"\n");
            textView2.append(cursor.getString(2)+"\n");
            textView3.append(cursor.getString(3)+"\n");
            textView4.append(dtd2(cursor.getString(4))+"\n");
            textView5.append(cursor.getString(6)+"\n");
            //+"\t\t"+cursor.getString(2)+"\t\t"+cursor.getString(3)+"\t\t"+cursor.getString(4)+"\t\t"+/*cursor.getString(5)+"\t\t"+*/cursor.getString(6)+"\n");
        }
    }

    public String dtd2(String date){
        String dtdt;
        int dateInt = Integer.valueOf(date);
        int year = dateInt/10000;
        int month = (dateInt-10000*year)/100;
        int day = dateInt%100;
        dtdt=Integer.toString(day)+"/"+Integer.toString(month)+"/"+Integer.toString(year);
        return dtdt;
    }


   public Cursor getData(SQLiteDatabase db){
        //Cursor data;
       String[] columns = {"FIRSTNAME","LASTNAME","PACE","EXAMDATE","STARTDATE","COMPLETEDPERCENT"};
        //SQLiteDatabase db=this.getWritableDatabase();
        //String query = "SELECT * FROM STUDENTS";
        //Cursor data = db.rawQuery(query,null);
       Cursor data = db.rawQuery("SELECT * FROM STUDENTS",null);
       if (data != null) {
           data.moveToFirst();
       }
       return data;
   }

    public void update_student_full(String old_firstname,String name, String newcredits, String newpace, String newexamdate, String newprepdate, String newcompletion) {

        this.getWritableDatabase().execSQL("UPDATE STUDENTS SET COMPLETEDPERCENT='"+newcompletion+"'WHERE FIRSTNAME='"+old_firstname+"'");
        this.getWritableDatabase().execSQL("UPDATE STUDENTS SET LASTNAME='"+newcredits+"'WHERE FIRSTNAME='"+old_firstname+"'");
        this.getWritableDatabase().execSQL("UPDATE STUDENTS SET PACE='"+newpace+"'WHERE FIRSTNAME='"+old_firstname+"'");
        this.getWritableDatabase().execSQL("UPDATE STUDENTS SET EXAMDATE='"+newexamdate+"'WHERE FIRSTNAME='"+old_firstname+"'");
        this.getWritableDatabase().execSQL("UPDATE STUDENTS SET STARTDATE='"+newprepdate+"'WHERE FIRSTNAME='"+old_firstname+"'");
        this.getWritableDatabase().execSQL("UPDATE STUDENTS SET FIRSTNAME='"+name+"'WHERE FIRSTNAME='"+old_firstname+"'");

   }


}
