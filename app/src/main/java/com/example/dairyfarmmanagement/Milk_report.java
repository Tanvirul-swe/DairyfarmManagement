package com.example.dairyfarmmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import es.dmoral.toasty.Toasty;

public class Milk_report extends AppCompatActivity implements View.OnClickListener {
      private Button save;
      private EditText milk_for_calve,date,amount_of_milk,number_of_cows;
      private ImageView imageView;
      private DatePickerDialog datePicker;
      DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milk_report);
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseReference = FirebaseDatabase.getInstance().getReference("Milk_information");

        save = (Button) findViewById(R.id.milk_report_save_buttonId);
        milk_for_calve = (EditText) findViewById(R.id.use_of_calvesId);
        amount_of_milk = (EditText) findViewById(R.id.milk_amountid);
        number_of_cows = (EditText) findViewById(R.id.number_of_milking_cowsId);
        date = (EditText) findViewById(R.id.date_of_milkingId);
        imageView = (ImageView) findViewById(R.id.dateId);
        save.setOnClickListener(this);
        imageView.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.dateId:
                int currentYear;
                int currentMonth, currentDay;

                DatePicker datePicker1 = new DatePicker(Milk_report.this);

                currentDay = datePicker1.getDayOfMonth();
                currentMonth = datePicker1.getMonth();
                currentYear = datePicker1.getYear();
                datePicker = new DatePickerDialog(Milk_report.this, new DatePickerDialog.OnDateSetListener() {


                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);



                    }
                }, currentYear, currentMonth, currentDay);
                datePicker.show();
                break;

            case R.id.milk_report_save_buttonId:
                       save_data();
                Intent intent = new Intent(Milk_report.this,List_of_milk_report.class);
                startActivity(intent);
                break;

        }

    }

    public void save_data()
    {
        String calves_for_milk = milk_for_calve.getText().toString().trim();
        String amount = amount_of_milk.getText().toString().trim();
        String cows = number_of_cows.getText().toString().trim();
        String date_milk = date.getText().toString().trim();

        String key = databaseReference.push().getKey();
        Milk_data_handaler milk_data_handaler = new Milk_data_handaler(amount,cows,calves_for_milk,date_milk);
        databaseReference.child(key).setValue(milk_data_handaler);
        Toasty.success(getApplicationContext(),"Successful", Toast.LENGTH_LONG,true).show();



    }


}