package com.example.scavengerhunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleGameView extends Activity {
 // Declare Variables     
    String gameName;
    TextView txtname;
    
    String desc;       
     @Override
    public void onCreate(Bundle savedInstanceState) {         
        super.onCreate(savedInstanceState);         
        // Get the view from singleitemview.xml         
        setContentView(R.layout.singlegameview);           
        // Retrieve data from indoor login on item click event         
        Intent i = getIntent();           
        // Get the name         
        gameName = i.getStringExtra("playGameName");
        // Locate the TextView in singleitemview.xml         
        txtname = (TextView) findViewById(R.id.gamename);           
        // Load the text into the TextView         
        txtname.setText(gameName);       
        }
  
    }


