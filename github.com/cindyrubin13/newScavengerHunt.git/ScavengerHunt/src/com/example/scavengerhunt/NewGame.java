package com.example.scavengerhunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewGame extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games);
        
        Button btnNextScreen = (Button) findViewById(R.id.mainMenuButton_newGame);
        
        //Listening to button event
        btnNextScreen.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), IndoorGame.class);
 
                //Sending data to another Activity
              
                startActivity(nextScreen);
 
            }
        });
 
    }

}
