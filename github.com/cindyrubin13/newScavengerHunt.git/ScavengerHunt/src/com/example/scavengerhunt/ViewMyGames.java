package com.example.scavengerhunt;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ViewMyGames extends Activity {
    // Declare variables
    ListView listview;
    List<ParseObject> ob;
    ViewMyGamesListViewAdapter adapter;
    private List<MyGame> mygamelist = null;
    private ParseUser currentUser;
    private String currentUserObjectId;
    private Button mainMenuButton;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get the view from listview_mygames
        setContentView(R.layout.mygameslistview_main);
        currentUser = ParseUser.getCurrentUser();
        currentUserObjectId = currentUser.getObjectId();
        viewMyGames();
        mainMenuButton = (Button) findViewById(R.id.mainMenuButton);
        mainMenuButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(nextScreen);
                
            }
        });
        
    }
    
    private void viewMyGames() {
        mygamelist = new ArrayList<MyGame>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("newGame");
        query.whereEqualTo("userObjectId", currentUserObjectId);
        //  query.whereNotEqualTo("beginDate", myDate);
          Log.i("scavenger Hunt", "in parse query"  + currentUserObjectId);
          query.findInBackground(new FindCallback<ParseObject>() {
              public void done(List<ParseObject> objects, ParseException e) {
              Log.i("scavenger Hunt", "in parse query2 getting  games" );
                  if (e == null) {
                      Log.d("games", "Retrieved " + objects.size() + " games");
                      
                          for (ParseObject obj : objects) {
                              MyGame game = new MyGame();
                              game.setObjectId (obj.getObjectId());
                              game.setTitle(obj.getString("title"));
                              
                              Log.i("scavenger Hunt", "getting title" + "<" + game.getObjectId() + ">");
                              Log.i("scavenger Hunt", "getting title" + "<" + game.getTitle() + ">"); 
                              mygamelist.add(game);
                              Log.i("scavenger Hunt", "getting gamelist" + "<" + mygamelist + ">");
                              listview = (ListView) findViewById(R.id.mygameslistview);
                              // Pass the results into ListViewAdapter.java
                              adapter = new ViewMyGamesListViewAdapter(ViewMyGames.this, mygamelist);
                              // Binds the Adapter to the ListView
                              listview.setAdapter(adapter);
                          }
                  }
                  }});      
          }            
    }
