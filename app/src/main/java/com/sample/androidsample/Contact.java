package com.sample.androidsample;

import java.util.ArrayList;

/**
 * Created by rajan.kali on 5/9/2017.
 * Model class - Contact
 */

class Contact {
    private String name;
    private String number;
    private String photoUrl;

    private Contact(String name, String number, String photoUrl) {
        this.name = name;
        this.number = number;
        this.photoUrl = photoUrl;
    }

    public Contact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return name+" \n\n "+number+"\n";
    }

    static ArrayList<Contact> getDummyContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Sateesh", "12121212414", "http://www.filmyfolks.com/celebrity/bollywood/images/rohit-sharma.jpg"));
        contacts.add(new Contact("Dheeraj", "12121212414", "http://2.bp.blogspot.com/-WZf8DGei8dY/VT3-r_94OtI/AAAAAAAAjH0/XQ-omKDNwv8/s1600/10-best-virat-kohli-latest-hd-wallpapers-7.jpg"));
        contacts.add(new Contact("Lavith", "12121212414", "http://2.bp.blogspot.com/-u01K6ep51ms/UEMDwldkTTI/AAAAAAAAAA8/BnNUnfCFQ8M/s1600/Mahendra-Singh-Dhoni-Photo.jpg"));
        contacts.add(new Contact("Thanesh", "12121212414", "http://www.cricket.com.au/-/media/News/2014/11/20MikeHussey.ashx"));
        contacts.add(new Contact("Sourabh", "12121212414", "https://s.ndtvimg.com/images/stories/yuvraj-hell412.jpg"));
        contacts.add(new Contact("Rajan", "12121212414", "http://media.santabanta.com/gallery/cricket/suresh%20raina/suresh-raina-15-v.jpg"));
        return contacts;
    }
}
