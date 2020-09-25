package com.example.mahadi.frgmtandrcycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by Mahadi on 3/7/2018.
 */

public class Scrollable extends Fragment {

    View v;
    RecyclerView recyclerView;
    List<Card> listCont;
    Schedule schedule;

    public Scrollable() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.contact_frmt,container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.contact_recycleView);
        RecycleViewAdapter viewAdapter = new RecycleViewAdapter(getContext(), listCont);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(viewAdapter);
        return v;
    }

     @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
         String json = null;
         String theJson = null;
         try {
             InputStream is = getContext().getAssets().open("data.json");
             int size = is.available();
             byte[] buffer = new byte[size];
             is.read(buffer);
             is.close();
             json = new String(buffer, "UTF-8");


             URL url;
             StringBuffer response = new StringBuffer();
             try {
                 url = new URL("https://api.hackillinois.org/event/");
             } catch (MalformedURLException e) {
                 throw new IllegalArgumentException("invalid url");
             }

             HttpURLConnection conn = null;
             try {
                 conn = (HttpURLConnection) url.openConnection();
                 conn.setDoOutput(false);
                 conn.setDoInput(true);
                 conn.setUseCaches(false);
                 conn.setRequestMethod("GET");
                 conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

                 // handle the response
                 int status = conn.getResponseCode();
                 if (status != 200) {
                     throw new IOException("Post failed with error code " + status);
                 } else {
                     BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                     String inputLine;
                     while ((inputLine = in.readLine()) != null) {
                         response.append(inputLine);
                     }
                     in.close();
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             } finally {
                 if (conn != null) {
                     conn.disconnect();
                 }

                 //Here is your json in string format
                 String responseJSON = response.toString();
                 theJson = responseJSON;
                 schedule = new Schedule(json);
             }




         } catch (IOException ex) {
             ex.printStackTrace();
         }


         listCont = new ArrayList<>();
         super.onCreate(savedInstanceState);


         for (Object e : schedule.getData()) {
             Map<Object, Object> tempMap = (Map) e;

             String  tempTime = (String) tempMap.get("description");
             int time = (int) tempMap.get("startTime");
             int endTime = (int) tempMap.get("endTime");
             String timer = "" + time;
             String endTimer = "" + endTime;
             SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
             Date dateFormat = new java.util.Date(endTime * 1000);
             Date dateFormateend = new java.util.Date(time * 1000);
             String weekday = sdf.format(dateFormat );
             String startDay = sdf.format((dateFormateend));


             //Unix seconds
             long unix_seconds = time;
             //convert seconds to milliseconds
             Date date = new Date(unix_seconds*1000L);
             // format of the date
             SimpleDateFormat jdf = new SimpleDateFormat("HH:mm:ss");
             jdf.setTimeZone(TimeZone.getTimeZone("GMT-5"));
             String java_date = sdf.format(date);
             int duration = (int)(endTime - time)/60;
             String timing = jdf.format(date);

             if (java_date.contains("Sat")){
                 listCont.add(new Card(((String) tempMap.get("name")), (String) tempMap.get("description"), R.drawable.ic_launcher_background, "Duration: "+ duration + " minutes at "+ timing));
             }






         }


         //listCont.add(new Contact("Mahadi Hasan", "01717677540", R.drawable.ic_launcher_background,"sdfs"));


    }
}
