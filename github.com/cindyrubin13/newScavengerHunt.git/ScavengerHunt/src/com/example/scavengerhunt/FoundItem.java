package com.example.scavengerhunt;

import java.util.Date;

public class FoundItem {
    private String gameid;
    private Date createddate;
    private String itemobjectid;
    private String userinfo;
    public boolean isFound = false;
     
    public String toString()
    {
             return gameid + ", " + itemobjectid + "," + userinfo + "," + createddate;
        
    }
    
   
 
    public String getGameId() {
        return gameid;
    }
 
    public void setGameId(String gameid) {
        this.gameid = gameid;
    }
    
    public String getItemObjectId() {
        return itemobjectid;
    }
 
    public void setItemObjectId(String itemobjectid) {
        this.itemobjectid = itemobjectid;
    }
    public Date getCreatedAtDate() {
        return createddate;
    }
 
    public Date setCreatedAtDate(Date createddate) {
        return this.createddate = createddate;
    }
    
    public String getUserInfo() {
        return userinfo;
    }
 
    public void setUserInfo(String userinfo) {
        this.userinfo = userinfo;
    }

}
