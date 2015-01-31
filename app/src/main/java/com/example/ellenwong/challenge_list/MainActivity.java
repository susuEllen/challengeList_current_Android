package com.example.ellenwong.challenge_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.AppEventsLogger;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends ActionBarActivity {

    private static String TAG = "MainActivity";
    private static final String APP_ID_PARSE = "A6AWoEb3MwcoCdYOLyAkT4IcRPCsEz17zkh9LSBR";
    private static final String CLIENT_ID_PARSE = "R0XTcA7Np8LclTrvTLS9rOsQlvq2kkOMwxARj1i9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.registerParseObjects();
        Parse.initialize(this, APP_ID_PARSE, CLIENT_ID_PARSE);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            this.proceedToChallengeList(savedInstanceState);
        } else {
            Log.d(TAG, "currentUser is null, start login process");
            this.logIn();
        }

        // create a testUser
        //testCreateAUser();
        // try to create a test Parse Object, REMOVE LATER
        //testCreatingTestChallengeListObject();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        switch (id) {
            case R.id.action_settings:
                // TODO: code to pop out settings menu
                return true;
            case R.id.action_logout:
                logOut();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //TODO: move this method to the right place, logOut should be able to take place in everyActivity.
    private void logOut(){
        Log.d(TAG, "In logOut");
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            ParseUser.logOut();
            currentUser = ParseUser.getCurrentUser(); // this will now be null
            Log.d(TAG, "Logout completed. currentUser = " + currentUser);
            if(currentUser == null) logIn();
        } else {
            Log.d(TAG, "currentUser = " + currentUser);
            logIn();
        }

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);  //TODO: REMOVE? [when people install or engage with your app, you'll see this data reflected in your FB app's Insights dashboard.]

    }

    @Override
    protected void onPause() {
        super.onPause();
        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this); //TODO: REMOVE? [when people install or engage with your app, you'll see this data reflected in your FB app's Insights dashboard.]
    }

    //    private void testCreateParseUser() {
//        // try out creating user with standard code
//        //testCreateParseUser();
//
//        ParseUser user = new ParseUser();
//        user.setUsername("my name Ellen");
//        user.setPassword("my pass Ellen");
//        user.setEmail("email@example.com");
//
//// other fields can be set just like with ParseObject
//        user.put("phone", "650-555-0000");
//
//        user.signUpInBackground(new SignUpCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null) {
//
//                } else {
//
//                }
//            }
//        });
//    }
//    private void testCreateParseObject() {
//        ParseObject testObject = new ParseObject("TestObjectEllen");
//        testObject.put("What", "is");
//        testObject.saveInBackground();
//    }
//
//    private void testCreatingTestChallengeListObject() {
//        ChallengeListItem item = new ChallengeListItem();
//        item.setName("Yoga");
//        item.setDescription("Do Yoga for 30 Days");
//        item.setGoal(30);
//        item.setStart(0);
//        item.setImageURL("http://www.lanzarotesurf.com/wp-content/uploads/2014/11/yoga-pose.jpg");
//        item.setMetric("day");
//        item.saveInBackground();
//    }


    private void logIn() {
        Log.d(TAG, "In login");

        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void proceedToChallengeList(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ChallengeListFragment())
                    .commit();
        }
    }

    private void testCreateAUser() {
        Log.d(TAG, "In testCreateAUser");
        ParseUser user = new ParseUser();
        user.setUsername("my name");
        user.setPassword("my pass");
        user.setEmail("email1@example.com");

        // other fields can be set just like with ParseObject
        user.put("phone", "650-555-0000");
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });
    }

    private void registerParseObjects() {
        ParseObject.registerSubclass(ChallengeListItem.class);
    }

//    // placeholder logOut method
//    private void logOut() {
//        ParseUser.logOut();
//        ParseUser currentUser = ParseUser.getCurrentUser();     // currentUser should now be null
//    }
}