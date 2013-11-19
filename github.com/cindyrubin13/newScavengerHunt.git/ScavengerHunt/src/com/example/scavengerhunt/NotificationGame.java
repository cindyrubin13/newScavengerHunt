package com.example.scavengerhunt;

import java.util.Date;

public class NotificationGame {
    private String title;
    private String gameid;
    private Date enddate;
    private Date begindate;
    
    
     
    public String toString()
    {
             return gameid + "," + title;
        
    }
    
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getGameId() {
        return gameid;
    }
 
    public void setGameId(String gameid) {
        this.gameid = gameid;
    }
    public Date getBeginDate() {
        return begindate;
    }
 
    public Date setBeginDate(Date begindate) {
        return this.begindate = begindate;
    }
    public Date getEndDate() {
        return enddate;
    }
 
    public Date setEndDate(Date enddate) {
        return this.enddate = enddate;
    }
 
}
