package com.example.scavengerhunt;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData.Item;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;




import com.parse.ParseException;
import com.parse.ParseFacebookUtils.Permissions.User;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class NewGameList extends Activity  {
    
   
    ListView listview;  
    
    
    String gameId;
    String itemId;
    String itemName;
    Integer k; 
    List<ParseObject> ob;     
    ProgressDialog mProgressDialog;     
    
    ListViewAdapter adapter;
    private List<Items> itemlist;
    // end of new code
    private Button mainMenu;
    private Button editButton;
   
    List<String> items = new ArrayList<String>();
    
   
    
   
    
    @Override    
    public void onCreate(Bundle savedInstanceState) {         
        super.onCreate(savedInstanceState);         
        // Get the view from listview_main.xml         
        setContentView(R.layout.indoor);         
        // Execute RemoteDataTask AsyncTask         
        new RemoteDataTask().execute(); 
        // adding code for checkbox
        // add main menu onclicklistener
       
        
        mainMenu = (Button) findViewById(R.id.mainMenuButton);
        mainMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(nextScreen);
                
            }
        });
        
        
        } 
 
    
    
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {         
        private final String ItemId = null;
        private  final String ItemName = null;
        private  OnClickListener OnClickListener = null;
        private Context context;

        @Override        
        protected void onPreExecute() {            
            super.onPreExecute();             
            // Create a progressdialog            
            mProgressDialog = new ProgressDialog(NewGameList.this);             
            // Set progressdialog title             
            mProgressDialog.setTitle("Retrieving List of Items");             
            // Set progressdialog message             
            mProgressDialog.setMessage("Loading...");             
            mProgressDialog.setIndeterminate(false);             
            // Show progressdialog            
            mProgressDialog.show();         
            } 
   
        @Override        
        protected Void doInBackground(Void... params) {             
                      
           //new code for array list
            itemlist = new ArrayList<Items>();
            //end of ne code
            
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("newItems");  
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                gameId = extras.getString("gameId");
            }
            Log.i("newGameList", "after query ");
                        
            try {                 
                
                query.whereEqualTo("gameId", gameId);
                ob =  query.find();
                Log.i("NewGameList", "after find " + gameId);
                // new code for query 
                for (ParseObject gameItems : ob) {
                   Items map = new Items();
               
                   map.itemId = gameItems.getObjectId();
                 
                   map.itemName = (String) gameItems.get("item1");
                   itemlist.add(map);
                   Log.i("ScavengerHuntActivity", "items after load"  + map);
                    
                }
                // end of new code
                } 
            catch (ParseException e) {                 
                Log.e("Error", e.getMessage());                 
                e.printStackTrace();             
                }             
            return null;        
            } 
       
        @Override       
        protected void onPostExecute(Void result) {             
            // Locate the listview in listview_main.xml             
            listview = (ListView) findViewById(R.id.listview);             
            
            //new code
            Log.i("ScavengerHuntActivity", "items in post executte"  + itemlist);
            adapter = new ListViewAdapter(NewGameList.this,itemlist);
            listview.setAdapter(adapter);
            //end of new code
            // Retrieve object "name" from Parse.com database            
            
           
            // Close the progressdialog    
           
            mProgressDialog.dismiss(); 
            
            //new code for editbuttom end of code
         
       
           listview.setOnItemClickListener(new OnItemClickListener() {                 
                @Override               
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) { 
                   
                            
                    Intent i = new Intent(NewGameList.this,  SingleItemView.class);                     
                    // Pass data "name" followed by the position
            
                    String item2 = (String) listview.getItemAtPosition(position);
                    
            i.putExtra("item1", item2);                  
                   Log.i("ScavengerHuntActivity", "in listview single item click " + item2);             
                startActivity(i);                            
                    }             
                }); 
   
       }
    
        
     }
            
}


    
            
            
            
         
   
    


    
   
    


   


      
                    
                       
                
        
    
    
   