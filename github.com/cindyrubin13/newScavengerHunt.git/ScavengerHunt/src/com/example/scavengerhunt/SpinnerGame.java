package com.example.scavengerhunt;

import java.util.Date;

public class SpinnerGame {
    private String title;
    
    private String objectid;
   
     
    public String toString()
    {
             return objectid + ", " + title;
        
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
    
 
}
