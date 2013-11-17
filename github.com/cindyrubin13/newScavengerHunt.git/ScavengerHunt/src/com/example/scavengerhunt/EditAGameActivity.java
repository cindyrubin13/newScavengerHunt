package com.example.scavengerhunt;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class EditAGameActivity extends Activity {
    
    
    private EditText updateGame;
    
    String objectId;
    String gameName;
    TextView currentGame;
    String nUpdateItem;
    String gameId;
    String gameTitle;
    private Date gameBeginDate;
    private Date gameEndDate;
    private Date gameCreatedAt;
    private ParseUser currentUser;
    private String gameUserId;
    private String currentUserObjectId;
    private String nUpdateGame;
    Context context;
    private TextView tvDisplayDate;
    private DatePicker dpResult;
    private TextView tvDisplayDate2;
    private DatePicker dpResult2;
    private TimePicker tpResult;
    private TimePicker tpResult2;
    private Button btnChangeDate;
    private Button btnChangeDate2;
    private Button submitForm;
    static final int DATE_DIALOG_START = 0;
    static final int DATE_DIALOG_END = 1;
    static final int TIME_DIALOG_START = 2;
    static final int TIME_DIALOG_END = 3;
    private Date convertedDate;
    private Date convertDateValue;
    private Date convertDateValue2;
  
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_game);
        Bundle extras = getIntent().getExtras();
        Log.i("ScavengerHuntActivity", "gameid value itemslist 1" + extras );
        if (extras != null) {
            
             gameId = extras.getString("GameId");
             gameName = extras.getString("GameName");
             
            Log.i("ScavengerHuntActivity", "gameid value itemslist 1" + gameId );
         }
        this.context = context;
        currentGame = (TextView)findViewById(R.id.currentTitle);
        currentGame.setText(gameName);
        updateGame = (EditText)findViewById(R.id.game_title);
        nUpdateGame = updateGame.getText().toString();
        submitForm = (Button) findViewById(R.id.button2);
       
        currentUser = ParseUser.getCurrentUser();
        Log.d("scavenger hunt", "Current User " + "<" + currentUser + ">" );
        currentUserObjectId = currentUser.getObjectId();
        Log.d("scavenger hunt", "Current User " + "<" + currentUserObjectId + ">" );
        
     
        ParseQuery<ParseObject> query = ParseQuery.getQuery("newGame");
        query.whereEqualTo("objectId", gameId);
        //  query.whereNotEqualTo("beginDate", myDate);
          Log.i("scavenger Hunt", "in parse query"  + gameId);
          query.getInBackground( gameId, new GetCallback<ParseObject>() {  
              public void done( ParseObject object, ParseException e) {
              Log.i("scavenger Hunt", "in parse query2 getting  game" );
                  if (e == null) {
                      Log.d("score", "Retrieved "  + " items in game");
                      
                     
                   //   gameId = object.getString("objectId");
                      gameUserId = object.getString("userObjectId");
                      gameTitle =  object.getString("title");
                      gameCreatedAt = object.getCreatedAt();
                      gameBeginDate = object.getDate("beginDate");
                      gameEndDate = object.getDate("endDate");
                      Log.d("scavenger hunt", "Retrieved " + "<" + gameTitle + ">" );
                      Log.d("scavenger hunt", "Retrieved " + "<" + gameUserId + ">" );
                      Log.d("scavenger hunt", "Retrieved " + "<" + gameId + ">" );
                      Log.d("scavenger hunt", "Retrieved " + "<" + gameBeginDate + ">" );
                      Log.d("scavenger hunt", "Retrieved " + "<" + gameEndDate + ">" );
                      Log.d("scavenger hunt", "Retrieved " + "<" + gameCreatedAt + ">" );
                      setCurrentValuesOnView();
                         addListenerOnButton();
                      addListenerOnButton2();
                      addTimePickerBtnListeners();
                      setSubmitForm();
                      
                 //    if (gameUserId.equals(currentUserObjectId)) {
                  //       Toast.makeText(getApplicationContext(), "You did not create this game! Sorry you can't change it!!", Toast.LENGTH_LONG).show();
                  //        finish();
                          
                  //    }
                      }
              }});
          
    
      /*    addListenerOnButton();
          addListenerOnButton2();
          addTimePickerBtnListeners();*/

        //  setSubmitForm();
          
     //     Log.i("ScavengerHuntActivity", "gameid value create game 1" + objectId );
   }
  //  }
    private void setSubmitForm() {
        submitForm.setOnClickListener(new View.OnClickListener() {
        
        public void onClick(View arg0) {
       //     final String titleValue = title.getText().toString();
            nUpdateGame = updateGame.getText().toString();
            final String dpValue = dpResult.getContext().toString();
            final String dpValue2 = dpResult2.getContext().toString();
            
            convertDateValue = new Date(dpResult.getYear()-1900,dpResult.getMonth(),dpResult.getDayOfMonth(), tpResult.getCurrentHour(), tpResult.getCurrentMinute());//convertDate( testDate);
            convertDateValue2 = new Date(dpResult2.getYear()-1900,dpResult2.getMonth(),dpResult2.getDayOfMonth(), tpResult2.getCurrentHour(), tpResult2.getCurrentMinute());
            
            ParseQuery<ParseObject> query = ParseQuery.getQuery("newGame");
          //  query.whereEqualTo("objectId", gameId); 
            query.getInBackground( gameId, new GetCallback<ParseObject>() {  
                public void done( ParseObject gameInfo, ParseException e) {
                Log.i("scavenger Hunt", "in parse query2 getting  game" );
                    if (e == null) {
                        Log.d("score", "Retrieved "  + " items in game");
                       gameInfo.put("userObjectId", currentUserObjectId);
                       
                       if (nUpdateGame.equals("")){
                           Log.d("score", "checking if game title is not null "  + "<" + nUpdateGame + ">");  
                           
                       } else {
                           Log.d("score", "if here title should be null "  + "<" + nUpdateGame + ">");    
                           gameInfo.put("title", nUpdateGame);
                       }
         
            gameInfo.put("beginDate", convertDateValue);
            gameInfo.put("endDate", convertDateValue2);           
         // getting objectId after save in background is done.
            gameInfo.saveInBackground(new SaveCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        Log.i("ScavengerHuntActivity", "game information is updated " );                
                        Intent nextScreen = new Intent(getApplicationContext(), NewGameList.class);
                        nextScreen.putExtra("gameId", gameId);
                        startActivity(nextScreen);
                    
                    } else {
                        Log.i("ScavengerHuntActivity", "game title that is inputted "  + e);
                    }
                }});}

           
                }              
        });}});
}

