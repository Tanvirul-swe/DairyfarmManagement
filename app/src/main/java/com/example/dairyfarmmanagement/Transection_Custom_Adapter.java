package com.example.dairyfarmmanagement;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class Transection_Custom_Adapter extends ArrayAdapter<Transection_data_halader> {
    private Activity context;
    private List<Transection_data_halader> Transection_data;

    public Transection_Custom_Adapter( Activity context, List<Transection_data_halader>Transection_data) {
        super(context,R.layout.transaction_sample_view, Transection_data);
        this.context = context;
        this.Transection_data = Transection_data;
    }



    @NonNull
    @Override
    public View getView(int position,  View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.transaction_sample_view,null,true);
        Transection_data_halader transection_data_halader = Transection_data.get(position);


        TextView t1 = view.findViewById(R.id.transection_tkid);
        TextView t2 = view.findViewById(R.id.transection_dateId);
        TextView t3= view.findViewById(R.id.noteId);

         t1.setText(transection_data_halader.getTk());
         t2.setText(transection_data_halader.getDate().toString());
         t3.setText(transection_data_halader.getNote().toString());



        return view;
    }

}
