package com.example.dairyfarmmanagement;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Input_field_exp_and_inco extends AppCompatActivity implements View.OnClickListener {
         private ImageView date;
         private Button save;
         private RadioButton Expense,Income;
         private EditText Amount,Category,Note,editText_date;
         private DatePickerDialog datePicker;
         public RadioButton radioButton1;
         public RadioGroup radioGroup1;
         public String value1;
         DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_field_exp_and_inco);
        databaseReference = FirebaseDatabase.getInstance().getReference("Transaction_data");

        date = (ImageView) findViewById(R.id.date_of_income_or_expense);
        editText_date = (EditText) findViewById(R.id.date_of_income_or_expenseEdittext);
        Amount = (EditText) findViewById(R.id.amount_of_tk);
        Category = (EditText) findViewById(R.id.catagory_of_tk);
        Note = (EditText) findViewById(R.id.noteId);
        save = (Button) findViewById(R.id.save_button);
        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup);
        save.setOnClickListener(this);
        date.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.save_button:
               save_data();
               Intent intent = new Intent(Input_field_exp_and_inco.this,Incomes_Expenses.class);
               startActivity(intent);


               break;

           case R.id.date_of_income_or_expense:
               int currentYear;
               int currentMonth, currentDay;

               DatePicker datePicker1 = new DatePicker(Input_field_exp_and_inco.this);

               currentDay = datePicker1.getDayOfMonth();
               currentMonth = datePicker1.getMonth();
               currentYear = datePicker1.getYear();
               datePicker = new DatePickerDialog(Input_field_exp_and_inco.this, new DatePickerDialog.OnDateSetListener() {


                   @Override
                   public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       editText_date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);



                   }
               }, currentYear, currentMonth, currentDay);
               datePicker.show();
               break;
       }

    }

    private void save_data() {
        int radioId1 = radioGroup1.getCheckedRadioButtonId();
        radioButton1 = findViewById(radioId1);
        value1 = radioButton1.getText().toString().trim();
        String compare_value =new String("Income");

        String tk=Amount.getText().toString().trim();
        String category = Category.getText().toString().trim();
        String note = Note.getText().toString().trim();
        String date_value = editText_date.getText().toString().trim();
        String key2 = databaseReference.push().getKey();

        Transection_data_halader transection_data_halader = new Transection_data_halader(category,note,date_value,tk);
        if(value1.equals(compare_value))
        {

            databaseReference.child("Income").child(key2).setValue(transection_data_halader);

        }
        else
        {

            databaseReference.child("Expense").child(key2).setValue(transection_data_halader);
        }

        

    }


}