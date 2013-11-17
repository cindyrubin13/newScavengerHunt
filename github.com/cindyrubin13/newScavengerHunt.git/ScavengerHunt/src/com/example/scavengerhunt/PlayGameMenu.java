package com.example.scavengerhunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlayGameMenu extends Activity{
    private Button playMenu;
    
    
    public void onCreate(Bundle savedInstanceState) {         
        super.onCreate(savedInstanceState);         
        // Get the view from listview_main.xml         
        setContentView(R.layout.playgame_menu); 
    
    playMenu = (Button) findViewById(R.id.playGame);
    playMenu.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent nextScreen = new Intent(getApplicationContext(), PlayGame.class);
            startActivity(nextScreen);
            
        }
    });

    
}
}
