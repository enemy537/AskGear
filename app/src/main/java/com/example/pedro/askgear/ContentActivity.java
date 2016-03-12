package com.example.pedro.askgear;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.ContentGenerator;
import model.Curiosity;

public class ContentActivity extends AppCompatActivity {
    // Path definition
    public static final String PATH = "curiosity.json";
    // Objects definition
    private ContentGenerator contentGenerator;
    private List<Curiosity> contentList;
    private Curiosity currentSubject;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        // Index for Curiosity list instantiation
        index = 0;

        try {
            // Try to create ContentGenerator object
            contentGenerator = new ContentGenerator(prepareFiles());
            // Create the curiosity list and randomize it
            contentList = contentGenerator.getContentList();
            Collections.shuffle(contentList);
            // Set curiosity content for the first use
            newContent();
            // If an error occur, show a Toast message
        }catch (IOException e){
            Context context = getApplicationContext();
            CharSequence text = "Erro ao importar o dataset.";
            Toast toast = Toast.makeText(context,text,Toast.LENGTH_LONG);
            toast.show();
        }
    }

    /**
     * Pass the path to JSON
     * @return InputStream containing the path to JSON file
     * @throws IOException if file not exist or contained errors
     */
    private InputStream prepareFiles() throws IOException{
        AssetManager assetManager = this.getAssets();
        InputStream is = assetManager.open(PATH);
        return is;
    }

    /**
     * Set title in activity content
     * @param title curiosity title
     */
    private void setTitle(String title){
        TextView textView = (TextView) findViewById(R.id.content_title);
        textView.setText(title);
    }

    /**
     * Set content text in activity content
     * @param text curiosity content text
     */
    private void setText(String text){
        TextView textView = (TextView) findViewById(R.id.content_text);
        textView.setText(text);
    }
    /**
     * Set descritive image in activity content
     * @param image curiosity image path
     * The path must be converted to Drawable
     */
    private void setImage(String image){
        ImageView imageView = (ImageView) findViewById(R.id.content_image);
        int imageResource = getResources().getIdentifier(image, "String", getPackageName());
        Log.v("ContentActivity", imageResource + "");
        Drawable drawable = getResources().getDrawable(imageResource);
        imageView.setImageDrawable(drawable);
    }

    /**
     * Prepare variable and list position for curiosity
     */
    private void newContent(){
        if(index == contentList.size()-1)
            index = 0;
        else
            index++;

        currentSubject = contentList.get(index);

        setTitle(currentSubject.getTitle());
        setText(currentSubject.getText());
        setImage(currentSubject.getImage());
    }

    /**
     * Action to change content
     * @param view default parameter for onClick methods
     */
    public void getMoreContent(View view){
        newContent();
    }
    /**
     * Action to close all intents and quit application
     * @param view default parameter for onClick methods
     */
    public void closeAll(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
        finish();
        System.exit(1);
    }
    /**
     * Pass string query to the browser for more information
     * @param view default parameter for onClick methods
     */
    public void knowMore(View view) {
        try {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, currentSubject.getQuery());
            startActivity(intent);
        }catch (Exception e){
            Context context = getApplicationContext();
            CharSequence charSequence = "Erro ao pesquisar conteúdo";
            Toast toast = Toast.makeText(context,charSequence,Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
