package com.example.scavengerhunt;

public class Items {
    
        public String itemId;
        public String itemName;
        public boolean IsChecked;
        
        @Override
        public String toString()
        {
                 return itemId + ", " + itemName;
            
        }
        
       public String getItemId() {
          return itemId;
       }
     
     
        public String getItemName() {
          return itemName;
       }
     
     
     
       
        
  }



