package com.example.mahadi.frgmtandrcycle;

import com.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Schedule {

    public String stringToParse;
    private Map<String, Object> map;
    private ArrayList<Object> data;
    private Map<Object, Object> questions;
    private List<String> getAnswerChoices;

    //Code for API calls recieved from https://rapidapi.com/blog/how-to-use-an-api-with-java

    public Schedule(String json) throws IOException {
       String path = "com/example/mahadi/frgmtandrcycle/data.json";
       //File file = new File("C:/Users/Shrey Patel/AndroidStudioProjects/Fragment-with-Recyclerview-/app/src/main/java/com/example/mahadi/frgmtandrcycle/data.json");
       ObjectMapper mapper = new ObjectMapper();
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
            this.stringToParse = responseJSON;
        }








        this.map = mapper.readValue(json, Map.class);

        this.data = (ArrayList<Object>) map.get("events");






    }

    public String getStringToParse() {
        return stringToParse;
    }

    public void setStringToParse(String stringToParse) {
        this.stringToParse = stringToParse;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public ArrayList<Object> getData() {
        return data;
    }

    public void setData(ArrayList<Object> data) {
        this.data = data;
    }

    public Map<Object, Object> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<Object, Object> questions) {
        this.questions = questions;
    }

    public List<String> getGetAnswerChoices() {
        return getAnswerChoices;
    }

    public void setGetAnswerChoices(List<String> getAnswerChoices) {
        this.getAnswerChoices = getAnswerChoices;
    }

    public List<String> getAnswerChoices() {

        return getAnswerChoices;
    }

    /**
     *checks if an answer is correct
     *
     * @param answer the user inputted answer
     * @return true if the answer matches the API given answer, false otherwise
     */

    public boolean checkAnswer(String answer) {

        String correctAnswer = questions.get("correct_answer").toString().toLowerCase();
        answer = answer.toLowerCase();

        return correctAnswer.equals(answer);


    }

    public String getQuestion() {
        return (String) questions.get("question");

    }

    public static void main(String[] args) throws IOException {

        Schedule schedule = new Schedule("asd");
        System.out.println(schedule.data);


    }

}