public static Date convertDate(String testDate) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'z'" , Locale.US);
 //   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'z'" );
    Date convertedDate = new Date();
 
        try {
           convertedDate = dateFormat.parse(testDate);
       } catch (java.text.ParseException e) {
           // TODO Auto-generated catch block
           Log.i("ScavengerHuntActivity", "display converted date did not work " + testDate + "CD" + convertedDate);
           e.printStackTrace();
       }
        Log.i("ScavengerHuntActivity", "display converted date 2" + testDate + "CD" + convertedDate);
        return convertedDate;
    
}

// display current date
public void setCurrentValuesOnView()  {
    Log.i("ScavengerHuntActivity", "in current values" + "<" + gameBeginDate + ">");
    tvDisplayDate = (TextView) findViewById(R.id.tvDate);
   dpResult = (DatePicker) findViewById(R.id.dpResult);
    Date dpResultOrig = gameBeginDate;
    String h1 = "";
    String h2 = "";
    Log.i("ScavengerHuntActivity", "gameBeginDate" + "<" + gameBeginDate + ">");
    Log.i("ScavengerHuntActivity", "dpResultOrig" + "<" + dpResultOrig + ">");
    Log.i("ScavengerHuntActivity", "dpResultOrig - Date" + "<" + dpResultOrig.getDate() + ">");
  Log.i("ScavengerHuntActivity", "dpResultOrig - year" + "<" + dpResultOrig.getYear() + ">");
  Log.i("ScavengerHuntActivity", "dpResultOrig - month" + "<" + dpResultOrig.getMonth() + ">");
   Log.i("ScavengerHuntActivity", "dpResultOrig - Day" + "<" + dpResultOrig.getDay() + ">");
    tpResult = (TimePicker) findViewById(R.id.tpResult);
    
    tvDisplayDate2 = (TextView) findViewById(R.id.tvDate2);
    dpResult2 = (DatePicker) findViewById(R.id.dpResult2);
    tpResult2 = (TimePicker) findViewById(R.id.tpResult2);
    
    Calendar c = Calendar.getInstance();
    //  dpResult.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), null);
  //   dpResult.init(dpResultOrig.getYear(), dpResultOrig.getMonth()monthOfYear, dayOfMonth, onDateChangedListener)
    dpResult.init(gameBeginDate.getYear()+ 1900, gameBeginDate.getMonth(), gameBeginDate.getDate(), null);
   // tpResult.setCurrentHour(c.get(Calendar.HOUR));
    tpResult.setCurrentHour(gameBeginDate.getHours());
  //  tpResult.setCurrentMinute(c.get(Calendar.MINUTE));
    tpResult.setCurrentMinute(gameBeginDate.getMinutes());
  
    int am_pm = c.get(Calendar.AM_PM);
    String ampmStr = (am_pm ==0) ? "AM" : "PM";
  
    Log.i("ScavengerHuntActivity", "display AM PM" + ampmStr + "<" + c.get(Calendar.AM_PM) + ">" + "<" + ">");
    
   
    // display date and time for begin date
    if (dpResultOrig.getHours() < 10){
        h1= "0";
    }
    if (dpResultOrig.getMinutes() < 10){
        h2= "0";
    }
   
   // tvDisplayDate.setText(new StringBuilder().append(dpResult.getMonth()+1).append("-").append(dpResult.getDayOfMonth()).append("-").append(dpResult.getYear()).append("   ").append(tpResult.getCurrentHour()).append(":").append(tpResult.getCurrentMinute()).append(ampmStr));
  tvDisplayDate.setText(new StringBuilder().append(dpResultOrig.getMonth()+ 1).append("-").append(dpResultOrig. getDate()).append('-').append(dpResultOrig.getYear()+ 1900).append("   ").append(h1).append(dpResultOrig.getHours()).append(":").append(h2).append(dpResultOrig.getMinutes()));
  //  dpResult2.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), null);
    dpResult2.init(gameEndDate.getYear()+ 1900, gameEndDate.getMonth(), gameEndDate.getDate(), null);
  //  tpResult2.setCurrentHour(c.get(Calendar.HOUR));
    tpResult2.setCurrentHour(gameEndDate.getHours());
  //  tpResult2.setCurrentMinute(c.get(Calendar.MINUTE));
    tpResult2.setCurrentMinute(gameEndDate.getMinutes());
    String h3 = "";
    String h4 = "";
    if (gameEndDate.getHours() < 10) {
        h3 = "0";
    }
   
    if (gameEndDate.getMinutes() < 10) {
        h4 = "0";
    }
   
  //  tvDisplayDate2.setText(new StringBuilder().append(dpResult2.getMonth()+1).append("-").append(dpResult2.getDayOfMonth()).append("-").append(dpResult2.getYear()).append("   ").append(tpResult2.getCurrentHour()).append(":").append(tpResult2.getCurrentMinute()).append(ampmStr));  
    tvDisplayDate2.setText(new StringBuilder().append(gameEndDate.getMonth()+ 1).append("-").append(gameEndDate. getDate()).append('-').append(gameEndDate.getYear()+ 1900).append("   ").append(h3).append(gameEndDate.getHours()).append(":").append(h4).append(gameEndDate.getMinutes()));
    Log.i("ScavengerHuntActivity", "tvdisplaydate2" +  "<" + tvDisplayDate2.toString() + ">");
    Log.i("ScavengerHuntActivity", "tvdisplaydate" +  "<" + tvDisplayDate.toString() + ">");
}


