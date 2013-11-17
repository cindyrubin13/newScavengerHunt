package com.example.scavengerhunt;

import java.util.ArrayList;
import java.util.List;

import com.example.scavengerhunt.PlayAGameListViewAdapter.ViewHolder;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

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

public class EditExistingGameAdapter extends BaseAdapter{

 // Declare Variables
    Context context;
    LayoutInflater inflater;
   
    private List<Game> gamelist = null;
    private ArrayList<Game> arraylist;
   
    
    public EditExistingGameAdapter(Context context,
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
        Button editButton;
       
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
            view = inflater.inflate(R.layout.listview_editgame, null);
            // Locate the TextViews in listview_item.xml
            holder.title = (TextView) view.findViewById(R.id.title);
            holder.objectid = (TextView) view.findViewById(R.id.gameid);
            holder.editButton = (Button) view.findViewById(R.id.editButton);
            
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        
     // Set the results into TextViews
        holder.title.setText(gamelist.get(position).getTitle());
        holder.objectid.setText(gamelist.get(position).getObjectId());
     // add code for play button
        holder.editButton.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Log.i("play Button Clicked", "edit game button");
                Intent intent = new Intent(context, EditAGameActivity.class);
                String editGameId = (String) gamelist.get(position).getObjectId();
                String editGameName = (String) gamelist.get(position).getTitle();
                Log.i("ScavengerHuntActivity", "in listview single play game object ID" + editGameId);             
                Log.i("ScavengerHuntActivity", "in listview single play game name" + editGameName);
                intent.putExtra("GameId", editGameId);
                intent.putExtra("GameName", editGameName);
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
                intent.putExtra("editGameName",
                        (gamelist.get(position).getTitle()));
                
                // Start SingleGameView Class
                context.startActivity(intent);
            }
        });
        return view;
    }
 
}
       
        
 
