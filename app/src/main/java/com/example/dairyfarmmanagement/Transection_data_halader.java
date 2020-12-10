package com.example.dairyfarmmanagement;

public class Transection_data_halader {
    private String category,note,date,tk;


    public Transection_data_halader(String category, String note, String date, String tk) {
        this.category = category;
        this.note = note;
        this.date = date;
        this.tk = tk;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }
}
