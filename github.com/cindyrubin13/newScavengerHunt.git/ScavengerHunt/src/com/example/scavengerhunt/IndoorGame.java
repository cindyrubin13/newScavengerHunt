package com.example.scavengerhunt;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData.Item;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class IndoorGame extends Activity{ 
 
    ListView listview;     
    List<ParseObject> ob;     
    ProgressDialog mProgressDialog;     
    ArrayAdapter<String> adapter; 
    @Override    
    public void onCreate(Bundle savedInstanceState) {         
        super.onCreate(savedInstanceState);         
        // Get the view from listview_main.xml         
        setContentView(R.layout.indoor);         
        // Execute RemoteDataTask AsyncTask         
        new RemoteDataTask().execute();     
        } 
    
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {         
        @Override        
        protected void onPreExecute() {            
            super.onPreExecute();             
            // Create a progressdialog            
            mProgressDialog = new ProgressDialog(IndoorGame.this);             
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
            // Locate the class table named "Country" in Parse.com             
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("items");   
            Log.i("IndoorGame", "after query ");
           // query.orderByDescending("_created_at");             
            try {                 
                ob = query.find();  
                Log.i("IndoorGame", "after find ");
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
            // Pass the results into an ArrayAdapter             
            adapter = new ArrayAdapter<String>(IndoorGame.this,R.layout.listview_item, R.id.textItemName);             
            // Retrieve object "name" from Parse.com database            
            for (ParseObject desc : ob) {                 
                adapter.add((String) desc.get("description"));             
                }             
            // Binds the Adapter to the ListView             
            listview.setAdapter(adapter);             
            // Close the progressdialog            
            mProgressDialog.dismiss(); 
         //
            listview.setOnItemClickListener(new OnItemClickListener() {                 
                @Override               
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {                    
                    // Send single item click data to SingleItemView Class                     
                    Intent i = new Intent(IndoorGame.this,  SingleItemView.class);                     
                    // Pass data "name" followed by the position                    
                    i.putExtra("description", ob.get(position).getString("description").toString());                    
                    // Open SingleItemView.java Activity                     
                    startActivity(i);                 
                    }             
                }); 
                }
            }
    
            
    }
   
    


   
  
         
                    
    
   

               
   
           
           
   
            
                 
              
                
               
     



      
            		
            		   
            	
        
    
    
