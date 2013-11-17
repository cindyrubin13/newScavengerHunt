package com.example.scavengerhunt;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParsePush;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerListViewAdapter extends BaseAdapter {
 // Declare Variables
    Context context;
    LayoutInflater inflater;
   
     List<Players> playerslist = null;
    private ArrayList<Players> arraylist;
    private CheckBox checkBox;
    public PlayerListViewAdapter(Context context,
             List<Players> playerslist) {
        this.context = context;
        this.playerslist = playerslist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<Players>();
        this.arraylist.addAll(playerslist);
       
    }
 
    public class ViewHolder {
        TextView username;
        TextView email;
        CheckBox checkBox;
        
       
    }
    
    
    
    
    
    
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return playerslist.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return playerslist.get(position);
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
            view = inflater.inflate(R.layout.listview_player, null);
            // Locate the TextViews in listview_item.xml
            holder.username = (TextView) view.findViewById(R.id.username);
         //   holder.email = (TextView) view.findViewById(R.id.email);
            holder.checkBox = (CheckBox)view.findViewById(R.id.playerCheckBox);
            
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        
     // Set the results into TextViews
     //   holder.username.setText(playerslist.get(position).getUsername());
     //   holder.email.setText(playerslist.get(position).getEmail());
      
        holder.checkBox.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                
                CheckBox cb = (CheckBox) v;
                Players player = (Players) cb.getTag();
                
               // if (((CheckBox) v).isChecked()) {
               // Players player = (Players) cb.getTag();    
                Toast.makeText(context, "A new Game Is Starting " + cb.getText() + "is" + cb.isChecked(), Toast.LENGTH_LONG).show();
                player.setSelected(cb.isChecked());
                //  String playerName = (String) playerslist.get(position).getUsername();
                
                 //  player.setSelected(cb.isChecked());
                    //    long weekInterval = 60*60*24*7; // 1 week
               //     ParsePush push = new ParsePush();
               //     push.setExpirationTimeInterval(weekInterval);
                   
                //    push.setMessage("There is a new game starting, please join us!!!");
                 //   push.sendInBackground();
              // } else {
               //     Log.i("ScavengerHuntActivity", "checkbox problem" );

                }
            });
       
        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                // Send single item click data to SinglePlayerView Class
                Intent intent = new Intent(context, SinglePlayerView.class);
                
                intent.putExtra("username",
                        (playerslist.get(position).getUsername()));
                
                
                // Start SingleItemView Class
                context.startActivity(intent);
            }
        });
        
        Players player = playerslist.get(position);
      //  holder.username.setText(" (" + player.getUsername() + ")");
        holder.checkBox.setText(player.getUsername());
        holder.checkBox.setChecked(player.isSelected());
        holder.checkBox.setTag(player);
        //  holder.email.setText(player.getEmail());
       // holder.username.setChecked(player.isSelected());
       // holder.username.setTag(player);
        return view;
        
       
          
    }
    
   
 
}
   

        
        
   
