package com.example.dairyfarmmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Incomes_Expenses extends AppCompatActivity {
      private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomes__expenses);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.addtkId);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                switch (id){

                    case R.id.addtkId:
                        Intent intent = new Intent(Incomes_Expenses.this,Input_field_exp_and_inco.class);
                        startActivity(intent);
                }
            }
        });
    }
}