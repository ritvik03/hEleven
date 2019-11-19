package com.example.ritvik.a1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ritvik on 09-Mar-18.
 */

public class ListDataAdapter extends ArrayAdapter{

    List list = new ArrayList();


    private String name1;
    private String credit1;
    private String pace1;
    private String dateOfExam1;
    private String dateOfPrep1;
    private String completion1;

    public ListDataAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void data(String name, String credit, String pace, String doe, String dop, String completion) {
        name1=name;
        credit1=credit;
        pace1=pace;
        dateOfExam1=doe;
        dateOfPrep1=dop;
        completion1=completion;
    }

    static class LayoutHandler{
        TextView NAME,CREDIT,PACE,DOE,DOP,COMP;
    }

    @Override
    public void add(Object object){
        super.add(object);
        list.add(object);
    }

    public String dtd(String date){
        String dtdt;
        int dateInt = Integer.valueOf(date);
        int year = dateInt/10000;
        int month = (dateInt-10000*year)/100;
        int day = dateInt%100;
        dtdt=Integer.toString(day)+"/"+Integer.toString(month)+"/"+Integer.toString(year);
        return dtdt;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        LayoutHandler layoutHandler;
        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            layoutHandler=new LayoutHandler();
            layoutHandler.NAME=(TextView)row.findViewById(R.id.name_text);
            layoutHandler.CREDIT=(TextView)row.findViewById(R.id.credits_text);
            layoutHandler.PACE=(TextView)row.findViewById(R.id.pace_text);
            layoutHandler.DOE=(TextView)row.findViewById(R.id.date_text);
            layoutHandler.DOP=(TextView)row.findViewById(R.id.prep_date);
            layoutHandler.COMP=(TextView)row.findViewById(R.id.completition_text);
            row.setTag(layoutHandler);
        }
        else {
            layoutHandler = (LayoutHandler) row.getTag();
        }
            DataProvider dataProvider =  (DataProvider)this.getItem(position);
            layoutHandler.NAME.setText(dataProvider.getName());
            layoutHandler.CREDIT.setText(dataProvider.getCredit()+"/10");
            layoutHandler.PACE.setText(dataProvider.getPace()+"/10");
            layoutHandler.DOE.setText(dtd(dataProvider.getDateOfExam()));
            layoutHandler.DOP.setText(dataProvider.getDateOfPrep());
            layoutHandler.COMP.setText(dataProvider.getCompletion()+"%");

        return row;
    }
}
