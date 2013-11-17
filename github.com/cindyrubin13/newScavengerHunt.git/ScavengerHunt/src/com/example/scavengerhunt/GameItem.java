package com.example.scavengerhunt;

import android.widget.Button;

public class GameItem {
   
    public String gameItemName;
    public String gameObjectId;
    public String objectId;
    public boolean isFound = false;
    
    @Override
    public String toString()
    {
             return objectId + ", " + gameItemName + ", " + gameObjectId;
        
    }
    
        
    public String getObjectId() {
        return objectId;
    }
 
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    public String getGameItemName() {
        return gameItemName;
    }
 
    public void setGameItemName(String gameItemName) {
        this.gameItemName = gameItemName;
    }
    public String getGameObjectId() {
        return gameObjectId;
    }
 
    public void setGameObjectId(String gameObjectId) {
        this.gameObjectId = gameObjectId;
    }
   
    
}
