package com.example.scavengerhunt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class PlayerList extends Activity {
    // Declare Variables
    ListView listview;
    List<ParseUser> ob;
    ProgressDialog mProgressDialog;
    PlayerListViewAdapter adapter;
    private List<Players> playerslist; 
    private Button mainMenu;
    String nUserId;
    String nUserName;
    String objectid;
    String nGameName;
    private EditText gameName;
    
    
    
    
   
    
    @Override    
    public void onCreate(Bundle savedInstanceState) {         
        super.onCreate(savedInstanceState);         
        // Get the view from listview_main.xml         
        setContentView(R.layout.player_list);         
        // Execute RemoteDataTask AsyncTask         
     //   new RemoteDataTask().execute(); 
        gameName = (EditText)findViewById(R.id.gameName); 
        
        
        displayListView();
        checkButtonClick();
     //   mainMenu = (Button) findViewById(R.id.mainMenuButton);
     //   mainMenu.setOnClickListener(new View.OnClickListener() {

        //    @Override
         //   public void onClick(View v) {
          //      Intent nextScreen = new Intent(PlayerList.this, MainMenuActivity.class);
          //      startActivity(nextScreen);
                
         //   }
     //   });
    
}  
         
 
     

        private void displayListView() {
         // Create the array
            playerslist = new ArrayList<Players>();
            Log.i("ScavengerHuntActivity", "in do in background after arraylist" );
            // Locate the class table named "Country" in Parse.com
          
            ParseQuery<ParseUser> query = ParseUser.getQuery();
            query.orderByAscending("_created_at");
            query.findInBackground(new FindCallback<ParseUser>() {
              public void done(List<ParseUser> objects, ParseException e) {
                  Log.i("ScavengerHuntActivity", "before if" );
                if (e == null) {
                    // The query was successful.Log.i("ScavengerHuntActivity", "after query find" );
                    for (ParseUser obj : objects) {
                  
                        Log.i("ScavengerHuntActivity", "inside   if ok for loop" );
                        
                        Players player = new Players();
                        player.setUsername(obj.getString("username"));
                        player.setEmail(obj.getString("email"));
                      
                        player.setObjectId (obj.getObjectId());
                        Log.i("scavenger Hunt", "getting username" + "<" + player.getUsername() + ">");
                        Log.i("scavenger Hunt", "getting usereamil" + "<" + player.getEmail() + ">");
                        Log.i("scavenger Hunt", "getting objectid" + "<" + player.getObjectId() + ">");
                        playerslist.add(player);
                        Log.i("scavenger Hunt", "getting playerslist" + "<" + playerslist + ">");
                    }
                    Log.i("ScavengerHuntActivity", "players in post executte for loop"  + playerslist);
                    listview = (ListView) findViewById(R.id.listview);
                    // Pass the results into ListViewAdapter.java
                    adapter = new PlayerListViewAdapter(PlayerList.this, playerslist);
                    // Binds the Adapter to the ListView
                    listview.setAdapter(adapter);
                    
                } else {
                    Log.e("Error in if and for loop", e.getMessage());
                    // Something went wrong.
                }
              }
            });
    
        }
        
        private void checkButtonClick() {
            
        
       
        Button myButton = (Button) findViewById(R.id.sendNotificationButton);
        
        myButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                nGameName = gameName.getText().toString(); 
                Log.i("scavenger Hunt", "entered game name" + "<" + nGameName  + ">");
                StringBuffer response = new StringBuffer();
                response.append("The following were selected...\n");
                
                List<Players> playerslist = adapter.playerslist;
                for(int i=0; i<playerslist.size(); i++) {
                    Players player = playerslist.get(i);
                    Log.i("scavenger Hunt", "size of playerlist" + "<" + playerslist.size() + ">");
                    
                    if(player.isSelected()) {
                      response.append("\n" + player.getUsername()); 
                      ParseInstallation installation = ParseInstallation.getCurrentInstallation();
                      installation.put("username", player.getUsername());
                      installation.saveInBackground();
                      ParsePush parsePush = new ParsePush();
                      ParseQuery pQuery = ParseInstallation.getQuery(); // <-- Installation query
                      pQuery.whereEqualTo("username", player.getUsername()); // <-- you'll probably want to target someone that's not the current user, so modify accordingly
                      parsePush.sendMessageInBackground("Check out the new game " + nGameName + "  that is starting soon!!", pQuery); 
                      Log.i("scavenger Hunt", "push sent to" + "<" + player.getUsername() + ">");
                    }
                  
                }
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                finish();
            }
           
        });
        
      
            
        }
        
    
    }




      
    
        
   
    
    


    
            
            
            
         
   
    


    
   
    


   

 
    
    
    
    
    


