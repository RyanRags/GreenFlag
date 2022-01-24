package com.example.greenflag30;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logIn = findViewById(R.id.btn_create_account);


        logIn.setOnClickListener(view -> {

            Intent logIn = new Intent();
            logIn.setClass(MainActivity.this, LogIn.class );
            startActivity(logIn);
        });
    }

}


