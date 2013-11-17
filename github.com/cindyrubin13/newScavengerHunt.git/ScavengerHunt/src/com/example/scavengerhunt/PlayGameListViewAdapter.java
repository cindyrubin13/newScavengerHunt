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

import com.example.scavengerhunt.PlayerListViewAdapter.ViewHolder;

public class PlayGameListViewAdapter extends BaseAdapter {
 // Declare Variables
    Context context;
    LayoutInflater inflater;
   
    private List<Game> gamelist = null;
    private ArrayList<Game> arraylist;
   
    
    public PlayGameListViewAdapter(Context context,
            List<Game> gamelist) {
        this.context = context;
        this.gamelist = gamelist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<Game>();
        this.arraylist.addAll(gamelist);
       
    }
 
    public class ViewHolder {
        TextView title;
        TextView objectid;
        Button playButton;
       
    }
   
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return gamelist.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return gamelist.get(position);
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
            view = inflater.inflate(R.layout.listview_game, null);
            // Locate the TextViews in listview_item.xml
            holder.title = (TextView) view.findViewById(R.id.title);
            holder.objectid = (TextView) view.findViewById(R.id.gameid);
            holder.playButton = (Button) view.findViewById(R.id.playButton);
            
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        
     // Set the results into TextViews
        holder.title.setText(gamelist.get(position).getTitle());
        holder.objectid.setText(gamelist.get(position).getObjectId());
     // add code for play button
        holder.playButton.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Log.i("play Button Clicked", "play button");
                Intent intent = new Intent(context, PlayAGame.class);
                String playGameId = (String) gamelist.get(position).getObjectId();
                String playGameName = (String) gamelist.get(position).getTitle();
                Log.i("ScavengerHuntActivity", "in listview single play game object ID" + playGameId);             
                Log.i("ScavengerHuntActivity", "in listview single play game name" + playGameName);
                intent.putExtra("GameId", playGameId);
                intent.putExtra("GameName", playGameName);
                context.startActivity(intent);          
                
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
                        (gamelist.get(position).getTitle()));
                
                // Start SingleGameView Class
                context.startActivity(intent);
            }
        });
        return view;
    }
 
}
       
        
 
