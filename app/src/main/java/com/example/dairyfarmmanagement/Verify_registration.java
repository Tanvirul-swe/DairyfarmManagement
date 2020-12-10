package com.example.dairyfarmmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Verify_registration extends AppCompatActivity {
    private Button button;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_registration);

        button = (Button) findViewById(R.id.SubmitButtonInverifyCodeID);
        editText =(EditText) findViewById(R.id.UserRegoptCodeEditTextId);

       String phone = getIntent().getStringExtra("phone");
       
       sendVerificationCodeToUser(phone);
       
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Verify_registration.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void sendVerificationCodeToUser(String phone) {


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+880" + phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks


    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();

               if(code!= null)
            {

            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }
    };
}