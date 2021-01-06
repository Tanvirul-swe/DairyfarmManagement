package com.example.dairyfarmmanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginButton;
    private TextView textView1, textView2, demo;
    private ProgressBar progressBar;
    private EditText id, password;
    public String pass, user_id, email1,Password,ID;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuitem, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Welcome to dairy management");
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User_information");
        textView1 = (TextView) findViewById(R.id.createaccounttextviewID);
        textView2 = (TextView) findViewById(R.id.forgot_password_ID);
        loginButton = (Button) findViewById(R.id.LoginButtonID);
        id = (EditText) findViewById(R.id.loginEditTextID);
        password = (EditText) findViewById(R.id.LoginPasswordEditTextID);
        loginButton.setOnClickListener(this);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        demo = (TextView) findViewById(R.id.test);
        progressBar = (ProgressBar) findViewById(R.id.loginprogressbar);

        progressBar.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.LoginButtonID:

                checkInputValidation();

                break;
            case R.id.forgot_password_ID:

                final EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordresetdialog = new AlertDialog.Builder(v.getContext());
                passwordresetdialog.setTitle("Reset Password?");
                passwordresetdialog.setMessage("Enter email to get reset link");
                passwordresetdialog.setView(resetMail);

                passwordresetdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail = resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toasty.success(getApplicationContext(), "Check Your mail", Toast.LENGTH_LONG,true).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

                    }
                });

                passwordresetdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                passwordresetdialog.create().show();

                break;
            case R.id.createaccounttextviewID:
                Intent intent2 = new Intent(MainActivity.this, registration.class);
                startActivity(intent2);
                break;
        }

    }


    private void checkInputValidation() {
         ID = id.getText().toString();
         Password = password.getText().toString();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    user_data_handle userDataHandle = dataSnapshot.getValue(user_data_handle.class);
                    pass = userDataHandle.getPassword().toString();
                    user_id = userDataHandle.getPhone().toString();



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (ID.equals(user_id)) {
            Intent intent = new Intent(MainActivity.this, User_profile.class);
            Toasty.success(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG, true);
            startActivity(intent);

        } else if (ID.isEmpty() && Password.isEmpty()) {
            id.setError("Empty ID");
            password.setError("Empty password");
            id.requestFocus();
        } else if (ID.equals(user_id) && Password.isEmpty()) {
            password.setError("Empty password");
            id.requestFocus();
        } else if (ID.isEmpty() && Password.equals(pass)) {
            id.setError("Empty ID");
            id.requestFocus();
        } else if (ID != user_id && Password != pass) {
            id.setError("Wrong ID");
            password.setError("Wrong password");
            id.requestFocus();
        } else if (ID.equals(user_id) && Password != pass) {
            password.setError("Wrong password");
            password.requestFocus();
        } else if (ID != user_id && Password.equals(pass)) {
            id.setError("Wrong ID");
            id.requestFocus();
        }




    }





}








