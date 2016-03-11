package com.example.pedro.askgear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().getBooleanExtra("EXIT",false))
            finish();
    }
    public void newContent(View view){
        Intent intent = new Intent(this,ContentActivity.class);
        startActivity(intent);
    }
}
