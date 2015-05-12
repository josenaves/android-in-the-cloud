package com.josenaves.android.cloud;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button btRefresh;
    private TextView txtStatus;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtStatus.setText(R.string.status_ready);

        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setIndeterminate(true);
        progress.setVisibility(View.INVISIBLE);

        btRefresh = (Button) findViewById(R.id.btRefresh);
        btRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateWeather().execute();
            }
        });
    }



    class UpdateWeather extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            txtStatus.setText(R.string.status_running);
            progress.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            txtStatus.setText(R.string.status_done);
            progress.setVisibility(View.INVISIBLE);

        }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
