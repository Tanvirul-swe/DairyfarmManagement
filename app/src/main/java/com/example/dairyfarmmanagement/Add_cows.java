package com.example.dairyfarmmanagement;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import es.dmoral.toasty.Toasty;

public class Add_cows extends AppCompatActivity implements View.OnClickListener {
    private Spinner spinner;
    String[] breed_name;
    public String breed;
    private ImageView imageView1, imageView2;
    private DatePickerDialog datePicker;
    private EditText editText1, editText2,eartag;
    private ProgressBar progressBar1;
     private Button save_buttom;
     private ListView listView;
     public RadioGroup radioGroup;
     public RadioButton radioButton;
     public String value;
     private ImageView chooseImage;
     private Uri imageUri;
     private String imagelink;
     private static final int Image_request = 1;
     public int count_number_of_cows;
      public int check=0;
     DatabaseReference databaseReference,databaseReference1;
     StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cows);
        databaseReference = FirebaseDatabase.getInstance().getReference("Cow_list");
        databaseReference1 = FirebaseDatabase.getInstance().getReference("Cow_list");
        storageReference = FirebaseStorage.getInstance().getReference("Cow_image");

        breed_name = getResources().getStringArray(R.array.breed_name);
        imageView1 = (ImageView) findViewById(R.id.calendar1);
        editText1 = (EditText) findViewById(R.id.setcalendar1);
        editText2 = (EditText) findViewById(R.id.setcalendar2);
        imageView2 = (ImageView) findViewById(R.id.calendar2);
        progressBar1 = (ProgressBar) findViewById(R.id.progressbarid);
      listView = (ListView) findViewById(R.id.listviewId);
      eartag = (EditText) findViewById(R.id.eartagid);
      chooseImage = (ImageView) findViewById(R.id.add_imageid);

         radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        spinner = (Spinner) findViewById(R.id.breed);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, breed_name);
        spinner.setAdapter(arrayAdapter);

        imageView1.setOnClickListener(this);
        save_buttom = (Button) findViewById(R.id.saveId);
        imageView2.setOnClickListener(this);
        save_buttom.setOnClickListener(this);
        chooseImage.setOnClickListener(this);
        progressBar1.setVisibility(View.GONE);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  breed = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.calendar1:
                int currentYear;
                int currentMonth, currentDay;

                DatePicker datePicker1 = new DatePicker(Add_cows.this);

                currentDay = datePicker1.getDayOfMonth();
                currentMonth = datePicker1.getMonth();
                currentYear = datePicker1.getYear();
                datePicker = new DatePickerDialog(Add_cows.this, new DatePickerDialog.OnDateSetListener() {


                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editText1.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                    }
                }, currentYear, currentMonth, currentDay);
                datePicker.show();
                break;
            case  R.id.calendar2:

                int currentYear1;
                int currentMonth1, currentDay1;
                DatePicker datePicker2 = new DatePicker(Add_cows.this);

                currentDay1 = datePicker2.getDayOfMonth();
                currentMonth1 = datePicker2.getMonth();
                currentYear1 = datePicker2.getYear();
                datePicker = new DatePickerDialog(Add_cows.this, new DatePickerDialog.OnDateSetListener() {


                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editText2.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                    }
                }, currentYear1, currentMonth1, currentDay1);
                datePicker.show();
                break;

            case R.id.saveId:
                save_data();
                Intent intent = new Intent(Add_cows.this,List_of_cows.class);
                progressBar1.setVisibility(View.VISIBLE);
                startActivity(intent);
                break;
            case R.id.add_imageid:
                openFileChooser();
                break;





        }


    }

    private void openFileChooser() {

        Intent intent5 = new Intent();
        intent5.setType("image/*");
        intent5.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent5,Image_request);
        Toasty.info(getApplicationContext(),"Select a image",Toast.LENGTH_LONG,true).show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Image_request && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            imageUri = data.getData();
            Picasso.with(this).load(imageUri).into(chooseImage);

        }
    }
    public String getFileExtension(Uri imageUri)
    {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));
    }




    private void save_data() {

        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        value = radioButton.getText().toString();




        final StorageReference ref = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(imageUri));


        ref.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String tag = eartag.getText().toString().trim();
                        String Birth_date = editText1.getText().toString().trim();
                        String start_dairy = editText2.getText().toString().trim();
                        imagelink = taskSnapshot.getStorage().getDownloadUrl().toString();
                        count_number_of_cows = count_number_of_cows+1;

                        String key = databaseReference.push().getKey();
                        cowdata cowdata1 = new cowdata(tag,Birth_date,start_dairy,breed,value,imagelink,count_number_of_cows);
                        databaseReference.child(key).setValue(cowdata1);
                        User_profile user_profile = new User_profile();
                        user_profile.c=0;
                        Toasty.success(getApplicationContext(),"Successful",Toast.LENGTH_LONG,true).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {

                        // Handle unsuccessful uploads
                        // ...
                    }
                });






    }


}

