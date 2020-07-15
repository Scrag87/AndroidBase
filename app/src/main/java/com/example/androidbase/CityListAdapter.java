package com.example.androidbase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.CityItemViewHolder> {
  public interface ClickHandler {
    void onClick(String value);
  }

  private final ClickHandler listener;

  class CityItemViewHolder extends RecyclerView.ViewHolder {
    TextView cityNameView;

    public CityItemViewHolder(@NonNull View itemView, ClickHandler listener) {
      super(itemView);
      cityNameView = itemView.findViewById(R.id.cityName);
      itemView.setOnClickListener(view -> listener.onClick(cityNameView.getText().toString()));
    }

    void bind(String name) {
      cityNameView.setText(name);
    }
  }

  ArrayList<String> cities;

  public CityListAdapter(ArrayList<String> cities, ClickHandler listener) {
    this.listener = listener;
    this.cities=cities;
    this.cities.addAll(Settings.getInstance().getLocationMap().values());
  }

  @NonNull
  @Override
  public CityItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new CityItemViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, parent, false),
        listener);
  }

  @Override
  public void onBindViewHolder(@NonNull CityItemViewHolder holder, int position) {
    holder.bind(cities.get(position));
  }

  @Override
  public int getItemCount() {
    return cities.size();
  }

  void addCity(String name) {
    cities.add(name);
    notifyDataSetChanged();
  }

  public List<String> getData(){
    return cities;
  }

  public void clearData(){
    cities.clear();
  }

  void deleteCity(String name) {
    int size = cities.size();
    for (int i = 0; i < size; i++) {
      if (cities.get(i).equalsIgnoreCase(name)) {
        cities.remove(i);
        notifyDataSetChanged();
        return;
      }
    }
  }
}
