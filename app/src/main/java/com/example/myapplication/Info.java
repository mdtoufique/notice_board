package com.example.myapplication;

public class Info {
    String Mail;
    String Time;
    String Chk;
    public Info(String chk, String mail, String time) {
        Mail = mail;
        Time = time;
        Chk=chk;
    }
    public Info() {

    }
    public String getMail() {
        return Mail;
    }
    public void setMail(String mail) {
        Mail = mail;
    }
    public String getChk() {
        return Chk;
    }

    public void setChk(String chk) {
        Chk = chk;
    }



    public String getTime() {
        return Time;
    }

    public void setTime(String time) {

        Time = time;
    }
}
