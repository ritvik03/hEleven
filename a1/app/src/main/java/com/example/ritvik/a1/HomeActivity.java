package com.example.ritvik.a1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SQLiteDatabase sqlitedb;
    DB_Controller controller;
    private ListView mListView;
    Cursor cursor;
    //SharedPreferences sharedPreferences;

    public static List DataListActivity1 = null;
    Tasks t1,t2,t3,t4,t5,t6;
    TextView T1=null,T2=null,T3=null;
    TextView T4=null,T5=null,T6=null;
    Date today1,tomorrow;
    int found=0;
    int found2=0;
    int today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().setLogo(R.drawable.brain_w);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //sqlitedb=openOrCreateDatabase("STUDENTS",MODE_PRIVATE,null);
        //TextView task1 = findViewById(R.id.task1);
        //LinearLayout todayTasks = (LinearLayout)(findViewById(R.id.taskstoday));
        //controller.TaskAssigner(todayTasks);
        mListView = (ListView) findViewById(R.id.listview);
        //populateListView();
        Button showthem = findViewById(R.id.showthem);
        DataListActivity1 = new Vector<DataProvider>();
        //alldata=d.getAlldata();
        //DataListActivity1.clear();
        DataListActivity1 = DataListActivity.alldata;
        int size = DataListActivity1.size();
        //Toast.makeText(getApplication(), "LocalAlldata Value 1 name: "+ Integer.toString(size), Toast.LENGTH_SHORT).show();

        //CREATE TASKS NOW
        Calendar c = Calendar.getInstance();

        today1 = c.getTime();
        c.add(Calendar.DAY_OF_YEAR, 1);
        tomorrow = c.getTime();
        Taskoftheday(today1);
        Taskofthetomoday(tomorrow);
        T1 = findViewById(R.id.TextT1);
        T2 = findViewById(R.id.TextT2);
        T3 = findViewById(R.id.TextT3);
        if(found==0){
            T1.setText("No tasks today");
        }
        if(found>0) {
            T1.setText("Task 1: Complete " + tocomp(t1,today) + "% of " + t1.getName() + " by tonight.");
        }
        if(found>1){
            T2.setText("Task 2: Complete "+tocomp(t2,today)+"% of "+t2.getName()+" by tonight.");
        }
        if(found>2){
            T3.setText("Task 3: Complete "+tocomp(t3,today)+"% of "+t3.getName()+" by tonight.");
        }

        if (t1 != null) {
            Toast.makeText(getApplication(), "Tasks Today", Toast.LENGTH_SHORT).show();
        }/*

        T4 = findViewById(R.id.TextT4);
        T5 = findViewById(R.id.TextT5);
        T6 = findViewById(R.id.TextT6);
        if(found2==0){
            T4.setText("No tasks for tomorrow");
        }
        if(found2>0) {
            T4.setText("Task 1: Complete " + tocomp(t4,today) + "% of " + t4.getName() + " by tomorrow");
        }
        if(found2>1){
            T5.setText("Task 2: Complete "+tocomp(t5,today)+"% of "+t5.getName()+" by tomorrow");
        }
        if(found2>2){
            T6.setText("Task 3: Complete "+tocomp(t6,today)+"% of "+t6.getName()+" by tomorrow");
        }*/

    }


    private void Taskoftheday(Date today2) {
        today = today2.getDate()+100*(today2.getMonth()+1)+10000*2018;
        for(int i = 0; i< DataListActivity1.size(); i++){
            if(found<3){
                DataProvider eventData = (DataProvider) DataListActivity1.get(i);
                int eventPrepDate = Integer.valueOf(eventData.getDateOfPrep());
                int examdate = Integer.valueOf(eventData.getDateOfExam());
                if(/*(today>=eventPrepDate)&&*/(examdate>today)){
                    found=found+1;
                    if(found==1) {
                        t1 = new Tasks(eventData, today);
                        Toast.makeText(getApplicationContext(),"doe: "+Integer.toString(t1.getDoe())+"\n Today: "+Integer.toString(today),Toast.LENGTH_SHORT).show();
                    }
                    else if(found==2)
                        t2=new Tasks(eventData,today);
                    else if(found==3)
                        t3=new Tasks(eventData,today);

                }
            }

        }
    }

    private void Taskofthetomoday(Date today2) {
        today = today2.getDate()+100*(today2.getMonth()+1)+10000*2018;
        for(int i = 0; i< DataListActivity1.size(); i++){

            if(found2<3){
                DataProvider eventData = (DataProvider) DataListActivity1.get(i);
                int eventPrepDate = Integer.valueOf(eventData.getDateOfPrep());
                int examdate = Integer.valueOf(eventData.getDateOfExam());
                if(/*(today>=eventPrepDate)&&*/(examdate>today)){
                    found2=found2+1;
                    if(found2==1) {
                        t4 = new Tasks(eventData, today);
                        //Toast.makeText(getApplicationContext(),"doe: "+Integer.toString(t1.getDoe())+"\n Today: "+Integer.toString(today),Toast.LENGTH_SHORT).show();
                    }
                    else if(found2==2)
                        t5=new Tasks(eventData,today);
                    else if(found2==3)
                        t6=new Tasks(eventData,today);

                }
            }

        }
    }

    public String tocomp(Tasks d,int today){
        String pctoCom;
       // String comp = ;
        int completed = d.getCompletion();
        float toComplete = (float) (1.0*(100-completed)/(d.getDoe()-today));
        pctoCom = Float.toString(toComplete);
        return pctoCom;
    }


    private void populateListView() {
        Log.d("HomeActivity","Displaying Data in ListView");

        //get data and append it to list
        //Cursor data = controller.getData();
        Cursor data=cursor;
        ArrayList<String> listData= new ArrayList<>();
        while((data.moveToNext())){
            //get values from database in column 1
            //then add it to arraylist
            listData.add(data.getString(1));
        }
        //Create List Adapter and set the adapter
        ListAdapter adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openAddEvent(View view) {
        Intent toAddEvent = new Intent(this,AddEventActivity.class);
        startActivity(toAddEvent);

    }


    public void showResult(View view) {
        /*Cursor c;
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

        ((TextView) findViewById(R.id.textName)).setText(data);

    */}

    public void onBtnClick(View view) {
        switch(view.getId()){
            case R.id.showthem:
                startActivity(new Intent(this,DataListActivity.class));
                //controller.insert_student("firstname","5","7","20180317","20180309","20");
               /* try{
                    populateListView();
                    Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_SHORT).show();
                }
                catch (SQLiteException e){
                    Toast.makeText(getApplicationContext(),"Populate View couldn't happen",Toast.LENGTH_SHORT).show();
                }
                break;*/
        }
    }
}
