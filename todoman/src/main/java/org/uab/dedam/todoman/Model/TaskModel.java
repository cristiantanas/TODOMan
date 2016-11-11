package org.uab.dedam.todoman.Model;

import java.util.Date;

public class TaskModel {

    private long mTask_Id;
    private String mTitle;
    private String mDescription;
    private Boolean mDone;
    private Date mDueDate;
    private Date mDueTime;

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
    public Date getDueDate() {
        return mDueDate;
    }
    public void setDueDate(Date mDueDate) {
        this.mDueDate = mDueDate;
    }
    public Date getDueTime() {return mDueTime;}
    public void setDueTime(Date mDueTime) { this.mDueTime = mDueTime; }


}
