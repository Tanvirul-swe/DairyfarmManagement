package com.example.dairyfarmmanagement;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class Milk_report_adapter extends ArrayAdapter<Milk_data_handaler> {
    private Activity context;
    private List<Milk_data_handaler> milk_list;

    public Milk_report_adapter( Activity context, List<Milk_data_handaler> milk_list) {
        super(context,R.layout.sample_milk_list, milk_list);
        this.context = context;
        this.milk_list = milk_list;

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_milk_list,null,true);
        Milk_data_handaler milkDataHandaler = milk_list.get(position);
        TextView t1 = view.findViewById(R.id.milking_date);
        TextView t2 = view.findViewById(R.id.totel_milk_amountid);
        TextView t3 = view.findViewById(R.id.milk_eat_calve_amountid);
        TextView t4 = view.findViewById(R.id.reminde_milkid);
        t1.setText(milkDataHandaler.getDate());

        t2.setText(milkDataHandaler.getAmount());
       t3.setText(milkDataHandaler.getMilk_for_calves());
       int a = Integer.parseInt(milkDataHandaler.amount);
       int b = Integer.parseInt(milkDataHandaler.milk_for_calves);
       int sub = a-b;
       String value = String.valueOf(sub);
       t4.setText(value);


        return view;
    }
}
