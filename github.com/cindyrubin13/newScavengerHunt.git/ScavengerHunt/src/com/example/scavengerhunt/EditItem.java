package com.example.scavengerhunt;

import java.util.List;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditItem extends Activity {
    private EditText updateItem;
    private Button submitUpdate;
    private Button deleteButton;
    private Button addButton;
    String objectId;
    String itemName;
    TextView currentItem;
    String nUpdateItem;
    String gameId;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_item);
        Bundle extras = getIntent().getExtras();
        Log.i("ScavengerHuntActivity", "gameid value itemslist 1" + extras );
        if (extras != null) {
            
             objectId = extras.getString("ItemId");
             itemName = extras.getString("ItemName");
            Log.i("ScavengerHuntActivity", "gameid value itemslist 1" + objectId + "  " + itemName );
         }
        
        updateItem = (EditText)findViewById(R.id.updateItem);
        currentItem = (TextView)findViewById(R.id.currentItem);
        currentItem.setText(itemName);
        nUpdateItem = updateItem.getText().toString(); 
       
         Log.i("ScavengerHuntActivity", "retrieved newupdate item" + nUpdateItem );

        submitUpdate = (Button) findViewById(R.id.updateButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        
         
        submitUpdate.setOnClickListener(new View.OnClickListener() {
         
            @Override
            public void onClick(View v) {
                nUpdateItem = updateItem.getText().toString();
                Log.i("ScavengerHuntActivity", "retrieved newupdate item" + nUpdateItem );
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("newItems");
           
                query.getInBackground( objectId, new GetCallback<ParseObject>() {
               
                  public void done( ParseObject object, ParseException e) {
                      Log.i("ScavengerHuntActivity", "inside parse part" + objectId );
                    if (e == null) {
                      // object will be your game score
                        Log.i("ScavengerHuntActivity", "retrieved objectid1" + objectId );
                        Log.i("ScavengerHuntActivity", "retrieved update item" + nUpdateItem );
                        // get the gameId to go back to listview with new items
                        gameId = object.getString("gameId");
                        Log.i("ScavengerHuntActivity", "retrieved gameId in update methd" + gameId );
                        object.put("item1", nUpdateItem);
                        object.saveInBackground();
                    
                        Intent nextScreen = new Intent(getApplicationContext(), NewGameList.class);
                        nextScreen.putExtra("gameId", gameId);
                        startActivity(nextScreen);
                    } 
                        
                     else {
                        Log.i("ScavengerHuntActivity", "retrieved objectid2" + e.getMessage() );
                      // something went wrong
                    }
                  }
                });
                // TODO Auto-generated method stub
                
            }
        });
        
        
        deleteButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("newItems");
                //query.whereEqualTo("objectId", objectId);
                query.getInBackground( objectId, new GetCallback<ParseObject>() {
                //  query.findInBackground( new FindCallback<ParseObject>() {
                  public void done( ParseObject object, ParseException e) {
                      Log.i("ScavengerHuntActivity", "inside delete part" + objectId );
                    if (e == null) {
                      // object will be your game score
                     // get the gameId to go back to listview with new items
                        gameId = object.getString("gameId");
                        Log.i("ScavengerHuntActivity", "retrieved gameId in delete methd" + gameId );
                      //  Log.i("ScavengerHuntActivity", "retrieved objectid1" + gameId );
                        object.deleteInBackground();
                     
                        Intent nextScreen = new Intent(getApplicationContext(), NewGameList.class);
                        nextScreen.putExtra("gameId", gameId);
                        startActivity(nextScreen);
                    } 
                        
                     else {
                        Log.i("ScavengerHuntActivity", "error in delete" + e.getMessage() );
                      // something went wrong
                    }
                  }
                });
                // TODO Auto-generated method stub
                
            }
        } );     
    
    }
    
}