package com.example.scavengerhunt;


    import java.util.ArrayList;
import java.util.Date;
import java.util.List;

    import com.parse.FindCallback;
import com.parse.GetCallback;
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

    public class InvitedGame  extends Activity {
        
     // Declare Variables
        ListView listview;
        List<ParseObject> obj;
        ProgressDialog mProgressDialog;
        NotificationListViewAdapter adapter;
        private List<NotificationGame> notificationlist = null;
        Date historyDate;
        Date futureDate;
        Context context;
        String currentUser;
        String gameObjectId;
        
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Get the view from listview_main.xml
            setContentView(R.layout.notification_listview);
            // Execute RemoteDataTask AsyncTask
         //   new RemoteDataTask().execute();
     //   }
       // RemoteDataTask AsyncTask
     /*   private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Create a progressdialog
                mProgressDialog = new ProgressDialog(InvitedGame.this);
                // Set progressdialog title
                mProgressDialog.setTitle("Loading Lists of Invited Games");
                // Set progressdialog message
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.setIndeterminate(false);
                // Show progressdialog
                mProgressDialog.show();
           }*/

        //    @Override
          //  protected Void doInBackground(Void... params) {
              
             // Create the array
                final Date myDate = new Date();
                currentUser = ParseUser.getCurrentUser().getObjectId();
                notificationlist = new ArrayList<NotificationGame>();
                Log.i("ScavengerHuntActivity", "in do in background after arraylist" );
                ParseQuery<ParseObject> query = ParseQuery.getQuery("notification");
                query.whereEqualTo("invitedUserId", currentUser);
                    Log.i("scavenger Hunt", "in parse query" + currentUser );
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> objects, ParseException e) {
                        Log.i("scavenger Hunt", "in parse query2 getting list of games" );
                            if (e == null) {
                                Log.d("score", "Retrieved " + objects.size() + " games");
                                
                                for (ParseObject obj : objects) {
                                    gameObjectId = obj.getString("gameId");
                                    Log.i("scavenger ", "need to see object id" +  "<" + gameObjectId + ">");
                                    ParseQuery<ParseObject> query = ParseQuery.getQuery("newGame");
                                    query.whereEqualTo("objectId", gameObjectId);
                                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                                        public void done(ParseObject object, ParseException e) {
                                          if (object == null) {
                                            Log.d("Game", "The game had an invalid objectid and wasn't found");
                                            Toast.makeText(getApplicationContext(),  " Sorry, There are no games available at this time! Try back later!!", Toast.LENGTH_LONG).show(); 
                                            finish();

                                          } else {
                                            Log.d("Viola", "There is a game");
                                            historyDate = object.getDate("beginDate");
                                            futureDate = object.getDate("endDate");
                                            if (myDate.after(historyDate) && myDate.before(futureDate)){
                                            NotificationGame ng = new NotificationGame();
                                            ng.setGameId(gameObjectId);
                                            ng.setTitle(object.getString("title"));
                                            notificationlist.add(ng);
                                            listview = (ListView) findViewById(R.id.listview);
                                            // Pass the results into ListViewAdapter.java
                                            adapter = new NotificationListViewAdapter(InvitedGame.this, notificationlist);
                                            // Binds the Adapter to the ListView
                                            listview.setAdapter(adapter);
                                            }
                                          }
                                         
                                        }
                                        
                                      });
                                   
                                    }  
                                  
                                }
                   
                        }

                    });
              //      return null;
                    }       
        }
   // }






            
                       





/*
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
                                    Log.i("ScavengerHuntActivity", "games in post executte for loop"  + gamelist);*/
