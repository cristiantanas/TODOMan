package org.uab.dedam.todoman;

public class task {
    private int id;
    private String name;
    private String description;
    private String date;
    private String time;
        private Boolean completed;

    public task() {
        super();
    }

    public task(int id, String name, String description, String date, String time, Boolean completed) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.completed = completed;
    }

    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getName(){return name;}
    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}

    public String getTime() {return time;}
    public void setTime(String time) {this.time = time;}

    public Boolean getCompleted() {return completed;}
    public void setCompleted(Boolean completed) {this.completed = completed;}

   }

