package com.example.scavengerhunt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
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
  //  List<String> game;
    ProgressDialog mProgressDialog;
    PlayerListViewAdapter adapter;
    private List<Players> playerslist; 
    private Button mainMenu;
    private String notificationGameId;
    String nUserId;
    String nUserName;
    String objectid;
    String nGameName;
    private EditText gameName;
    private PlayerList context = this;
    private Spinner spinner;
    private Button btnSubmit;
    private List<MyGame> mygamelist = null;
    private String gameId;
    ParseUser currentUser;
   
    // New code to get objectId for game
    ArrayList<String> gamelist;
    ArrayList<SpinnerGame> gameSearch;
   // HashMap<String, ParseObject> gameNamesToObjectId;
    
   
    
    @Override    
    public void onCreate(Bundle savedInstanceState) {         
        super.onCreate(savedInstanceState);         
        // Get the view from listview_main.xml         
        setContentView(R.layout.player_list);         
     //    Execute RemoteDataTask AsyncTask         
   //   new RemoteDataTask().execute(); 
     //   gameName = (EditText)findViewById(R.id.gameName); 
     //   gameNamesToObjectId = new HashMap<String,ParseObject>();
       fillSpinner();
     //  addListenerOnButton();
       addListenerOnSpinnerItemSelection();
       addListenerOnButton();
        displayListView();
        checkButtonClick();
     
    
}  
         
 
     private void fillSpinner()
     {
         currentUser = ParseUser.getCurrentUser();
         
          final Spinner spinner = (Spinner)findViewById(R.id.spinner);
         //new code
         gamelist = new ArrayList<String>();
         gameSearch = new ArrayList<SpinnerGame>();
         //end of new code
         ParseQuery<ParseObject> query = ParseQuery.getQuery("newGame");
         query.whereEqualTo("userObjectId", currentUser.getObjectId());
       
          Log.i("scavenger Hunt", "in parse query"  + currentUser.getObjectId());
          query.findInBackground(new FindCallback<ParseObject>() {
              public void done(List<ParseObject> objects, ParseException e) {
              Log.i("scavenger Hunt", "in parse query2 getting  games" );
                  if (e == null) {
                      Log.d("games", "Retrieved " + objects.size() + " games");
                    //   List<String> game = new ArrayList<String>();
                     
                          for (ParseObject obj : objects) {
                              SpinnerGame gamepop = new SpinnerGame();
                              gamepop.setTitle(obj.getString("title"));
                              Log.i("scavenger Hunt", "title" + "<" + obj.getString("title") +">");
                              gamepop.setObjectId(obj.getObjectId());
                              Log.i("scavenger Hunt", "title" + "<" + obj.getObjectId() +">");
                              gameSearch.add(gamepop);
                              gamelist.add(obj.getString("title"));
                              
                              Log.i("scavenger Hunt", "gamelist" + "<" + gamelist +">");
                              
                              Log.i("scavenger Hunt", "gameSearch" + "<" + gameSearch +">");
                          }    
                          ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, gamelist);
                          dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                          spinner.setAdapter(dataAdapter);

                  }
                  }});      
          }
     public void addListenerOnSpinnerItemSelection() {
         Log.i("scavenger Hunt", "inspinner add listerner portion " + "<" + gamelist +">");
         spinner = (Spinner) findViewById(R.id.spinner);
         spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
       //  spinner.setAdapter(new ArrayAdapter<String>(PlayerList.this,
        //         android.R.layout.simple_spinner_dropdown_item,
          //       gamelist));
         
         spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             
             @Override
             public void onItemSelected(AdapterView<?> arg0,
                     View arg1, int position, long arg3) {
                 // TODO Auto-generated method stub
                 // Locate the textviews in activity_main.xml
                 Log.i("scavenger Hunt", "in text portion " + "<" + gamelist +">");
                 TextView tvGameName = (TextView) findViewById(R.id.tvGameName);
                 TextView tvGameId = (TextView) findViewById(R.id.tvGameId);
                 

                 // Set the text followed by the position
                 tvGameName.setText("Game Name : "
                         + gameSearch.get(position).getTitle());
                 tvGameId.setText("ObjectId : "
                         + gameSearch.get(position).getObjectId());
                 notificationGameId = gameSearch.get(position).getObjectId();
                 
             }

             @Override
             public void onNothingSelected(AdapterView<?> arg0) {
                 // TODO Auto-generated method stub
             }
         });
     }   
   //   }
 
     public void addListenerOnButton() {
         
       spinner = (Spinner) findViewById(R.id.spinner);
       
         btnSubmit = (Button) findViewById(R.id.btnSubmit);
      
         btnSubmit.setOnClickListener(new OnClickListener() {
      
           @Override
           public void onClick(View v) {
      
             Toast.makeText(PlayerList.this,
             "OnClickListener : " + 
                     "\nSpinner  : "+ String.valueOf(spinner.getSelectedItem())
                     ,
                 Toast.LENGTH_SHORT).show();
             
             Log.i("scavenger Hunt", "getting spinner item" + "<" + String.valueOf(spinner.getSelectedItem()) + ">");
            
           }
      
         });}
     
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
                sendNotification();
                }
        });
         
         }
                private void sendNotification() {
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
                          parsePush.sendMessageInBackground("Check out the new game " + String.valueOf(spinner.getSelectedItem()) + "  that is starting soon!!", pQuery); 
                          Log.i("scavenger Hunt", "push sent to" + "<" + player.getUsername() + ">");
                          ParseObject notification = new ParseObject("notification");
                          Log.i("scavenger Hunt", "in notigiacation for parse -game id"+ "<" + gameId + ">" );
                        //  notification.put("gameId",  gameId);
                          notification.put("gameId", notificationGameId);
                          Log.i("scavenger Hunt", "in notigiacation for parse -currentueser"+ "<" + currentUser.getObjectId() + ">" );
                          notification.put("createdUserId", currentUser.getObjectId());
                          Log.i("scavenger Hunt", "in notigiacation for parse -currentueser"+ "<" + player.getObjectId() + ">" );
                          
                          notification.put("invitedUserId", player.getObjectId());
                          notification.put("sentNotification", true);
                          notification.saveInBackground();
                          finish();
                       
                        }
                      
                    
                }}              
       
            
    //    }
        
    
    }




      
    
        
   
    
    


    
            
            
            
         
   
    


    
   
    


   

 
    
    
    
    
    


