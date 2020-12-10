package com.example.dairyfarmmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Forgot_password extends AppCompatActivity implements View.OnClickListener {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        button = (Button) findViewById(R.id.next_Button_for_pass_reset);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Forgot_password.this,Verify_registration.class);
        startActivity(intent);
    }
}