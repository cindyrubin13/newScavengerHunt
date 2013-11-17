package com.example.scavengerhunt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
//import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

public class PlayGame extends Activity {
    
 // Declare Variables
    ListView listview;
    List<ParseObject> obj;
    ProgressDialog mProgressDialog;
    PlayGameListViewAdapter adapter;
    private List<Game> gamelist = null;
    Date historyDate;
    Date futureDate;
    Context context;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
        setContentView(R.layout.gamelistview_main);
        // Execute RemoteDataTask AsyncTask
        new RemoteDataTask().execute();
    }
   // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(PlayGame.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Loading Lists of Games");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
       }

        @Override
        protected Void doInBackground(Void... params) {
          
         // Create the array
            final Date myDate = new Date();
            
            gamelist = new ArrayList<Game>();
            Log.i("ScavengerHuntActivity", "in do in background after arraylist" );
            ParseQuery<ParseObject> query = ParseQuery.getQuery("newGame");
           //    query.whereGreaterThanOrEqualTo("beginDate", myDate);
                Log.i("scavenger Hunt", "in parse query" + myDate );
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> objects, ParseException e) {
                    Log.i("scavenger Hunt", "in parse query2" );
                        if (e == null) {
                            Log.d("score", "Retrieved " + objects.size() + " scores");
                            Log.i("scavenger Hunt", "getting gamelist" + "<" + gamelist + ">");
                            for (ParseObject obj : objects) {
                                Log.i("scavenger Hunt", "getting obj" + "< in loop>");   
                                historyDate = obj.getDate("beginDate");
                                futureDate = obj.getDate("endDate");
                                Log.i("scavenger Hunt", "getting history date" + "<" + historyDate + ">");       
                                Log.i("scavenger Hunt", "getting future date" + "<" + futureDate + ">"); 
                                Log.i("scavenger Hunt", "getting obj" + "< before if>"); 
                                
                             //  if (game.setBeginDate(obj.getDate("beginDate")).after(myDate)){
                             if (myDate.after(historyDate) && myDate.before(futureDate)){
                                 Game game = new Game();
                                 game.setTitle(obj.getString("title"));
                                 game.setObjectId (obj.getObjectId());
                                Log.i("scavenger Hunt", "getting title" + "<" + game.getTitle() + ">");   
                                Log.i("scavenger Hunt", "getting history date" + "<" + historyDate + ">");       
                                Log.i("scavenger Hunt", "getting history date" + "<" + futureDate + ">"); 
                                Log.i("scavenger Hunt", "getting objectid" + "<" + game.getObjectId() + ">");
                                gamelist.add(game);
                                Log.i("scavenger Hunt", "getting gamelist" + "<" + gamelist + ">");
                                Log.i("ScavengerHuntActivity", "games in post executte for loop"  + gamelist);
                                listview = (ListView) findViewById(R.id.listview);
                                // Pass the results into ListViewAdapter.java
                                adapter = new PlayGameListViewAdapter(PlayGame.this, gamelist);
                                // Binds the Adapter to the ListView
                                listview.setAdapter(adapter);
                             // Close the progressdialog
                                mProgressDialog.dismiss();
                                }  
                              
                            }
                          
                            
                            mProgressDialog.dismiss();
                            Log.i("scavenger Hunt", "before second if after dismiss" + "< after dismiss>");
                            if (gamelist.isEmpty()) {
                                Log.i("scavenger Hunt", "inside second if with  " + "< empty hopefully>");
                                mProgressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(),  " Sorry, There are no games available at this time! Try back later!!", Toast.LENGTH_LONG).show(); 
                                    finish();
                               }
                            
                        } else {
                            Log.d("scavenger hunt", "Error: " + e.getMessage());
                        }
                    }

     
                });
                return null;
                }       
    }
}






        
                   

