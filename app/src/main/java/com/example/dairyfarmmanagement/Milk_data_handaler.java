package com.example.dairyfarmmanagement;

public class Milk_data_handaler {

    String amount,number_of_cows,milk_for_calves,date;

    public Milk_data_handaler()
    {

    }

    public Milk_data_handaler(String amount, String number_of_cows, String milk_for_calves, String date) {
        this.amount = amount;
        this.number_of_cows = number_of_cows;
        this.milk_for_calves = milk_for_calves;
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNumber_of_cows() {
        return number_of_cows;
    }

    public void setNumber_of_cows(String number_of_cows) {
        this.number_of_cows = number_of_cows;
    }

    public String getMilk_for_calves() {
        return milk_for_calves;
    }

    public void setMilk_for_calves(String milk_for_calves) {
        this.milk_for_calves = milk_for_calves;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
