package com.example.scavengerhunt;

public class Players {
    private String username;
    private String email;
    private String objectid;
    boolean selected = false;
    boolean checked = false;
     
    public String toString()
    {
             return username + ", " + email + "," + objectid;
        
    }
    
   
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getObjectId() {
        return objectid;
    }
 
    public void setObjectId(String objectid) {
        this.objectid = objectid;
    }
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean isSelected() {
        return selected;
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
  
}
