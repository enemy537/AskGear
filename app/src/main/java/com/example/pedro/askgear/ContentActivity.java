package com.example.pedro.askgear;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import model.ContentGenerator;
import model.Curiosity;

public class ContentActivity extends AppCompatActivity {
    public static final String PATH = "curiosity.json";
    private ContentGenerator contentGenerator;
    private List<Curiosity> contentList;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        random = new Random();

        try {
            contentGenerator = new ContentGenerator(prepareFiles());
            contentList = contentGenerator.getContentList();
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
        TextView textView = (TextView) findViewById(R.id.content_title);
        textView.setText(title);
    }
    private void setText(String text){
        TextView textView = (TextView) findViewById(R.id.content_text);
        textView.setText(text);
    }
    private void setImage(String image){
        ImageView imageView = (ImageView) findViewById(R.id.content_image);
        int imageResource = getResources().getIdentifier(image,null,getPackageName());
        Drawable drawable = getResources().getDrawable(imageResource);
        imageView.setImageDrawable(drawable);
    }
    private void newContent(){
        int index = random.nextInt(contentList.size());
        Curiosity randonContent = contentList.get(index);

        setTitle(randonContent.getTitle());
        setText(randonContent.getText());
        setImage(randonContent.getImage());
    }
    public void getMoreContent(View view){
        newContent();
    }
}
