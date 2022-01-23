package com.example.greenflag30;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogIn extends AppCompatActivity {

    private Button backBtn;
    private EditText email;
    private ConstraintLayout invalidEmail;
    private EditText createPW;
    private EditText confirmPW;
    private ConstraintLayout invalidPW;
    private Button next;
    private ConstraintLayout samePW;
    private ArrayList emails;
    private String passRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$";
    private Pattern pattern;
    private Matcher matcher;
    private EditText changeError;
    private boolean emailCheck;
    private boolean createCheck;
    private boolean confirmCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        email = findViewById(R.id.et_email_addresss);
        invalidEmail = findViewById(R.id.email_not_valid);
        confirmPW = findViewById(R.id.et_confirm_password_box);
        invalidPW = findViewById(R.id.constraintLayout);
        next = findViewById(R.id.btn_next);
        createPW = findViewById(R.id.et_password1);
        samePW = findViewById(R.id.constraintLayout1);
        changeError = findViewById(R.id.editTextTextMultiLine);

        ArrayList<String> emails = new ArrayList<>();

        backBtn.setOnClickListener(view -> {
            finish();
        });


        email.setOnFocusChangeListener((view, b) -> {
            if(!b) {
                if (Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    email.setBackgroundResource(R.drawable.green_box);
                    email.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                    invalidEmail.setVisibility(View.GONE);
                    emailCheck = true;
                } else {
                    email.setBackgroundResource(R.drawable.red_box);
                    email.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.cross, 0);
                    invalidEmail.setVisibility(View.VISIBLE);
                    emailCheck = false;
                }
                button();
            }
            });


            createPW.setOnFocusChangeListener((view, b) -> {
                if(!b){
                    pattern = Pattern.compile(passRegex);
                    matcher = pattern.matcher(createPW.getText().toString());
                    if(matcher.matches()){
                        createPW.setBackgroundResource(R.drawable.green_box);
                        createPW.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.tick,0);
                        changeError.setText(R.string.pWord_notValid);
                        samePW.setVisibility(View.GONE);
                        createCheck = true;
                    } else {
                        createPW.setBackgroundResource(R.drawable.red_box);
                        createPW.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.cross,0);
                        changeError.setText(R.string.pWord_notValid);
                        samePW.setVisibility(View.VISIBLE);
                        createCheck = false;
                    }
                    button();
                }
            });

            confirmPW.setOnFocusChangeListener((view, b) -> {
                if(!b){
                    if(!createPW.getText().toString().trim().isEmpty()){
                        if(confirmPW.getText().toString().equals(createPW.getText().toString())){
                            confirmPW.setBackgroundResource(R.drawable.green_box);
                            confirmPW.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.tick,0);
                            changeError.setText(R.string.no_match);
                            samePW.setVisibility(View.GONE);
                            confirmCheck = true;
                        } else {
                            confirmPW.setBackgroundResource(R.drawable.red_box);
                            confirmPW.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.cross,0);
                            changeError.setText(R.string.no_match);
                            samePW.setVisibility(View.VISIBLE);
                            confirmCheck = false;
                        }
                        button();
                    }
                }
            });

            next.setOnClickListener(view -> {
                emails.add(email.getText().toString());
                Intent mainScreen = new Intent();
                mainScreen.setClass(this, MainScreen.class );
                mainScreen.putExtra("Emails", emails);
                startActivity(mainScreen);
            });
        }

        private void button(){
            if(emailCheck && createCheck && confirmCheck){
                next.setEnabled(true);
                next.setAlpha(1.0f);
            } else {
                next.setEnabled(false);
                next.setAlpha(0.5f);
            }
        }
}