public void addListenerOnButton() {
    btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
    btnChangeDate.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            showDialog(DATE_DIALOG_START);
        }
    });
}

public void addTimePickerBtnListeners() {
    Button btnToChange = (Button) findViewById(R.id.btnChangeTime);
    btnToChange.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            showDialog(TIME_DIALOG_START);
        }
    });
    
    btnToChange = (Button) findViewById(R.id.btnChangeTime2);
    btnToChange.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            showDialog(TIME_DIALOG_END);
        }
    });
}

public void addListenerOnButton2() {
    btnChangeDate2 = (Button) findViewById(R.id.btnChangeDate2);
        btnChangeDate2.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_END);  
            }
    });
}


@Override
protected Dialog onCreateDialog(int id) {
    
    Calendar c = Calendar.getInstance();
    switch (id) {
 
    case DATE_DIALOG_START:
 //    set begin date of date picker as current date
      
         return new DatePickerDialog(this, start_dateListener, 
            c.get(Calendar.YEAR), c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
    case DATE_DIALOG_END:
      //    set end date of date picker as current date
         return new DatePickerDialog(this, end_dateListener, 
                 c.get(Calendar.YEAR), c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
    
    case TIME_DIALOG_START:
        return new TimePickerDialog(this, timePickerListener, c.get(Calendar.HOUR), c.get(Calendar.MINUTE), false);
        
    case TIME_DIALOG_END:
        return new TimePickerDialog(this, timePickerListener2, c.get(Calendar.HOUR), c.get(Calendar.MINUTE), false);
    }
    return null;
  
}

private TimePickerDialog.OnTimeSetListener timePickerListener 
    = new TimePickerDialog.OnTimeSetListener() {
    
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            tpResult.setCurrentHour(hourOfDay);
            tpResult.setCurrentMinute(minute);
           
          
            tvDisplayDate.setText(new Date(dpResult.getYear()-1900,dpResult.getMonth(),dpResult.getDayOfMonth(), hourOfDay, minute ).toLocaleString());
        }
    };
    
    private TimePickerDialog.OnTimeSetListener timePickerListener2
    = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            tpResult2.setCurrentHour(hourOfDay);
            tpResult2.setCurrentMinute(minute);         
            tvDisplayDate2.setText(new Date(dpResult2.getYear()-1900,dpResult2.getMonth(),dpResult2.getDayOfMonth(), hourOfDay, minute).toLocaleString());
        }
    };

