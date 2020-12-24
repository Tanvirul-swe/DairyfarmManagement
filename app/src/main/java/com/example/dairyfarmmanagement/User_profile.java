package com.example.dairyfarmmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class User_profile extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

     private CardView sikc_Cardview;
     private TextView username,dairyid;
     DatabaseReference databaseReference,databaseReference1;
     public String value,id;
     private TextView number_fo_cows;
      public int count=0;
      public int c;

     public BottomNavigationView nav_bottom,notification,milkreport,budget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
         databaseReference = FirebaseDatabase.getInstance().getReference("User_information");
        databaseReference1 = FirebaseDatabase.getInstance().getReference("Cow_list");
        nav_bottom = (BottomNavigationView) findViewById(R.id.botton_nev);


        nav_bottom.setOnNavigationItemSelectedListener(this);
         username = (TextView) findViewById(R.id.userid);
         dairyid = (TextView) findViewById(R.id.dairyId);
         number_fo_cows = (TextView) findViewById(R.id.number_of_cowsid);


         Bundle bundle = getIntent().getExtras();
         if(bundle!=null)
         {
             String value = bundle.getString("key");
             username.setText(value);
         }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){

            case R.id.addcow:
                Intent intent = new Intent(User_profile.this,List_of_cows.class);
                startActivity(intent);
                break;
            case R.id.budget:
                Intent intent1 = new Intent(User_profile.this,Incomes_Expenses.class);
                startActivity(intent1);
                 break;

        }
        return false;
    }


    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot1 : snapshot.getChildren())
                {
                      demo demo1 = dataSnapshot1.getValue(demo.class);

                     value = demo1.getName().toString();



                }

                username.setText(value);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
     // Add_cows add_cows = new Add_cows();
     //   c = add_cows.check;

        if(c==0)
        {
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot snap : snapshot.getChildren())
                    {
                        count++;
                    }

                    String temp = Integer.toString(count);
                    number_fo_cows.setText(temp);

                   c++;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }


        super.onStart();
    }




}