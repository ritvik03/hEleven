package com.example.ritvik.a1;

/**
 * Created by Ritvik on 10-Mar-18.
 */

public class Tasks {
    private int pcToDo;
    private int doe;
    private String name;
    private int completion;

    public int getPcToDo() {
        return pcToDo;
    }

    public void setPcToDo(int pcToDo) {
        this.pcToDo = pcToDo;
    }

    public int getDoe() {
        return doe;
    }

    public void setDoe(int doe) {
        this.doe = doe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCompletion() {
        return completion;
    }

    public void setCompletion(int completion) {
        this.completion = completion;
    }

    public Tasks(DataProvider d, int dateToday){
        this.name=d.getName();
        this.completion=Integer.valueOf(d.getCompletion());
        this.doe=Integer.valueOf(d.getDateOfExam());
        //this.pcToDo=(100-this.completion)/(this.doe-dateToday);
    }
}
