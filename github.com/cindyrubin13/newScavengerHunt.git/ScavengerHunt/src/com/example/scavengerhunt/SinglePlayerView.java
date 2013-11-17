package com.example.scavengerhunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SinglePlayerView extends Activity {
    
 // Declare Variables     
    String username;
    String  email;
    String objectid;
    TextView txtname;
    
    String desc;       
     @Override
    public void onCreate(Bundle savedInstanceState) {         
        super.onCreate(savedInstanceState);         
        // Get the view from singleitemview.xml         
        setContentView(R.layout.singleplayerview);           
        // Retrieve data from indoor login on item click event         
        Intent i = getIntent();           
        // Get the name         
        username = i.getStringExtra("username");
        email = i.getStringExtra("email"); 
        // Locate the TextView in singleitemview.xml         
        txtname = (TextView) findViewById(R.id.playername);           
        // Load the text into the TextView         
        txtname.setText(username);       
        }
  
    }





