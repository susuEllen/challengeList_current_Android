package com.example.ellenwong.challenge_list;

/**
 * Created by ellenwong on 1/26/15.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class AddItemActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_add_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_add_item, container, false);

            Button challengeButton = (Button)rootView.findViewById(R.id.button_challenge);

            challengeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createNewChallenge(rootView);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });

            return rootView;
        }

        private static void createNewChallenge(View rootView) {

            EditText name_input = (EditText)rootView.findViewById(R.id.editText_challengeName);
            String name = name_input.getText().toString();

            EditText detail_input = (EditText)rootView.findViewById(R.id.editText_challengeDetail);
            String detail = detail_input.getText().toString();


            ChallengeListItem item = new ChallengeListItem();
            item.setName(name);
            item.setDescription(detail);
            //item.setGoal(30);
            item.setStart(0);
            //item.setImageURL("http://www.lanzarotesurf.com/wp-content/uploads/2014/11/yoga-pose.jpg");
            //item.setMetric("day");
            item.saveInBackground();
        }
    }
}