package com.example.scavengerhunt;

import java.util.ArrayList;
import java.util.Date;
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
import com.parse.SaveCallback;

public class PlayAGameListViewAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    private List<GameItem> itemlist;
    private ArrayList<GameItem> arraylist;
    ParseUser currentUser;
    private String currentUserObjectId;
    private String gameId;
    ParseObject object;
    String gameObjectId;
    
    public PlayAGameListViewAdapter(Context context,
            List<GameItem> itemlist) {
        this.context = context;
        this.itemlist = itemlist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<GameItem>();
     //   this.arraylist.addAll(itemlist);
       
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
                final ParseObject foundItems =  new ParseObject("foundItems");
                foundItems.put("itemObjectId", itemId);
                foundItems.put("gameId", gameObjectId);
                foundItems.put("userInfo", currentUserObjectId);
                foundItems.saveInBackground(new SaveCallback(){
                    public void done(ParseException e) {
                        if (e== null) {   
                            Log.i("ParseStarterProjectActivity", "saved successfulyy after save in background " );
                        
                // end of new code
               
                
               /* try
                {
                    foundItems.save();
                }
                catch(ParseException exc)
                {
                    Log.e("Scavenger hunt", "failed to save", exc);
                }*/
                checkFoundItemsFirst();            
              //  ParseQuery<ParseObject> query = ParseQuery.getQuery("foundItems");
              //  query.whereEqualTo("itemObjectId", itemId);
              //  query.whereEqualTo("gameId", gameObjectId);
             //   query.findInBackground(new FindCallback<ParseObject>() {
              //    public void done(List<ParseObject> objects, ParseException e) {
              //        Date firstDate = objects.get(0).getCreatedAt();
               //       String foundUserId = objects.get(0).getString("userInfo");
               //       for(ParseObject o : objects)
                //      {
               //           if(o.getCreatedAt().before(firstDate))
                //          {
                 //             firstDate = o.getCreatedAt();
                 //             foundUserId = o.get("userInfo").toString();
                 //         }
                 //     }
                //      
                //      if(foundUserId.equals(currentUserObjectId))
                //      {
                 //         Toast.makeText(context, "You are the first to find the item", Toast.LENGTH_LONG).show();
                //          Log.d("score", "The getFirst request failed.");
                 //   } else {
                     
                   //     ParseQuery<ParseUser> userQuery = ParseUser.getQuery();
                   //     userQuery.whereEqualTo("objectId", foundUserId);
                  //      userQuery.getFirstInBackground(new GetCallback<ParseUser>() {
                   //         public void done(ParseUser object, ParseException e) {
                     //           if (object != null) {
                     //               String userName= object.getUsername();
                     //               Toast.makeText(context, userName + " beat you to this item", Toast.LENGTH_LONG).show();
                    // 
                     //           }
                    //        }
                    //    });
                  //  }
                    
                  //  itemlist.get(position).isFound = true;
                  //  if(allFound(itemlist))
                 //           Toast.makeText(context, "Congratulations you won!!!", Toast.LENGTH_LONG).show();
               //   }
            //    });
            //    holder.foundItButton.setEnabled(false);
            //    holder.foundItButton.setText("Got It!");
               //// else goes here  
                
                        }
                        else{
                            Log.e("ParseStarterProjectActivity", "in else portion save was no good");
                        }
                    }

                    private void checkFoundItemsFirst() {
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("foundItems");
                        query.whereEqualTo("itemObjectId", itemId);
                        query.whereEqualTo("gameId", gameObjectId);
                        query.findInBackground(new FindCallback<ParseObject>() {
                          public void done(List<ParseObject> objects, ParseException e) {
                              Date firstDate = objects.get(0).getCreatedAt();
                              String foundUserId = objects.get(0).getString("userInfo");
                              for(ParseObject o : objects)
                              {
                                  if(o.getCreatedAt().before(firstDate))
                                  {
                                      firstDate = o.getCreatedAt();
                                      foundUserId = o.get("userInfo").toString();
                                  }
                              }
                              
                              if(foundUserId.equals(currentUserObjectId))
                              {
                                  Toast.makeText(context, "You are the first to find the item", Toast.LENGTH_LONG).show();
                                  Log.d("score", "The getFirst request failed.");
                            } else {
                                whoBeatThisPerson(foundUserId);
                            //    ParseQuery<ParseUser> userQuery = ParseUser.getQuery();
                            //    userQuery.whereEqualTo("objectId", foundUserId);
                             //   userQuery.getFirstInBackground(new GetCallback<ParseUser>() {
                            //        public void done(ParseUser object, ParseException e) {
                           //             if (object != null) {
                          //                  String userName= object.getUsername();
                          //                  Toast.makeText(context, userName + " beat you to this item", Toast.LENGTH_LONG).show();
                          //   
                           //             }
                          //          }
                         //       });
                            }
                            
                            itemlist.get(position).isFound = true;
                            if(allFound(itemlist))
                                    Toast.makeText(context, "Congratulations you won!!!", Toast.LENGTH_LONG).show();
                          }

                        private void whoBeatThisPerson(String foundUserId) {
                            // TODO Auto-generated method stub
                            ParseQuery<ParseUser> userQuery = ParseUser.getQuery();
                            userQuery.whereEqualTo("objectId", foundUserId);
                            userQuery.getFirstInBackground(new GetCallback<ParseUser>() {
                                public void done(ParseUser object, ParseException e) {
                                    if (object != null) {
                                        String userName= object.getUsername();
                                        Toast.makeText(context, userName + " beat you to this item", Toast.LENGTH_LONG).show();
                         
                                    }
                                }
                            });
                            
                        }

                        
                        });
                        holder.foundItButton.setEnabled(false);
                        holder.foundItButton.setText("Got It!");
                       //// else goes here  
                        
                        
                    }
                });
              
                
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
 
    private static boolean allFound(List<GameItem> theItems)
    {
        for(GameItem gi : theItems)
        {
            if(!gi.isFound)
                return false;
        }
        return true;
    }
}
       
        
  
