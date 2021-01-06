package com.example.dairyfarmmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Incomes_Expenses extends AppCompatActivity {
      private FloatingActionButton floatingActionButton;
      ArrayAdapter arrayAdapter;
      private List Transection_data;
      private Transection_Custom_Adapter adapter;
      private ListView listView1;
      private TextView income_amount,expense_textview,Cash_Textview;
      public int i,sum=0,sum1=0,i1,a=0,b=0;
    private String temp1,temp,copy_income_sum,copy_expenses_sum;
      DatabaseReference databaseReference1,databaseReference2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomes__expenses);
        getSupportActionBar().setTitle("Transaction");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseReference1 = FirebaseDatabase.getInstance().getReference("Transaction_data").child("Income");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Transaction_data").child("Expense");

        listView1= (ListView) findViewById(R.id.income_listviewID);
        income_amount = (TextView) findViewById(R.id.incomeID);
        expense_textview = (TextView) findViewById(R.id.expenseId);
        Cash_Textview = (TextView) findViewById(R.id.cashId);
        Transection_data = new ArrayList<>();
        adapter = new Transection_Custom_Adapter(Incomes_Expenses.this,Transection_data);



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




    @Override
    protected void onStart() {


      databaseReference1.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
              for (DataSnapshot dataSnapshot2 : snapshot.getChildren())
              {
                  Transection_data_halader transection_data_halader1 = dataSnapshot2.getValue(Transection_data_halader.class);
                  Transection_data.add(transection_data_halader1);
                    try {
                        i  =Integer.parseInt(transection_data_halader1.getTk().replaceAll("[\\D]",""));
                        sum = sum+i;
                    } catch (NumberFormatException ex)
                    {

                    }


              }
                temp = Integer.toString(sum);
                copy_income_sum = temp;

               income_amount.setText(temp+" \u09F3");

              listView1.setAdapter(adapter);

          }


          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
      });

      databaseReference2.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {

              for (DataSnapshot dataSnapshot2 : snapshot.getChildren())
              {
                  Transection_data_halader transection_data_halader1 = dataSnapshot2.getValue(Transection_data_halader.class);
                  Transection_data.add(transection_data_halader1);
                  i1  =Integer.parseInt(transection_data_halader1.getTk().replaceAll("[\\D]",""));
                  sum1=sum1+i1;
              }
               temp1 = Integer.toString(sum1);
              copy_expenses_sum=temp1;
              expense_textview.setText(temp1+" \u09F3");
              a = Integer.parseInt(copy_income_sum.replaceAll("[\\D]",""));
              b = Integer.parseInt(copy_expenses_sum.replaceAll("[\\D]",""));
              int cash = a-b;
              Cash_Textview.setText(Integer.toString(cash)+" \u09F3");

              listView1.setAdapter(adapter);


          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }



      });


/*
       a = Integer.parseInt(copy_income_sum.replaceAll("[\\D]",""));
       b = Integer.parseInt(copy_expenses_sum.replaceAll("[\\D]",""));
        int cash = a-b;
        String temp3 = Integer.toString(cash);
        Cash_Textview.setText(temp3);

 */

        super.onStart();
    }



}