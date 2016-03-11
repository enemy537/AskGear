package com.example.pedro.askgear;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.ContentGenerator;
import model.Curiosity;

public class ContentActivity extends AppCompatActivity {
    public static final String PATH = "curiosity.json";
    private ContentGenerator contentGenerator;
    private List<Curiosity> contentList;
    private String currentSubject;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        index = 0;

        try {
            contentGenerator = new ContentGenerator(prepareFiles());
            contentList = contentGenerator.getContentList();
            Collections.shuffle(contentList);
            newContent();
        }catch (IOException e){
            Context context = getApplicationContext();
            CharSequence text = "Erro ao importar o dataset.";
            Toast toast = Toast.makeText(context,text,Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private InputStream prepareFiles() throws IOException{
        AssetManager assetManager = this.getAssets();
        InputStream is = assetManager.open(PATH);
        return is;
    }
    private void setTitle(String title){
        currentSubject = title;
        TextView textView = (TextView) findViewById(R.id.content_title);
        textView.setText(title);
    }
    private void setText(String text){
        TextView textView = (TextView) findViewById(R.id.content_text);
        textView.setText(text);
    }
    private void setImage(String image){
        ImageView imageView = (ImageView) findViewById(R.id.content_image);
        int imageResource = getResources().getIdentifier(image,"string",getPackageName());
        Drawable drawable = getResources().getDrawable(imageResource);
        imageView.setImageDrawable(drawable);
    }
    private void newContent(){
        Curiosity randonContent = contentList.get(index);

        if(index == contentList.size()-1)
            index = 0;
        else
            index++;

        setTitle(randonContent.getTitle());
        setText(randonContent.getText());
        setImage(randonContent.getImage());
    }
    public void getMoreContent(View view){
        newContent();
    }
    public void closeAll(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
        finish();
        System.exit(1);
    }
    public void knowMore(View view){
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.setType("*/*");
        intent.setData(Uri.parse(currentSubject));
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }
}