private DatePickerDialog.OnDateSetListener start_dateListener 
            = new DatePickerDialog.OnDateSetListener() {

    // when dialog box is closed, below method will be called.
    public void onDateSet(DatePicker view, int selectedYear,
            int selectedMonth, int selectedDay) {
        // set selected date into textview
        dpResult.init(selectedYear, selectedMonth, selectedDay, null);
        
        
        tvDisplayDate.setText(new Date(selectedYear-1900,selectedMonth,selectedDay, tpResult.getCurrentHour(), tpResult.getCurrentMinute()).toLocaleString());     
       // tvDisplayDate.setText(new StringBuilder().append(selectedMonth).append(" ").append(selectedDay).append(", ").append(selectedYear).append("  ").append(tpResult.getCurrentHour()).append(":").append(tpResult.getCurrentMinute()));
    };
};
private DatePickerDialog.OnDateSetListener end_dateListener 
= new DatePickerDialog.OnDateSetListener() {

//when dialog box is closed, below method will be called.
public void onDateSet(DatePicker view, int selectedYear,
int selectedMonth, int selectedDay) {
   
//set selected date into textview

dpResult2.init(selectedYear, selectedMonth, selectedDay, null);
    tvDisplayDate2.setText(new Date(selectedYear-1900,selectedMonth,selectedDay, tpResult2.getCurrentHour(), tpResult2.getCurrentMinute()).toLocaleString());
// tvDisplayDate2.setText(new StringBuilder().append(selectedMonth).append(" ").append(selectedDay).append(", ").append(selectedYear).append("  ").append(tpResult2.getCurrentHour()).append(":").append(tpResult2.getCurrentMinute()));  
};
};
}    
    
    
    
    



