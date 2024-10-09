package com.example.praktikum3;

public class HelperClass {
    String nama, nim, pass, conf_pass, email;
    public HelperClass(String nama, String nim, String email, String pass, String conf_pass) {
        this.nama = nama;
        this.nim = nim;
        this.email = email;
        this.pass = pass;
        this.conf_pass = conf_pass;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConf_pass() {
        return conf_pass;
    }

    public void setConf_pass(String conf_pass) {
        this.conf_pass = conf_pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
