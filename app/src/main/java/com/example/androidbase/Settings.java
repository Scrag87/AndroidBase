package com.example.androidbase;

public class Settings {
  private static Settings uniqueInstance;
  private boolean temperatureInF;
  private boolean windspeedInMph;
  private boolean pressureInPascal;
  private Integer location;

  public Integer getLocation() {
    return location;
  }

  public void setLocation(Integer location) {
    this.location = location;
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

  private Settings() {}

  public static synchronized Settings getInstance() {
    if (uniqueInstance == null) {
      uniqueInstance = new Settings();
    }
    return uniqueInstance;
  }

  @Override
  public String toString() {
    return "Settings{" +
            "temperatureInF=" + temperatureInF +
            ", windspeedInMph=" + windspeedInMph +
            ", pressureInPascal=" + pressureInPascal +
            ", location=" + location +
            '}';
  }
}
