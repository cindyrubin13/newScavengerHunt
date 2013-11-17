package com.example.scavengerhunt;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
 
public class ListViewAdapter extends BaseAdapter {
 
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    int position2;
    
    private List<Items> itemlist = null;
    private ArrayList<Items> arraylist;
 
    public ListViewAdapter(Context context,
            List<Items> itemlist) {
        this.context = context;
        this.itemlist = itemlist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<Items>();
        this.arraylist.addAll(itemlist);
        
    }
 
    public class ViewHolder {
        TextView itemId;
        TextView itemName;
        Button editButton;
        
    }
 
    @Override
    public int getCount() {
        return itemlist.size();
    }
 
    @Override
    public Object getItem(int position) {
        return itemlist.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    public long getItemName(int position2) {
        return position2;
    }
    
    public View getView( final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.itemId = (TextView) view.findViewById(R.id.textItemId);
            holder.itemName = (TextView) view.findViewById(R.id.textItemName);
            holder.editButton = (Button) view.findViewById(R.id.editButton);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.itemId.setText(itemlist.get(position).getItemId());
        holder.itemName.setText(itemlist.get(position).getItemName());
        
        // Listen for ListView Item Click
        
        
        // add code for buttonEdit
        holder.editButton.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Log.i("edit Button Clicked", "edit button");
                Intent intent = new Intent(context, EditItem.class);
                String editItemId = (String) itemlist.get(position).getItemId();
                String editItemName = (String) itemlist.get(position).getItemName();
                Log.i("ScavengerHuntActivity", "in listview single item click item2" + editItemId);             
                Log.i("ScavengerHuntActivity", "in listview single item click item3" + editItemName);
                intent.putExtra("ItemId", editItemId);
                intent.putExtra("ItemName", editItemName);
                               
                context.startActivity(intent);          
                
            }
            
            
        });
        view.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                String editItemId = (String) itemlist.get(position).getItemId();
                String editItemName = (String) itemlist.get(position).getItemName();
                Log.i("ScavengerHuntActivity", "in listview single item click item2" + editItemId);             
                Log.i("ScavengerHuntActivity", "in listview single item click item3" + editItemName);             
                
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, SingleItemView.class);
                
                intent.putExtra("ItemName", editItemName);
                    
                context.startActivity(intent);
            }
        });
        return view;
    }
 
}

