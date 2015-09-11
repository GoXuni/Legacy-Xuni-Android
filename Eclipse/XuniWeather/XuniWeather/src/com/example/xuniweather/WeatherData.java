package com.example.xuniweather;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.format.DateFormat;

import com.grapecity.xuni.core.*;

public class WeatherData
{
	protected Date date;
	protected String weatherDescription;
	protected Double highTemp;
	protected Double lowTemp;
	protected Double humidity;
	protected Double pressure;


	public WeatherData()
	{
		super();
		this.date = new Date();
		this.weatherDescription = "";
		this.highTemp = 0.0;
		this.lowTemp = 0.0;
		this.humidity = 0.0;
		this.pressure = 0.0;
	}

	public WeatherData(Date date, String weatherDescription,  Double highTemp, Double lowTemp, Double humidity, Double pressure)
	{
		super();
		this.date = date;
		this.weatherDescription = weatherDescription;
		this.highTemp = highTemp;
		this.lowTemp = lowTemp;
		this.humidity = humidity;
		this.pressure = pressure;
	}
	
	
	public Date getDate() { return date; }
	
	public void setDate(Date date) { this.date = date; }
	
	public String getWeatherDescription() { return weatherDescription; }
	
	public void setWeatherDescription(String weatherDescription) { this.weatherDescription = weatherDescription; }
	
	public Double getHighTemp() { return highTemp; }
	
	public void setHighTemp(Double highTemp) { this.highTemp = highTemp; }
	
	public Double getLowTemp() { return lowTemp; }
	
	public void setLowTemp(Double lowTemp) { this.lowTemp = lowTemp; }
	
	public Double getHumidity() { return humidity; }
	
	public void setHumidity(Double humidity) { this.humidity = humidity; }
	
	public Double getPressure() { return pressure;}
	
	public void setPressure(Double pressure) { this.pressure = pressure; }

	
	public ObservableList<WeatherData> getList(String location) 
	{
		ObservableList<WeatherData> list = new ObservableList<WeatherData>();
		String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast?zip=";
		HttpURLConnection con = null ;
		InputStream is = null;
		JSONObject jObj;
		try{
			con = (HttpURLConnection) ( new URL(BASE_URL + location + "&units=imperial")).openConnection();
			con.setRequestMethod("GET");
	        con.setDoInput(true);
			con.setDoOutput(true);
			con.connect();
			StringBuffer buffer = new StringBuffer();
			is = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while (  (line = br.readLine()) != null )
			buffer.append(line + "\r\n");
			is.close();
			con.disconnect();
			jObj = new JSONObject(buffer.toString());
			JSONArray jArr = jObj.getJSONArray("list");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
			for(int i=0; i<jArr.length(); i++){
				JSONObject jDayForecast = jArr.getJSONObject(i);
				WeatherData weather= new WeatherData();
				jDayForecast.getString("dt_txt");
				JSONObject jTempObj = jDayForecast.getJSONObject("main");
				JSONArray jWeatherArr = jDayForecast.getJSONArray("weather");
				JSONObject jWeatherObj = jWeatherArr.getJSONObject(0);
				weather.date = format.parse(jDayForecast.getString("dt_txt"));
				weather.lowTemp = jTempObj.getDouble("temp_min");
				weather.highTemp = jTempObj.getDouble("temp_max");
				weather.pressure = jTempObj.getDouble("pressure");
				weather.humidity= jTempObj.getDouble("humidity");
				weather.weatherDescription = jWeatherObj.getString("description");
				//weather.setDate = jDayForecast.getString("dt");
				list.add(weather);
			}
		}
		catch(Throwable t){
			t.printStackTrace();
		}
		finally{
			try { is.close(); } catch(Throwable t) {}
			try { con.disconnect(); } catch(Throwable t) {}
		}
		
		return list;
	}

}