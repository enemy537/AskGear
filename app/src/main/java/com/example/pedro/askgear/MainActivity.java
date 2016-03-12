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

        /**
         * Receives a boolean variable from child activity for
         * close both intents simultaneously
         */
        if(getIntent().getBooleanExtra("EXIT",false))
            finish();
    }

    /**
     * Create the Content Activity
     * @param view default parameter to onClick methods
     */
    public void newContent(View view){
        Intent intent = new Intent(this,ContentActivity.class);
        startActivity(intent);
    }
}
