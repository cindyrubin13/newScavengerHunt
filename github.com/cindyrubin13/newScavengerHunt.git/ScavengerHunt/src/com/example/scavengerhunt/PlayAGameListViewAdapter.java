package com.example.scavengerhunt;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scavengerhunt.PlayerListViewAdapter.ViewHolder;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class PlayAGameListViewAdapter extends BaseAdapter {
 // Declare Variables
    Context context;
    LayoutInflater inflater;
   
    private List<GameItem> itemlist = null;
    private ArrayList<GameItem> arraylist;
    ParseUser currentUser;
    private String currentUserObjectId;
    private String gameId;
    ParseObject object;
    private String gameObjectId;
    
    public PlayAGameListViewAdapter(Context context,
            List<GameItem> itemlist) {
        this.context = context;
        this.itemlist = itemlist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<GameItem>();
        this.arraylist.addAll(itemlist);
       
    }
 
    public class ViewHolder {
        TextView gameItemId;
        TextView gameItemName;
        Button foundItButton;
        
       
    }
    
    
    
    
    
    
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return itemlist.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return itemlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_gameitem, null);
            currentUser = ParseUser.getCurrentUser();
            currentUserObjectId = currentUser.getObjectId();
            Log.i("main menu activity", "getting current user info " + currentUser.getObjectId());
            // Locate the TextViews in listview_item.xml
            holder.gameItemId = (TextView) view.findViewById(R.id.textItemId);
            holder.gameItemName = (TextView) view.findViewById(R.id.textItemName);
            holder.foundItButton = (Button) view.findViewById(R.id.foundItButton);
            
            
            
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        
     // Set the results into TextViews
        holder.gameItemId.setText(itemlist.get(position).getObjectId());
        holder.gameItemName.setText(itemlist.get(position).getGameItemName());
        
        SetupButtons(holder);
        final String itemId = (String) itemlist.get(position).getObjectId();
         gameObjectId = (String) itemlist.get(position).getGameObjectId();
        
        holder.foundItButton.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Log.i("foundIt Button Clicked", "foundit button");
                Log.i("foundIt Button Clicked", "item id" + itemId);
                // query to get gameid
               
                final ParseObject foundItems =  new ParseObject("foundItems");
                foundItems.put("itemObjectId", itemId);
                foundItems.put("gameId", gameObjectId);
                foundItems.put("userInfo", currentUserObjectId);
               // foundItems.saveInBackground();
                ParseQuery<ParseObject> query = ParseQuery.getQuery("foundItems");
                query.whereEqualTo("itemObjectId", itemId);
                query.whereEqualTo("gameId", gameObjectId);
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                  public void done(ParseObject object, ParseException e) {
                    if (object == null) {
                       Toast.makeText(context, "You are the first to find the item", Toast.LENGTH_LONG).show();
                       
                      Log.d("score", "The getFirst request failed.");
                    } else {
                        String userId = object.getString("userInfo");
                        
                        ParseQuery<ParseUser> userQuery = ParseUser.getQuery();
                        userQuery.whereEqualTo("objectId", userId);
                        userQuery.getFirstInBackground(new GetCallback<ParseUser>() {
                            public void done(ParseUser object, ParseException e) {
                                if (object != null) {
                                    String userName= object.getUsername();
                                    Toast.makeText(context, userName + " beat you to this item", Toast.LENGTH_LONG).show();
                     
                                }
                            }
                        });
                    }
                    foundItems.saveInBackground();
                    itemlist.get(position).isFound = true;
                    
                    boolean candy = true;
                    for(int i=0;i<itemlist.size() && candy;i++)
                    {
                        candy &= itemlist.get(i).isFound;
                    }
                    if(candy)
                    {
                        Toast.makeText(context, "Congradulations you won!!!", Toast.LENGTH_LONG).show();
                    }
                  }
                });
                holder.foundItButton.setEnabled(false);
                holder.foundItButton.setText("Got It!");
                
                
                
            }});
        return view;      
       
    }    
    
    private void SetupButtons(final ViewHolder individualHolder) {
        
        
        ParseQuery<ParseObject> query = ParseQuery.getQuery("foundItems");
        query.whereEqualTo("userInfo", currentUser.getObjectId());
        query.whereEqualTo("itemObjectId", individualHolder.gameItemId.getText());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object != null) {
                    individualHolder.foundItButton.setEnabled(false);
                    individualHolder.foundItButton.setText("Got It!");
                }
            }
        });
    }
 
}
       
        
  
