package com.example.scavengerhunt;

import java.util.Date;

public class Game {
    private String title;
    private Date begdate;
    private String objectid;
    private Date enddate;
     
    public String toString()
    {
             return objectid + ", " + title + "," + begdate;
        
    }
    
   
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getObjectId() {
        return objectid;
    }
 
    public void setObjectId(String objectid) {
        this.objectid = objectid;
    }
    public Date getBeginDate() {
        return begdate;
    }
 
    public Date setBeginDate(Date begdate) {
        return this.begdate = begdate;
    }
    public Date getEndDate() {
        return enddate;
    }
 
    public Date setEndDate(Date enddate) {
        return this.enddate = enddate;
    }
   
}

