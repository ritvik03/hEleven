package com.example.ritvik.a1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class AddEventActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    EditText entername;
    RatingBar imp;
    int credits;
    String name;
    Button add;
    TextView textView1,textView2,textView3,textView4,textView5;
    TextView textName_i;
    SeekBar seekBar1,seekBar2;
    DatePicker examdate,startdate;
    DB_Controller controller;

    SQLiteDatabase sqlitedb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().setLogo(R.drawable.brain_w);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_add_event);
        sqlitedb=openOrCreateDatabase("EventsList",MODE_PRIVATE,null);
        sqlitedb.execSQL("CREATE TABLE IF NOT EXISTS EmpRegistration(EmpId INTEGER PRIMARY KEY AUTOINCREMENT,EmpName string,EmpImp int);");
        entername = (EditText)findViewById(R.id.enternameData);
        //imp = (RatingBar)findViewById(R.id.impData);
        add = (Button)findViewById(R.id.submitData);
        seekBar1=(SeekBar)findViewById(R.id.seekBar1);
        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2=(SeekBar)findViewById(R.id.seekBar2);
        seekBar2.setOnSeekBarChangeListener(this);
        controller = new DB_Controller(this,"",null,1);
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        textView3 = findViewById(R.id.text3);
        textView4 = findViewById(R.id.text4);
        textView5 = findViewById(R.id.text5);
        //textName_i=findViewById(R.id.textName);
        examdate=findViewById(R.id.examdate);
        examdate.setMinDate(System.currentTimeMillis()+86400*1000);
        //startdate=findViewById(R.id.startdate);
        //startdate.updateDate(2018,02,12);
        //startdate.setMinDate(System.currentTimeMillis());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        //Toast.makeText(getApplicationContext(),"seekbar progress: "+progress, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int progress= seekBar.getProgress();
        Toast.makeText(getApplicationContext(),"seekbar progress: "+progress, Toast.LENGTH_SHORT).show();
    }

    public void onClick(View v){
        /*if(v.getId()==R.id.add){
            name=entername.getText().toString().trim();
            //credits = (int)imp.getRating();
            int credits= seekBar1.getProgress();
            String credit_str = Integer.toString(credits);
            if(name.equals("")||credits == 0){
                Toast.makeText(this,"Fields cannot be Empty",Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                sqlitedb.execSQL("INSERT INTO EmpRegistration(EmpName,EmpImp)VALUES('"+name+"','"+credits+"');");
                Toast.makeText(this,"Record Saved",Toast.LENGTH_SHORT).show();
                return;
            }
        }*/
    }



   /* public void addData(View view) {
        name=entername.getText().toString().trim();
        //credits = (int)imp.getRating();
        int credits= seekBar1.getProgress();
        String credit_str = Integer.toString(credits);
        if(name.equals("")||credits == 0){
            Toast.makeText(this,"Fields cannot be Empty",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            sqlitedb.execSQL("INSERT INTO EmpRegistration(EmpName,EmpImp)VALUES('"+name+"','"+credits+"');");
            Toast.makeText(this,"Record Saved",Toast.LENGTH_SHORT).show();
            //finish();
            //Intent toHomeActivity= new Intent(this, HomeActivity.class);
            //startActivity(toHomeActivity);
            return;
        }
        //Intent toHomeActivity= new Intent(this, HomeActivity.class);
        //startActivity(toHomeActivit);
    }*/

   /* public void showResult(View view) {
        Cursor c;
        int temp;
        String data;
        data="";

        c=sqlitedb.rawQuery("Select * from EventsList;",null);
        c.moveToFirst();

        for(int i=0;c.moveToPosition(i);i++){
            //temp=c.getString(1); //Column 1 = names
            data+= c.getString(1);
            temp=c.getInt(2); //Column 2 = credits
            data+="\t" + Integer.toString(temp)+"\n";
        }

        ((TextView) findViewById(R.id.longtext)).setText(data);

    }*/

   public int getDate(DatePicker d){
       int day=d.getDayOfMonth();
       int month = d.getMonth()+1;
       int year = d.getYear();
       int finaldate=day+100*month+10000*year;
       return finaldate;
   }


    public void onBtn(View view) {
        switch (view.getId()){
            case R.id.submitData:
                try{
                    if(entername.getText().toString()==""||seekBar1.getProgress()==0||seekBar2.getProgress()==0){
                        Toast.makeText(this,"Fields cannot be Empty",Toast.LENGTH_SHORT).show();
                    }
                   /* else if(getDate(startdate)>=getDate(examdate)){
                        Toast.makeText(this,"You have to freeze time to achieve this or\nSet preparation date earlier",Toast.LENGTH_SHORT).show();
                    }*/
                    else{
                        controller.insert_student(entername.getText().toString(),Integer.toString(seekBar1.getProgress()),Integer.toString(seekBar2.getProgress()),Integer.toString(getDate(examdate)),Integer.toString(20180317),Integer.toString(0));
                        Toast.makeText(this,"Record Saved",Toast.LENGTH_SHORT).show();
                    }

                }
                catch(SQLiteException e){
                    Toast.makeText(this,"ALREADY EXISTS",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.deleteData:
                try {
                    controller.delete_student(entername.getText().toString());
                    Toast.makeText(this, "Data Deleted", Toast.LENGTH_SHORT).show();
                }
                catch(SQLiteException e){
                    Toast.makeText(this, "Data does not exist", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.editData:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Enter new completion status");
                final SeekBar new_completition = new SeekBar(this);
                dialog.setView(new_completition);
                new_completition.setOnSeekBarChangeListener(this);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        controller.update_student(entername.getText().toString(),Integer.toString(new_completition.getProgress()));
                    }

                });

                dialog.show();
                break;
 /*               AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Edit details of a exam");
                TextView EnterExamName = null;
                EnterExamName.setText("Enter Exam old name");
                final EditText entername = null;
                entername.setHint("old name");

                /*TextView EnterExamNewName = null;
                EnterExamNewName.setText("Enter Exam new name");
                final EditText enterNewName = null;
                enterNewName.setHint("Leave blank if don't want to change");
                */

 /*               TextView Creditbar = null;
                Creditbar.setText("Set Updated Credits");
                final SeekBar credits = new SeekBar(this);
                credits.setMax(10);
                credits.setProgress(3);
                dialog.setView(credits);
                credits.setOnSeekBarChangeListener(this);

                TextView Pacebar = null;
                Pacebar.setText("Set Updated Pace");
                final SeekBar pace = new SeekBar(this);
                pace.setMax(10);
                pace.setProgress(3);
                dialog.setView(pace);
                pace.setOnSeekBarChangeListener(this);

                TextView EnterExamDate = null;
                EnterExamDate.setText("Enter New Exam date");
                final EditText newdate = null;
                newdate.setHint("yyyymmddd");

                TextView EnterPrepDate = null;
                EnterPrepDate.setText("Enter New Preparation Start date");
                final EditText newprepdate = null;
                newprepdate.setHint("yyyymmddd");

                final SeekBar new_completition = new SeekBar(this);
                dialog.setView(new_completition);
                new_completition.setOnSeekBarChangeListener(this);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        controller.update_student_full(entername.getText().toString(),entername.getText().toString(),Integer.toString(credits.getProgress()),Integer.toString(pace.getProgress()),newdate.getText().toString(),newprepdate.getText().toString(),Integer.toString(new_completition.getProgress()));
                    }

                });

                dialog.show();
                break;*/
            case R.id.show:
                controller.list_all_students(textView1,textView2,textView3,textView4,textView5);
                //controller.list_all_students(textName_i);
                //finish();
                //Intent refresh = new Intent(this,HomeActivity.class);
                //startActivity(refresh);
                break;
            case R.id.datalist:
                startActivity(new Intent(this,DataListActivity.class));
        }
    }
}
