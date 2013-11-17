package com.example.scavengerhunt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class EditExistingGameActivity extends Activity {
 // Declare Variables
    ListView listview;
    List<ParseObject> obj;
    ProgressDialog mProgressDialog;
    EditExistingGameAdapter adapter;
    private List<Game> gamelist = null;
    
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
            mProgressDialog = new ProgressDialog(EditExistingGameActivity.this);
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
               query.whereGreaterThanOrEqualTo("beginDate", myDate);
                Log.i("scavenger Hunt", "in parse query" + myDate );
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> objects, ParseException e) {
                    Log.i("scavenger Hunt", "in parse query2" );
                        if (e == null) {
                            Log.d("score", "Retrieved " + objects.size() + " scores");
                            for (ParseObject obj : objects) {
                                Game game = new Game();
                                game.setTitle(obj.getString("title"));
                                game.setObjectId (obj.getObjectId());
                                Log.i("scavenger Hunt", "getting title" + "<" + game.getTitle() + ">");       
                                
                                Log.i("scavenger Hunt", "getting objectid" + "<" + game.getObjectId() + ">");
                                gamelist.add(game);
                                Log.i("scavenger Hunt", "getting gamelist" + "<" + gamelist + ">");
                                Log.i("ScavengerHuntActivity", "games in post executte for loop"  + gamelist);
                                listview = (ListView) findViewById(R.id.listview);
                                // Pass the results into ListViewAdapter.java
                                adapter = new EditExistingGameAdapter(EditExistingGameActivity.this, gamelist);
                                // Binds the Adapter to the ListView
                                listview.setAdapter(adapter);
                             // Close the progressdialog
                                mProgressDialog.dismiss();
                                }  
                           // }
                        } else {
                            Log.d("scavenger hunt", "Error: " + e.getMessage());
                        }
                    }

     
                });
                return null;
                }       
    }
}






        
                   

