package com.example.scavengerhunt;

import java.util.ArrayList;
import java.util.List;

import com.example.scavengerhunt.PlayGameListViewAdapter.ViewHolder;

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

public class ViewMyGamesListViewAdapter extends BaseAdapter {
 // Declare Variables
    Context context;
    LayoutInflater inflater;
   
    private List<MyGame> mygamelist = null;
    private ArrayList<MyGame> arraylist;
   
    
    public ViewMyGamesListViewAdapter(Context context, List<MyGame> mygamelist) {
        this.context = context;
        this.mygamelist = mygamelist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<MyGame>();
        this.arraylist.addAll(mygamelist);
       
    }
    public class ViewHolder {
        TextView title;
        TextView objectId;
        Button editButton;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mygamelist.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mygamelist.get(position);
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
            view = inflater.inflate(R.layout.listview_mygames, null);
            // Locate the TextViews in listview_item.xml
            holder.title = (TextView) view.findViewById(R.id.textgametitle);
            holder.objectId = (TextView) view.findViewById(R.id.textgameobjectid);
            holder.editButton = (Button) view.findViewById(R.id.editButton);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        
     // Set the results into TextViews
        holder.title.setText(mygamelist.get(position).getTitle());
        holder.objectId.setText(mygamelist.get(position).getObjectId());
     // add code for edit button
        holder.editButton.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Log.i("edit Button Clicked", "play button");
                Intent intent = new Intent(context, EditAGameActivity.class);
                String myGameId = (String) mygamelist.get(position).getObjectId();
                String myGameName = (String)mygamelist.get(position).getTitle();
                Log.i("ScavengerHuntActivity", "in listview single play game object ID" + myGameId);             
                Log.i("ScavengerHuntActivity", "in listview single play game name" + myGameName);
                intent.putExtra("GameId", myGameId);
                intent.putExtra("GameName", myGameName);
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
                        (mygamelist.get(position).getTitle()));
                
                // Start SingleGameView Class
                context.startActivity(intent);
            }
        });
        return view;
    }
 
}
       
        
 
 

          
  



