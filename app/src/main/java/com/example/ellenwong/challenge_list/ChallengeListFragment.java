package com.example.ellenwong.challenge_list;

/**
 * Created by ellenwong on 1/26/15.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ellenwong on 1/20/15.
 */

/**
 * A placeholder fragment containing a simple view.
 */
public class ChallengeListFragment extends Fragment {

    private static String TAG = "ChallengeListFragment";
    private static String jsonStr = "";
    private static ArrayAdapter<String> mChallengeListAdapter = null;
    private static ArrayList<String> testDataArrayList = null;

    public ChallengeListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Button addButton = (Button) rootView.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButtonClicked();
            }
        });

        //TODO: get rid of testData
        String [] testData = {"This", "is", "to", "test", "list"};
        testDataArrayList = new ArrayList<String>(Arrays.asList(testData));

        // Initialize an array adapter to display content in ListView
        mChallengeListAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_main,
                R.id.list_item_main_textview,
                testDataArrayList);

        // set the adapter to the ListView that we will populate with the data
        ListView listView = (ListView) rootView.findViewById(R.id.listView_main);
        listView.setAdapter(mChallengeListAdapter);
        fetchChallengesFromParse();

        return rootView;
    }

    // Onclick method for testButton
//    void testButtonClicked() {
//        //new FetchServerDataTask().execute();
//        fetchChallengesFromParse();
//
//    }

    void addButtonClicked() {
        Intent intent = new Intent(this.getActivity(), AddItemActivity.class);
        startActivity(intent);
    }

    // Use Parse Query API to get challenges from Parse cloud
    private void fetchChallengesFromParse() {
        ParseQuery<ChallengeListItem> challenges = ChallengeListItem.getQuery();
        challenges.findInBackground(new FindCallback<ChallengeListItem>() {
            @Override
            public void done(List<ChallengeListItem> challengeListItems, ParseException e) {
                if (challengeListItems != null ) {
                    mChallengeListAdapter.clear();
                    for (ChallengeListItem challenge: challengeListItems) {
                        mChallengeListAdapter.add(challenge.getName());
                    }
                } else {
                    // TODO: error handling here, something is wrong since app isn't getting data from server
                }
            }
        });
    }


    /**
     * Created by ellenwong on 1/16/15.
     * Make it static so it doesn't have access to any member variables of the outer class
     * A temp lightweight network class to do calls to server using AsyncTask
     * Need implementing a more complex class to handle larger data load using
     * Runnables and concurrent class
     *
     */
//    private class FetchServerDataTask extends AsyncTask<String, String, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//
//            ServerConnectionHelper connectionHelper = new ServerConnectionHelper();
//            jsonStr = connectionHelper.sendAServerConnection();  // modify to call parse cloud
//            return jsonStr;
//        }
//
//        @Override
//        protected void onPostExecute(String jsonStr) {
//            Log.d(TAG, "jsonStr = " + jsonStr);
//
//            mChallengeListAdapter.clear();
//
//            ListDataParser parser = new ListDataParser();
//            try {
//                testDataArrayList = parser.getChallengeListFromJSON(jsonStr);
//            } catch(JSONException e) {
//                Log.e(TAG, "onPostExecute exception: " + e.toString());
//            }
//
//            for (String challenge: testDataArrayList) {
//                mChallengeListAdapter.add(challenge);
//            }
//
//        }
//
//    }
}