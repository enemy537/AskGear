package com.example.pedro.askgear;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Pedro on 09/03/2016.
 */
public class RequestContent {
    private URL url;
    private HttpURLConnection httpUrlConnection;
    private String query, httpBase;

    public RequestContent(String httpBase) {
        this.httpBase = httpBase;
    }
    public void setQuery(String query){
        this.query = query;
    }
    public void connect() throws IOException{
        if(!query.isEmpty()){
            URL url;
            try {
                url = new URL(httpBase+query);
                httpUrlConnection = (HttpURLConnection) url.openConnection();
                httpUrlConnection.connect();
                InputStream stream = httpUrlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line;

                while((line = reader.readLine()) != null){
                    buffer.append(line);
                }
                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                httpUrlConnection.disconnect();
            }

        }else{
            throw new IOException("Erro de conexão!");
        }
    }
}
