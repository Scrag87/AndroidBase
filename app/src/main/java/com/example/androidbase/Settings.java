package com.example.androidbase;

import java.util.HashMap;
import java.util.Map;

public class Settings {
  private static volatile Settings uniqueInstance;
  private boolean temperatureInF;
  private boolean windspeedInMph;
  private boolean pressureInPascal;
  private ThemeColor themeColor;

  public ThemeColor getThemeColor() {
    return themeColor;
  }

  public void setThemeColor(ThemeColor themeColor) {
    this.themeColor = themeColor;
  }

  private Integer location;
  private final Map<Integer, String> locationMap;


  public Map<Integer, String> getLocationMap() {
    return locationMap;
  }


  public Integer getLocation() {
    return location;
  }

  public void setLocation(Integer location , String locationName) {
    this.location = location;
    this.locationMap.put(location,locationName);
  }

  public boolean isTemperatureInF() {
    return temperatureInF;
  }

  public void setTemperatureInF(boolean temperatureInF) {
    this.temperatureInF = temperatureInF;
  }

  public boolean isWindspeedInMph() {
    return windspeedInMph;
  }

  public void setWindspeedInMph(boolean windspeedInMph) {
    this.windspeedInMph = windspeedInMph;
  }

  public boolean isPressureInPascal() {
    return pressureInPascal;
  }

  public void setPressureInPascal(boolean pressureInPascal) {
    this.pressureInPascal = pressureInPascal;
  }

  private Settings() {
    this.temperatureInF = true;
    this.windspeedInMph = false;
    this.pressureInPascal = false;
    this.location = 0;
    this.locationMap = new HashMap<>();
    this.themeColor = ThemeColor.BRIGHT;
  }

  public static synchronized Settings getInstance() {
    if (uniqueInstance == null) {
      uniqueInstance = new Settings();
    }
    return uniqueInstance;
  }


  @Override
  public String toString() {
    return "Settings{"
        + "temperatureInF="
        + temperatureInF
        + ", windspeedInMph="
        + windspeedInMph
        + ", pressureInPascal="
        + pressureInPascal
        + ", location="
        + location
        + '}';
  }
}
