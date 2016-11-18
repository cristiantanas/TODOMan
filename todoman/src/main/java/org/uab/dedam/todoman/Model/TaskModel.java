package org.uab.dedam.todoman.Model;

import java.util.Date;

public class TaskModel {

    private long mTask_Id;
    private String mTitle;
    private String mDescription;
    private Boolean mDone;
    private String mDueDate;
    private String mDueTime;

    public long getId() { return mTask_Id; }
    public void setId(int mTask_Id) { this.mTask_Id = mTask_Id; }
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
    public String getDescription() {
        return mDescription;
    }
    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
    public Boolean getDone() {
        return mDone;
    }
    public void setDone(Boolean mDone) {
        this.mDone = mDone;
    }
    public String getDueDate() {
        return mDueDate;
    }
    public void setDueDate(String mDueDate) {
        this.mDueDate = mDueDate;
    }
    public String getDueTime() {return mDueTime;}
    public void setDueTime(String mDueTime) { this.mDueTime = mDueTime; }


}
