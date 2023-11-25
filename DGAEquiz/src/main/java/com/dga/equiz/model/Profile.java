package com.dga.equiz.model;

public class Profile {
    private int ID;

    private String name;

    private String mail;

    private String dob;

    private String phone;

    private String github;

    private byte[] linkAva;

    private String password;

    private String username;
    private int score;

    private int currentCampaign;

    public int getCurrentCampaign() {
        return currentCampaign;
    }

    public void setCurrentCampaign(int currentCampaign) {
        this.currentCampaign = currentCampaign;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getLinkAva() {
        return linkAva;
    }

    public void setLinkAva(byte[] linkAva) {
        this.linkAva = linkAva;
    }
}