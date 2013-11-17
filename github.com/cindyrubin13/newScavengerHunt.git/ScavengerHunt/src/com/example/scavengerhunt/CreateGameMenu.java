package com.example.scavengerhunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateGameMenu extends Activity {
    private Button createMenu;
    private Button playerMenu;
    
    public void onCreate(Bundle savedInstanceState) {         
        super.onCreate(savedInstanceState);         
        // Get the view from listview_main.xml         
        setContentView(R.layout.creategame_menu); 
    
    createMenu = (Button) findViewById(R.id.createNewGame);
    createMenu.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent nextScreen = new Intent(getApplicationContext(), CreateGame.class);
            startActivity(nextScreen);
            
        }
    });

    playerMenu = (Button) findViewById(R.id.playerlist);
    playerMenu.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent nextScreen = new Intent(getApplicationContext(), PlayerList.class);
            startActivity(nextScreen);
            
        }
    });
    }}
