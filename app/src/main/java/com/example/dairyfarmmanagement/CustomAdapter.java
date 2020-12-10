package com.example.dairyfarmmanagement;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<cowdata> {

    private Activity context;
    private List<cowdata> cowdataList;

    public CustomAdapter( Activity context, List<cowdata> cowdataList) {
        super(context,R.layout.sample_view, cowdataList);
        this.context = context;
        this.cowdataList = cowdataList;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_view,null,true);
        cowdata cowdata1 = cowdataList.get(position);
        TextView t1 = view.findViewById(R.id.tag_textview);
        TextView t2 = view.findViewById(R.id.Breed_textview);
        TextView t3 = view.findViewById(R.id.gender_textview);

        t1.setText("Tag : "+cowdata1.getTag());
        t2.setText("Breed : "+cowdata1.getBreed());
        t3.setText("Gender : "+cowdata1.getGender());

        return view;
    }
}

