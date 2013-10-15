package com.example.scavengerhunt;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well...and then does login or signup using Parse.com API
 */
public class LoginActivity extends Activity {
    private static final String TAG = "LoginActivity";

    private Button continueButton;
    private Button cancelButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText emailEditText;

    private String username;
    private String password;
    private String email;

    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setupButtonCallbacks();
    }

    @Override
    public void onResume() {
        super.onResume();
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null && currentUser.getObjectId() != null) {
           username = currentUser.getUsername();
           usernameEditText.setText(username);
           email = currentUser.getEmail();
           emailEditText.setText(email);
           startActivity(new Intent(this, MainMenuActivity.class));
           finish();
        }
    }
    private void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    private final FindCallback<ParseUser> userFindCallback = new FindCallback<ParseUser>()  {
        @Override
        public void done(List<ParseUser> object, ParseException e) {
            dismissProgressDialog();
            if (e == null) {
                if (object != null && object.size() > 0) {
                    ParseUser user = (ParseUser) object.get(0);
                    if (username != null) {
                        String existingUsername = user.getUsername();
                        if (!username.equals(existingUsername)) {
                            usernameEditText.setText("");
                            usernameEditText.requestFocus();
                            username = null;
                            showToast(getString(R.string.label_loginUsernameAlreadyExists));
                            return;
                        }
                    }
                    if (email != null) {
                        String existingEmail = user.getEmail();
                        if (!email.equals(existingEmail)) {
                            emailEditText.setText("");
                            emailEditText.requestFocus();
                            email = null;
                            showToast(getString(R.string.label_loginEmailAlreadyExists));
                            return;
                        }
                    }
                    doLogin(username, password);
                } 
                else {
                    doSignUp(username, password, email);
                }
            } 
            else {
                showToast(getString(R.string.label_signupErrorMessage) + " "
                        + getString(R.string.label_loginPleaseTryAgainMessage));
            }
            }
    };

    private void showToast(String message) {
        ScavengerHuntApplication.getInstance().showToast(LoginActivity.this,
                message);
    }

    /**
     * Method to setup the UI button callbacks
     */
    private void setupButtonCallbacks() {
        initializeVariables();
        continueButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                loginVariables();
                if (username == null || username.length() == 0) {
                    showToast(getString(R.string.hint_username));
                    return;
                }
                if (password == null || password.length() == 0) {
                    showToast(getString(R.string.hint_password));
                    return;
                }
                getStoredData();
            }
        });

        // Cancel button handler
        cancelButton = (Button) findViewById(R.id.loginbutton_cancel);
        cancelButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Logout with ParseUser.logOut()
                ParseUser.logOut();
                finish();
            }
        });
    }
    
    
    
    
    
    
    
    
// initialize callback buttons
    private void initializeVariables() {
        usernameEditText = (EditText) findViewById(R.id.textbox_loginUsername);
        passwordEditText = (EditText) findViewById(R.id.textbox_loginPassword);
        emailEditText = (EditText) findViewById(R.id.textbox_loginEmail);
        continueButton = (Button) findViewById(R.id.loginbutton_continue);
    }
    private final SignUpCallback signinCallback = new SignUpCallback() {
        @Override
        public void done(ParseException e) {
            dismissProgressDialog();
            if (e == null) {
                Log.d(TAG + ".doSignUp",
                        "Success!  User account created for username="
                                + LoginActivity.this.username);
                gotoStartActivity();
            } else {
                showToast(getString(R.string.label_signupErrorMessage) + " "
                        + getString(R.string.label_loginPleaseTryAgainMessage));
            }
        }
    };

    private void doSignUp(final String username, final String password, final String email) {
        progressDialog = ProgressDialog.show(LoginActivity.this,
                getString(R.string.label_login_please_wait),
                getString(R.string.label_signup_in_progress));
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.signUpInBackground(signinCallback);
    }

    private final LogInCallback loginCallback = new LogInCallback() {
        @Override
        public void done(ParseUser object, ParseException e) {
            dismissProgressDialog();
            if (object != null) {
                Log.d(TAG + ".doParseLogin",
                        "Success!  Current User ObjectId: "
                                + object.getObjectId());
                gotoStartActivity();
            } else {
                // Notify user that login failed and ask to try again
                Log.d(TAG + ".doParseLogin", "Failed", e);
                showToast(getString(R.string.label_loginErrorMessage) + " "
                        + e.getMessage() + ".  "
                        + getString(R.string.label_loginPleaseTryAgainMessage));
            }
        }

       
    };
    // initialize variable from input screen
    private void loginVariables() {
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
        email = emailEditText.getText().toString();
    }
    
 // code used in 2 places
    private void gotoStartActivity() {
        startActivity(new Intent(LoginActivity.this,
                MainMenuActivity.class));
        finish();
        
    }
    private void doLogin(final String username, final String password) {
        progressDialog = ProgressDialog.show(LoginActivity.this,
                getString(R.string.label_login_please_wait),
                getString(R.string.label_login_in_progress) + " '" + username
                        + "'");
        ParseUser.logInInBackground(username, password, loginCallback);
    }
    private void getStoredData() {
        progressDialog = ProgressDialog.show(LoginActivity.this,
                getString(R.string.label_login_please_wait),
                getString(R.string.label_query_in_progress) + " '"
                        + username + "'");

        List<ParseQuery<ParseUser>> parseUserQueryList = new ArrayList<ParseQuery<ParseUser>>();

        ParseQuery<ParseUser> parseUsernameQuery = ParseUser.getQuery();
        parseUsernameQuery.whereEqualTo("username", username);
        parseUserQueryList.add(parseUsernameQuery);
        ParseQuery<ParseUser> parseEmailQuery = ParseUser.getQuery();
        parseEmailQuery.whereEqualTo("email", email);
        parseUserQueryList.add(parseEmailQuery);
        ParseQuery<ParseUser> parseUserQuery = ParseQuery.or(parseUserQueryList);
        parseUserQuery.findInBackground(userFindCallback); 
    }
}
