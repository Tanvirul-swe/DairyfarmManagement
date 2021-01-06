package com.example.dairyfarmmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import es.dmoral.toasty.Toasty;
import io.perfmark.Tag;

public class registration extends AppCompatActivity implements View.OnClickListener {
    private Button nextButton;
    private FirebaseAuth mAuth1;
    private TextView show_user_name;
    private ProgressBar progressBar;
    DatabaseReference databaseReference3;
    private EditText user_name, user_email, farm_name, user_password, confirm_password, phone_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAuth1 = FirebaseAuth.getInstance();
        databaseReference3 = FirebaseDatabase.getInstance().getReference("User_information");
        nextButton = (Button) findViewById(R.id.UserRegNextButtonId);
        user_name = (EditText) findViewById(R.id.UserNameEditTextId);
        user_email = (EditText) findViewById(R.id.UserEmailEditTExtId);
        farm_name = (EditText) findViewById(R.id.farmNameEditTextId);
        user_password = (EditText) findViewById(R.id.UserPasswordEditTextId);
        phone_number = (EditText) findViewById(R.id.UserPhoneNumberEditTextID);
        confirm_password = (EditText) findViewById(R.id.UserConfromPassEdittextId);
        progressBar = (ProgressBar) findViewById(R.id.progressbar1);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        nextButton.setOnClickListener(this);
        show_user_name = (TextView) findViewById(R.id.userid);


    }

    @Override
    public void onClick(View v) {
        progressBar.setVisibility(View.VISIBLE);
        Save_user_data();
        Intent intent = new Intent(registration.this, MainActivity.class);
        startActivity(intent);

    }


    private void Save_user_data() {

        String name = user_name.getText().toString().trim();
        String farm = farm_name.getText().toString().trim();

        String email = user_email.getText().toString().trim();

        String password = user_password.getText().toString().trim();

        String phone = phone_number.getText().toString().trim();

        String key1 = databaseReference3.push().getKey();


        user_data_handle user_data_handle1 = new user_data_handle(name, farm, email, password, phone);
        databaseReference3.child(key1).setValue(user_data_handle1);


    }
}

