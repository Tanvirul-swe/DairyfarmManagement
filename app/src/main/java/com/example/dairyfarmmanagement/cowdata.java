package com.example.dairyfarmmanagement;

public class cowdata {

    private String tag,birth_date,start_dairy_date,breed,gender;
    private String imagelink;
    private int number_of_cows;


    public cowdata(){

    }

    public cowdata(String tag, String birth_date, String start_dairy_date,String breed,String gender,String imagelink,int number_of_cows ){
        this.tag = tag;
        this.birth_date = birth_date;
        this.start_dairy_date = start_dairy_date;
        this.breed = breed;
        this.gender = gender;
        this.imagelink=imagelink;
        this.number_of_cows = number_of_cows;

    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getStart_dairy_date() {
        return start_dairy_date;
    }

    public void setStart_dairy_date(String start_dairy_date) {
        this.start_dairy_date = start_dairy_date;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public int getNumber_of_cows() {
        return number_of_cows;
    }

    public void setNumber_of_cows(int number_of_cows) {
        this.number_of_cows = number_of_cows;
    }
}
