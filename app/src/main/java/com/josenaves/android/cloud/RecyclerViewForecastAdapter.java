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

    private List<ForecastWeather> forecastWeatherList;

    public RecyclerViewForecastAdapter(List<ForecastWeather> forecastWeatherList) {
        this.forecastWeatherList = forecastWeatherList;
    }

    @Override
    public int getItemCount() {
        return forecastWeatherList.size();
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_forecast, viewGroup, false);
        ForecastViewHolder fvh = new ForecastViewHolder(v);
        return fvh;
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder personViewHolder, int i) {
        personViewHolder.date.setText(forecastWeatherList.get(i).getDate());
        personViewHolder.description.setText(forecastWeatherList.get(i).getForecast());
        personViewHolder.maximum.setText(String.valueOf(forecastWeatherList.get(i).getMaxTemp()) + "\u00B0C");
        personViewHolder.minimum.setText(String.valueOf(forecastWeatherList.get(i).getMinTemp()) + "\u00B0C");
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setWeather(List<ForecastWeather> list) {
        forecastWeatherList.clear();
        forecastWeatherList = list;
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
