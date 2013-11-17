package com.example.scavengerhunt;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;



import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.InputType;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;



     
public class CreateGame extends Activity {
    private TextView tvDisplayDate;
    private DatePicker dpResult;
    private TextView tvDisplayDate2;
    private DatePicker dpResult2;
    private TimePicker tpResult;
    private TimePicker tpResult2;
    private Button btnChangeDate;
    private Button btnChangeDate2;
    private EditText title; 
    private String testDate;
    private String testDate2;
    private String objectId;
    private Button submitForm;
    private String dtStart;
    //set date start and end dialog switches for date and time
    static final int DATE_DIALOG_START = 0;
    static final int DATE_DIALOG_END = 1;
    static final int TIME_DIALOG_START = 2;
    static final int TIME_DIALOG_END = 3;
    private Date convertedDate;
    private Date convertDateValue;
    private Date convertDateValue2;
    private ParseUser currentUser;
    private String currentUserObjectId;
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createnewgame);
        title = (EditText)findViewById(R.id.game_title);
        submitForm = (Button) findViewById(R.id.button2);
        currentUser = ParseUser.getCurrentUser();
        currentUserObjectId = currentUser.getObjectId();
        Log.i("main menu activity", "getting current user info " + currentUser.getObjectId());

        setCurrentValuesOnView();
        addListenerOnButton();
        addListenerOnButton2();
        addTimePickerBtnListeners();
     
        setSubmitForm();
        final String titleV = title.getText().toString();
        Log.i("ScavengerHuntActivity", "gameid value create game 1" + objectId );
      
    }

    private void setSubmitForm() {
            submitForm.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View arg0) {
                final String titleValue = title.getText().toString();
                final String dpValue = dpResult.getContext().toString();
                final String dpValue2 = dpResult2.getContext().toString();
                
                convertDateValue = new Date(dpResult.getYear()-1900,dpResult.getMonth(),dpResult.getDayOfMonth(), tpResult.getCurrentHour(), tpResult.getCurrentMinute());//convertDate( testDate);
                convertDateValue2 = new Date(dpResult2.getYear()-1900,dpResult2.getMonth(),dpResult2.getDayOfMonth(), tpResult2.getCurrentHour(), tpResult2.getCurrentMinute());
                
                final ParseObject newGame =  new ParseObject("newGame");
                newGame.put("userObjectId", currentUserObjectId);
                newGame.put("title", titleValue);
                newGame.put("beginDate", convertDateValue);
                newGame.put("endDate", convertDateValue2);           
             // getting objectId after save in background is done.
                newGame.saveInBackground(new SaveCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                           objectId = newGame.getObjectId();                 
                        } else {
                            Log.i("ScavengerHuntActivity", "game title that is inputted "  + e);
                        }
                    }

                    @Override
                    public void done(com.parse.ParseException e) {
                        // TODO Auto-generated method stub
                        Log.i("ScavengerHuntActivity", "game title that is inputted " + titleValue );
                        objectId = newGame.getObjectId();
                            Intent nextScreen = new Intent(getApplicationContext(), ItemList.class);
                            nextScreen.putExtra("gameId", objectId);
                            Log.i("ScavengerHuntActivity", "gameid value create game 3" + objectId );
                            startActivity(nextScreen); 
                     }  
                });              
            }});
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
        
        tvDisplayDate = (TextView) findViewById(R.id.tvDate);
        dpResult = (DatePicker) findViewById(R.id.dpResult);
        tpResult = (TimePicker) findViewById(R.id.tpResult);
        
        tvDisplayDate2 = (TextView) findViewById(R.id.tvDate2);
        dpResult2 = (DatePicker) findViewById(R.id.dpResult2);
        tpResult2 = (TimePicker) findViewById(R.id.tpResult2);
        
        Calendar c = Calendar.getInstance();
        dpResult.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), null);
        tpResult.setCurrentHour(c.get(Calendar.HOUR));
        tpResult.setCurrentMinute(c.get(Calendar.MINUTE));
      
        int am_pm = c.get(Calendar.AM_PM);
        String ampmStr = (am_pm ==0) ? "AM" : "PM";
      
        Log.i("ScavengerHuntActivity", "display AM PM" + ampmStr + "<" + c.get(Calendar.AM_PM) + ">" + "<" + ">");
        
       
        // display date and time for begin date
      
        tvDisplayDate.setText(new StringBuilder().append(dpResult.getMonth()+1).append("-").append(dpResult.getDayOfMonth()).append("-").append(dpResult.getYear()).append("   ").append(tpResult.getCurrentHour()).append(":").append(tpResult.getCurrentMinute()).append(ampmStr));
       
        dpResult2.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), null);
        tpResult2.setCurrentHour(c.get(Calendar.HOUR));
        tpResult2.setCurrentMinute(c.get(Calendar.MINUTE));
        
       
        tvDisplayDate2.setText(new StringBuilder().append(dpResult2.getMonth()+1).append("-").append(dpResult2.getDayOfMonth()).append("-").append(dpResult2.getYear()).append("   ").append(tpResult2.getCurrentHour()).append(":").append(tpResult2.getCurrentMinute()).append(ampmStr));  
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

// when dialog box is closed, below method will be called.
public void onDateSet(DatePicker view, int selectedYear,
    int selectedMonth, int selectedDay) {
       
// set selected date into textview
    
    dpResult2.init(selectedYear, selectedMonth, selectedDay, null);
        tvDisplayDate2.setText(new Date(selectedYear-1900,selectedMonth,selectedDay, tpResult2.getCurrentHour(), tpResult2.getCurrentMinute()).toLocaleString());
   // tvDisplayDate2.setText(new StringBuilder().append(selectedMonth).append(" ").append(selectedDay).append(", ").append(selectedYear).append("  ").append(tpResult2.getCurrentHour()).append(":").append(tpResult2.getCurrentMinute()));  
    };
  };
}
    
    

 
