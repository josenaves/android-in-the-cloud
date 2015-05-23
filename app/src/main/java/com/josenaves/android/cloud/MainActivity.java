package com.josenaves.android.cloud;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.josenaves.android.cloud.json.ForecastResponseDeserializer;
import com.josenaves.android.cloud.model.ForecastResponse;
import com.josenaves.android.cloud.model.ForecastWeather;
import com.josenaves.android.cloud.service.ForecastService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Esta classe representa a única tela do aplicativo de previsão do tempo.
 * @author josenaves
 */
public class MainActivity extends ActionBarActivity {

    public static final String API_ENDPOINT = "https://android-node.herokuapp.com";

    private Button btRefresh;
    private TextView txtStatus;
    private ProgressBar progress;

    private Toolbar toolbar;

    private RecyclerViewForecastAdapter adapter;

    private RecyclerView recyclerView;

    private List<ForecastWeather> forecastList = new ArrayList<ForecastWeather>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtStatus.setText(R.string.status_ready);

        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setIndeterminate(true);
        progress.setVisibility(View.INVISIBLE);

        btRefresh = (Button) findViewById(R.id.btRefresh);
        btRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtStatus.setText(R.string.status_running);
                progress.setVisibility(View.VISIBLE);

                getService().getForecast("sao+paulo", "metric", 3, new Callback<ForecastResponse>() {
                    @Override
                    public void success(ForecastResponse forecastResponse, Response response) {
                        adapter.setWeather(forecastResponse.getForecastList());
                        txtStatus.setText(R.string.status_done);
                        progress.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        txtStatus.setText(R.string.status_error);
                        progress.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });

        if (!isOnline()) {
            btRefresh.setEnabled(false);
            txtStatus.setText(R.string.no_connectivity);
            Toast.makeText(this, R.string.no_connectivity, Toast.LENGTH_LONG).show();
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_forecast);
        recyclerView.setHasFixedSize(true);

        // seta um layoutmanager para o recyclerview (obrigatório)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // seta o adapter para o recyclerview
        adapter = new RecyclerViewForecastAdapter(forecastList);
        recyclerView.setAdapter(adapter);
    }

    private ForecastService getService() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ForecastResponse.class, new ForecastResponseDeserializer())
                .create();

        GsonConverter converter = new GsonConverter(gson);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_ENDPOINT)
                .setConverter(converter)
                .setRequestInterceptor(basicAuth("android", "cloud"))
                .setLogLevel(RestAdapter.LogLevel.FULL)  // habilita logging
                .build();

        return restAdapter.create(ForecastService.class);
    }

    private RequestInterceptor basicAuth(String user, String password) {
        final String credentials = user + ":" + password;
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                request.addHeader("Accept", "application/json");
                request.addHeader("Authorization", basic);
            }
        };
    }

    /**
     * Verifica se o dispositivo está conectado a Internet
     * @return true se está conectado; false caso contrário
     */
    private boolean isOnline() {
        ConnectivityManager cm  = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        else {
            return false;
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
