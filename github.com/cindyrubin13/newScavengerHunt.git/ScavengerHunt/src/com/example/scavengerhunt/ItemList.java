package com.example.scavengerhunt;

import com.parse.ParseObject;
import com.parse.SaveCallback;

import android.app.Activity;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ItemList extends Activity {
    private EditText itemOne; 
    private EditText yesornoItem;
    private String gameId;
    
    private Button submitForm;
    private Button finishForm;

    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_input);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String gameId = extras.getString("gameId");
            Log.i("ScavengerHuntActivity", "gameid value itemslist 1" + gameId );
         }
        
        
        itemOne = (EditText)findViewById(R.id.item1);
        
        submitForm = (Button) findViewById(R.id.button3);
        finishForm = (Button) findViewById(R.id.finbutton);
        
        submitForm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                
                final String itemOneValue = itemOne.getText().toString();
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    String gameId = extras.getString("gameId");
                    Log.i("ScavengerHuntActivity", "gameid value itemslist 1" + gameId );
                 
                
                Log.i("ScavengerHuntActivity", "game item1 inputted " + itemOneValue );
                final ParseObject newItems =  new ParseObject("newItems");
             
                newItems.put("item1", itemOneValue);
                newItems.put("gameId", gameId);
              
              
                newItems.saveInBackground();
               
                Intent nextScreen = new Intent(getApplicationContext(), ItemList.class);
                nextScreen.putExtra("gameId", gameId);
                startActivity(nextScreen);     
                }
      
           }

        });           
        finishForm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    gameId = extras.getString("gameId");
                  
                    Intent nextScreen = new Intent(getApplicationContext(), NewGameList.class);
                    nextScreen.putExtra("gameId", gameId);
                    startActivity(nextScreen);
                }
                
            }
            
            
            
        });  
        }}
          
           
  
        
   


