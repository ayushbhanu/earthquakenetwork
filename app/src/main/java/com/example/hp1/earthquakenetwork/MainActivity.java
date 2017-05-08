package com.example.hp1.earthquakenetwork;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp1.earthquakenetwork.R;
import com.example.hp1.earthquakenetwork.info;
import com.example.hp1.earthquakenetwork.queryutils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static  ArrayList<info> i;
    ListView lv;
    ProgressBar pb;

    private static final String SAMPLE_JSON_RESPONSE="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=2&limit=10";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create a fake list of earthquake locations.
//        ArrayList<info> earthquakes = new ArrayList<>();
//        earthquakes.add(new info("7.2","San Francisco","23 dec 2004"));
//        earthquakes.add(new info("6.1","London","July-12-2014"));
//        earthquakes.add(new info("3.9","Tokyo","Nov-5-2015"));
//        earthquakes.add(new info("5.4","Mexico City","Feb-4-2014"));
//        earthquakes.add(new info("2.8","Moscow","Mar-19-2015"));
//        earthquakes.add(new info("4.9","Rio de Janeiro","Jan-4-2014"));
//        earthquakes.add(new info("1.6","Paris","Dec-25-2015"));

        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(!isConnected){
          pb=  (ProgressBar)findViewById(R.id.pbar);
            pb.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "Not Connected", Toast.LENGTH_SHORT).show();
            lv=(ListView)findViewById(R.id.list);
            TextView tv=(TextView)findViewById(R.id.empty);
            lv.setEmptyView(tv);
tv.setText("Please Check Your Connection");
        }
else {
            Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_SHORT).show();


            earth e = new earth();
            e.execute(SAMPLE_JSON_RESPONSE);
        }
     //   ArrayList<info> earthquakes= queryutils.extractEarthquakes();


        // Find a reference to the {@link ListView} in the layout
       // ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
//        customadapter adapter = new customadapter(
//                this,  earthquakes);
//
//        // Set the adapter on the {@link ListView}
//        // so the list can be populated in the user interface
//        earthquakeListView.setAdapter(adapter);
    }

    //


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/


    private void  update(ArrayList a){
         lv=(ListView) findViewById(R.id.list);
        pb=(ProgressBar)findViewById(R.id.pbar);
        pb.setVisibility(View.GONE);
        TextView tv=(TextView) findViewById(R.id.empty);

           // lv.setVisibility(View.INVISIBLE);
lv.setEmptyView(tv);
            tv.setText("No  Earthquake data");


            customadapter ca = new customadapter(this, a);
            lv.setAdapter(ca);


    }
    protected class earth extends AsyncTask<String ,Void,ArrayList>{
        @Override
        protected ArrayList<info> doInBackground(String... url) {
            if (url.length < 1 || url[0]==null) {
                return null;

            }



                i = queryutils.fetchEarthquakeData(url[0]);
                return i;
        }

        @Override
        protected void onPostExecute(ArrayList arrayList) {
            update(arrayList);
        }
    }

}
