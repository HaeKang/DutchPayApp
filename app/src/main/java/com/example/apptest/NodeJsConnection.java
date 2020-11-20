package com.example.apptest;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// new JSONTask().execute(server_url); 으로 실행


public class NodeJsConnection extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection con = null;
        BufferedReader reader = null;

        try{
            URL url = new URL(strings[0]);

            con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미
            con.connect();

            // 응답 받음
            InputStream stream = con.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";

            while((line = reader.readLine()) != null){
                buffer.append(line);
                Log.d("test", "here" + line);
            }

            return buffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            con.disconnect();
            try {
                if(reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);
    }

}
