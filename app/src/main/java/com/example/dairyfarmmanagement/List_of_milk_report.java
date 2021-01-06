package com.example.dairyfarmmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class List_of_milk_report extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    private TextView average,average_of_cows_milk;
    public int sum1,sum2,sum3,count=0,sum5;
    public  float sum4,sum6;
    ArrayAdapter arrayAdapter;
    private Milk_report_adapter milkReportAdapter;
    private List  milk_list;
    private ListView milk_list_view;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_milk_report);
        databaseReference = FirebaseDatabase.getInstance().getReference("Milk_information");
        milk_list = new ArrayList<>();
        milk_list_view = (ListView) findViewById(R.id.milk_listviewID);
        average = (TextView) findViewById(R.id.average_milk_textviewid);
        average_of_cows_milk = (TextView) findViewById(R.id.cow_averageId);
        milkReportAdapter = new Milk_report_adapter(List_of_milk_report.this,milk_list);


        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingId_for_milk_report);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(List_of_milk_report.this,Milk_report.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                milk_list.clear();
                for (DataSnapshot dataSnapshot1:snapshot.getChildren())
                {
                    Milk_data_handaler milkDataHandaler1 = dataSnapshot1.getValue(Milk_data_handaler.class);
                    milk_list.add(milkDataHandaler1);
                    String sum_of_milk = milkDataHandaler1.amount;
                    String cale_sum_milk = milkDataHandaler1.milk_for_calves;
                    String cows = milkDataHandaler1.number_of_cows;
                    sum1 = sum1+Integer.parseInt(sum_of_milk);
                    sum2 = sum2+Integer.parseInt(cale_sum_milk);
                    sum5 = sum5+Integer.parseInt(cows);

                    count++;


                }
                 sum3 = sum1-sum2;
                  sum4 = (float) sum3/count;
                  sum6 = (float) sum3/sum5;
                BigDecimal bd = new BigDecimal(sum6).setScale(2, RoundingMode.HALF_UP);
                BigDecimal bd1 = new BigDecimal(sum4).setScale(2, RoundingMode.HALF_UP);
                  average.setText(String.valueOf(bd1.doubleValue()));
                  average_of_cows_milk.setText(String.valueOf(bd.doubleValue()));
                milk_list_view.setAdapter(milkReportAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
}