package com.example.scavengerhunt;

import java.util.ArrayList;
import java.util.List;

import com.example.scavengerhunt.NotificationListViewAdapter.ViewHolder;

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

public class NotificationListViewAdapter extends BaseAdapter{

    // Declare Variables
    Context context;
    LayoutInflater inflater;
   
    private List<NotificationGame> notificationlist = null;
    private ArrayList<NotificationGame> arraylist;
   
    
    public NotificationListViewAdapter(Context context,
            List<NotificationGame> notificationlist) {
        this.context = context;
        this.notificationlist = notificationlist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<NotificationGame>();
        this.arraylist.addAll(notificationlist);
       
    }
 
    public class ViewHolder {
        TextView title;
        TextView objectid;
        Button playButton;
        Button rejectButton;
       
    }
   
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return notificationlist.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return notificationlist.get(position);
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
            view = inflater.inflate(R.layout.listview_notification, null);
            // Locate the TextViews in listview_item.xml
            holder.title = (TextView) view.findViewById(R.id.title);
            holder.objectid = (TextView) view.findViewById(R.id.gameid);
            holder.playButton = (Button) view.findViewById(R.id.playButton);
            holder.rejectButton = (Button) view.findViewById(R.id.rejectButton);
            
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        
     // Set the results into TextViews
        holder.title.setText(notificationlist.get(position).getTitle());
        holder.objectid.setText(notificationlist.get(position).getGameId());
     // add code for play button
        holder.playButton.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Log.i("play Button Clicked", "play button");
                Intent intent = new Intent(context, PlayAGame.class);
                String notificationGameId = (String) notificationlist.get(position).getGameId();
                String notificationGameName = (String) notificationlist.get(position).getTitle();
                Log.i("ScavengerHuntActivity", "in listview single play game object ID" + notificationGameId);             
                Log.i("ScavengerHuntActivity", "in listview single play game name" + notificationGameName);
                intent.putExtra("GameId", notificationGameId);
                intent.putExtra("GameName", notificationGameName);
                context.startActivity(intent);          
                
            }
            
            
        });
        holder.rejectButton.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Log.i("reject Button Clicked", "reject button");
                holder.playButton.setEnabled(false);
            //    Intent intent = new Intent(context, InvitedGame.class);
               /* String notificationGameId = (String) notificationlist.get(position).getObjectId();
                String notificationGameName = (String) notificationlist.get(position).getTitle();
                Log.i("ScavengerHuntActivity", "in listview single play game object ID" + notificationGameId);             
                Log.i("ScavengerHuntActivity", "in listview single play game name" + notificationGameName);
                intent.putExtra("GameId", notificationGameId);
                intent.putExtra("GameName", notificationGameName);*/
             //   context.startActivity(intent);          
                
            }
            
            
        });
        
        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, SingleGameView.class);
                // Pass all data rank
                intent.putExtra("playGameName",
                        (notificationlist.get(position).getTitle()));
                
                // Start SingleGameView Class
                context.startActivity(intent);
            }
        });
        return view;
    }
 
}
       
        
 
