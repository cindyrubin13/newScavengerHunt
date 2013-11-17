package com.example.scavengerhunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleItemView extends Activity{
    // Declare Variables     
    TextView txtname;     
    String desc;       
     @Override
    public void onCreate(Bundle savedInstanceState) {         
        super.onCreate(savedInstanceState);         
        // Get the view from singleitemview.xml         
        setContentView(R.layout.singleitemview);           
        // Retrieve data from indoor login on item click event         
        Intent i = getIntent();           
        // Get the name         
        desc = i.getStringExtra("ItemName");          
        // Locate the TextView in singleitemview.xml         
        txtname = (TextView) findViewById(R.id.desc);           
        // Load the text into the TextView         
        txtname.setText(desc);       
        }
  
    }


