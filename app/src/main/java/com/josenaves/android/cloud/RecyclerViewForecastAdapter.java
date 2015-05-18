package com.josenaves.android.cloud;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jnaves on 18/05/15.
 */
public class RecyclerViewForecastAdapter extends RecyclerView.Adapter<RecyclerViewForecastAdapter.ForecastViewHolder> {

    private List<Weather> weatherList;

    public RecyclerViewForecastAdapter(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_forecast, viewGroup, false);
        ForecastViewHolder fvh = new ForecastViewHolder(v);
        return fvh;
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder personViewHolder, int i) {
        personViewHolder.date.setText(weatherList.get(i).getDate());
        personViewHolder.description.setText(weatherList.get(i).getForecast());
        personViewHolder.maximum.setText(String.valueOf(weatherList.get(i).getMaxTemp()));
        personViewHolder.minimum.setText(String.valueOf(weatherList.get(i).getMinTemp()));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setWeather(List<Weather> list) {
        weatherList.clear();
        weatherList = list;
        notifyDataSetChanged();
    }

    public static class ForecastViewHolder extends RecyclerView.ViewHolder {
        CardView cardview;
        TextView date;
        TextView minimum;
        TextView maximum;
        TextView description;

        ForecastViewHolder(View itemView) {
            super(itemView);
            cardview = (CardView)itemView.findViewById(R.id.cardview_forecast);
            date = (TextView)itemView.findViewById(R.id.forecast_date);
            minimum = (TextView)itemView.findViewById(R.id.forecast_minimum);
            maximum = (TextView)itemView.findViewById(R.id.forecast_maximum);
            description = (TextView)itemView.findViewById(R.id.forecast_description);
        }
    }

}
