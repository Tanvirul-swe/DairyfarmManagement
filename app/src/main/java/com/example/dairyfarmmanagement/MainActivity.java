package com.example.dairyfarmmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
  private Button loginButton;
  private TextView textView1 ,textView2,demo;
  private ProgressBar progressBar;
  int count = 0;
  DatabaseReference databaseReference;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuitem,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Welcome to dairy management");
            textView1 = (TextView) findViewById(R.id.createaccounttextviewID);
            textView2 = (TextView) findViewById(R.id.forgot_password_ID);
        loginButton = (Button) findViewById(R.id.LoginButtonID);
         loginButton.setOnClickListener(this);
         textView1.setOnClickListener(this);
         textView2.setOnClickListener(this);
         demo = (TextView) findViewById(R.id.test);
         progressBar = (ProgressBar) findViewById(R.id.loginprogressbar);

         progressBar.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onClick(View v) {

             switch (v.getId())
             {
                 case R.id.LoginButtonID:

                    Intent intent = new Intent(MainActivity.this,User_profile.class);
                    startActivity(intent);

                     break;
                 case R.id.forgot_password_ID:
                     Intent intent1 = new Intent(MainActivity.this,Forgot_password.class);
                     startActivity(intent1);
                     break;
                 case R.id.createaccounttextviewID:
                     Intent intent2 = new Intent(MainActivity.this,registration.class);
                     startActivity(intent2);
                     break;
             }

    }





    }





