package com.example.ritvik.a1;

import java.util.Date;

/**
 * Created by Ritvik on 09-Mar-18.
 */

public class DataProvider {

    private String name;
    private String credit;
    private String pace;
    private String dateOfExam;
    private String dateOfPrep;
    private String completion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getPace() {
        return pace;
    }

    public void setPace(String pace) {
        this.pace = pace;
    }

    public String getDateOfExam() {
        return dateOfExam;
    }

    public void setDateOfExam(String dateOfExam) {
        this.dateOfExam = dateOfExam;
    }

    public String getDateOfPrep() {
        return dateOfPrep;
    }

    public void setDateOfPrep(String dateOfPrep) {
        this.dateOfPrep = dateOfPrep;
    }

    public String getCompletion() {
        return completion;
    }

    public void setCompletion(String completion) {
        this.completion = completion;
    }

    public Date getEDate(String date1){
        Date date;
        int dateOfExam=Integer.valueOf(date1);
        date = new Date(dateOfExam/10000, ((dateOfExam-10000*(dateOfExam/10000))/100)-1, dateOfExam%100);
        return date;
    }

    public DataProvider(String name, String credit, String pace, String dateOfExam, String dateOfPrep, String completion){
        this.name=name;
        this.credit=credit;
        this.pace=pace;
        this.dateOfExam=dateOfExam;
        this.dateOfPrep=dateOfPrep;
        this.completion=completion;



    }
}
