package android.ebozkurt.com.cs308ticket.domain;

/**
 * Created by erdem on 2.06.2017.
 */

public class User {

    private int id;
    private String name;
    private String surname;
    private String password;
    private String mail;
    private String address;

    public User(String name, String surname, String password, String mail, String address) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.mail = mail;
        this.address = address;
    }

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public User() {
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
