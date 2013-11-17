package com.example.scavengerhunt;

import java.util.ArrayList;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class PlayAGame extends Activity {
    
    PlayAGameListViewAdapter adapter;
    String objectId;
    String itemName;
    String gameId;
    String gameName;
    String gameObjectId;
    private List<GameItem> itemlist;
    List<ParseObject> objects;   
   
    
    ListView listview;  
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_list);
        Bundle extras = getIntent().getExtras();
        Log.i("ScavengerHuntActivity", "gameid value " + extras );
        if (extras != null) {
            
            gameId = extras.getString("GameId");
            gameName = extras.getString("GameName");
     
            Log.i("ScavengerHuntActivity", "gameid value itemslist 1" + gameId + "  " + gameName );
         }
        itemlist = new ArrayList<GameItem>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("newItems");
        query.whereEqualTo("gameId", gameId);
        //  query.whereNotEqualTo("beginDate", myDate);
          Log.i("scavenger Hunt", "in parse query"  + gameId);
          query.findInBackground(new FindCallback<ParseObject>() {
              public void done(List<ParseObject> objects, ParseException e) {
              Log.i("scavenger Hunt", "in parse query2 getting  items" );
                  if (e == null) {
                      Log.d("score", "Retrieved " + objects.size() + " items");
                      for (ParseObject gameItems : objects) {
                         
                      GameItem item = new GameItem();
                      item.objectId = gameItems.getObjectId();
                      item.gameItemName = (String) gameItems.get("item1");
                      item.gameObjectId = (String) gameItems.get("gameId");
                      Log.d("scavenger hunt", "Retrieved " + "<" + gameItems.getObjectId() + ">" );
                      Log.d("scavenger hunt", "Retrieved " + "<" + (String) gameItems.get("item1") + ">" );
                      itemlist.add(item);
                      Log.i("ScavengerHuntActivity", "items after load"  + item);
                      }
                      listview = (ListView) findViewById(R.id.listview);
                      adapter = new PlayAGameListViewAdapter(PlayAGame.this,itemlist);
                      
                      //new code
                      Log.i("ScavengerHuntActivity", "items in post executte"  + itemlist);
                     
                      listview.setAdapter(adapter);
                
                  }
              else {
                  Log.e("Error in if and for loop", e.getMessage());
                  // Something went wrong.
              }
            }
          });
    }
}
           
      
      