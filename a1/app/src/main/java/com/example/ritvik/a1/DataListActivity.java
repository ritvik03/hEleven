package com.example.ritvik.a1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class DataListActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    ListView listview;
    SQLiteDatabase sqlitedb;
    DB_Controller controller;
    Cursor cursor;
    ListDataAdapter listDataAdapter;
    SharedPreferences sharedPreferences;
    public static final List alldata = new Vector<DataProvider>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().setLogo(R.drawable.brain_w);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_data_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoHome();
            }
        });

        listview=findViewById(R.id.listview);
        listDataAdapter = new ListDataAdapter(getApplicationContext(),R.layout.row_layout);
        //listview.setAdapter(listDataAdapter);
        controller= new DB_Controller(getApplicationContext(),"TEST.db",null,1);
        sqlitedb = controller.getReadableDatabase();
        cursor = controller.getData(sqlitedb);
        startManagingCursor(cursor);

        int i = 0;
        String name="nothing",credit="nothing",pace="nothing",doe="nothing",dop="nothing",completion="nothing";
        if(cursor.moveToFirst()){

            for(i=0;cursor.moveToNext();i++){

                cursor.moveToPosition(i);

                name=cursor.getString(1);
                credit=cursor.getString(2);
                pace=cursor.getString(3);
                doe=cursor.getString(4);
                dop=cursor.getString(5);
                completion=cursor.getString(6);

                DataProvider dataProvider=new DataProvider(name,credit,pace,doe,dop,completion);
                //listDataAdapter.add(dataProvider);
                //listDataAdapter.data(name,credit,pace,doe,dop,completion);
                alldata.add(dataProvider);

            }
        }

        //listview.setAdapter(listDataAdapter);
        Collections.sort(alldata, new Comparator() {

            @Override
            public int compare(Object o, Object t) {
                DataProvider d1=(DataProvider) o;
                DataProvider d2=(DataProvider) t;
                if(Integer.valueOf(d1.getPace())*Integer.valueOf(d1.getCredit()) < Integer.valueOf(d2.getPace())*Integer.valueOf(d2.getCredit()))
                    return 1;
                if(Integer.valueOf(d1.getPace())*Integer.valueOf(d1.getCredit()) == Integer.valueOf(d2.getPace())*Integer.valueOf(d2.getCredit()))
                    return 1;
                if(Integer.valueOf(d1.getPace())*Integer.valueOf(d1.getCredit()) > Integer.valueOf(d2.getPace())*Integer.valueOf(d2.getCredit()))
                    return -1;
                else{
                    return 1;
                }
            }

        });

        Toast.makeText(getApplicationContext(),"Data List Activity\nSorted decreasing priority",Toast.LENGTH_SHORT).show();

        cursor.moveToFirst();
        for(i=0;cursor.moveToNext();i++){

            cursor.moveToPosition(i);

            listDataAdapter.add(alldata.get(i));
            //listDataAdapter.data(name,credit,pace,doe,dop,completion);

        }
        listview.setAdapter(listDataAdapter);
    }

    private void gotoHome() {

        //HomeActivity.DataListActivity1.clear();
        Intent toHome = new Intent(this,HomeActivity.class);
        startActivity(toHome);
    }



    public List getAlldata() {
        return alldata;
    }


    /*public void onClickRow(View view) {
        switch (view.getId()){
            case R.id.BtnFromDisplay :
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
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

               /* TextView Creditbar = null;
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
                        /*if(enterNewName.getText().toString()==""||enterNewName.getText().toString()==" ") {
                            enterNewName =  entername ;
                        }*/
        /*                controller.update_student_full(entername.getText().toString(),entername.getText().toString(),Integer.toString(credits.getProgress()),Integer.toString(pace.getProgress()),newdate.getText().toString(),newprepdate.getText().toString(),Integer.toString(new_completition.getProgress()));
                    }

                });

                dialog.show();
                break;

        }
    }*/

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
