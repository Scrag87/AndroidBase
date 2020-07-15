package com.example.androidbase;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass. Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {
  CityListAdapter cityListAdapter;
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public MenuFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of this fragment using the provided
   * parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment MenuFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static MenuFragment newInstance(String param1, String param2) {
    MenuFragment fragment = new MenuFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_menu, container, false);

    ArrayList<String> cities = new ArrayList<>();
    //        Settings.getInstance().getLocationMap().forEach((x, y) -> cities.add(y));
            Log.d("TAG", "onCreateView: " +cities);
    cityListAdapter =
        new CityListAdapter(cities, value -> {
          ((TextView) view.findViewById(R.id.textView)).setText(value);
          Settings.getInstance().setLocation(value);
        });
    RecyclerView recyclerView = view.findViewById(R.id.city_list);
    recyclerView.setAdapter(cityListAdapter);
    recyclerView.setHasFixedSize(true);
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(layoutManager);
    DividerItemDecoration itemDecoration =
        new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
    recyclerView.addItemDecoration(itemDecoration);
    onClickCityAddListener(view);
    onClickCityDeleteListener(view);
//    onClickOkListener(view);
    return view;
  }

  private void onClickCityAddListener(View view) {
    Button addCity = view.findViewById(R.id.button_add);
    addCity.setOnClickListener(view1 -> addCity(view));
  }

  private void onClickOkListener(View view) {
    Button okButton = view.findViewById(R.id.button_ok);
    okButton.setOnClickListener(view1 -> okButtonHandler(view));
  }

  private void onClickCityDeleteListener(View view) {
    Button delCity = view.findViewById(R.id.button_del);
    delCity.setOnClickListener(view1 -> deleteCity(view));
  }

  public void addCity(View view) {
    EditText newCity = view.findViewById(R.id.editText);
    String cityName = newCity.getText().toString();
    if (cityName.isEmpty()) {
      return;
    }
    cityListAdapter.addCity(cityName);
    newCity.setText("");
    Settings.getInstance().addCity(cityName);
  }

  public void deleteCity(View view) {
    TextView city = view.findViewById(R.id.textView);
    String cityName = city.getText().toString();
    if (cityName.isEmpty()) {
      return;
    }
    cityListAdapter.deleteCity(cityName);
    city.setText("");
    Settings.getInstance().delCity(cityName);
  }

  public void okButtonHandler(View view) {
    List<String> data = cityListAdapter.getData();
    cityListAdapter.clearData();
    Settings.getInstance().getLocationMap().clear();
    Log.d("TAG", "okButtonHandler: " +Settings.getInstance().getLocationMap());
    for (int i = 0; i < data.size(); i++) {
      Settings.getInstance().getLocationMap().put(i, data.get(i));
    }
    Log.d("TAG", "okButtonHandler: + " +Settings.getInstance().getLocationMap());

  }
}
