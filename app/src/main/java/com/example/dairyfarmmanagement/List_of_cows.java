package com.example.dairyfarmmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class List_of_cows extends AppCompatActivity {
      private FloatingActionButton floatingActionButton;
      ArrayAdapter arrayAdapter;
      private ListView listView;
      private List cowdataList;
      private CustomAdapter customAdapter;
      DatabaseReference databaseReference;
       private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_cows);
        getSupportActionBar().setTitle("Cows List");
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseReference = FirebaseDatabase.getInstance().getReference("Cow_list");
          cowdataList = new ArrayList<>();

          customAdapter = new CustomAdapter(List_of_cows.this,cowdataList);

      searchView = (SearchView) findViewById(R.id.searchviewid);
      listView = (ListView) findViewById(R.id.listviewId);



     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             Toasty.success(getApplicationContext(),"Clicked",Toast.LENGTH_LONG,true).show();


         }
     });

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingId);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(List_of_cows.this,Add_cows.class);
                startActivity(intent);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                    

                return false;
            }
        });



    }

    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                cowdataList.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren())
                {
                    cowdata cowdata2 = dataSnapshot1.getValue(cowdata.class);
                    cowdataList.add(cowdata2);
                }
                listView.setAdapter(customAdapter);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
}