package android.ebozkurt.com.cs308ticket.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by erdem on 4.06.2017.
 */

public class Ticket implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("seatname")
    @Expose
    private String seatName;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("user")
    @Expose
    private User user;

    public Ticket() {
    }

    public Ticket(String seatName, Category category, User user) {
        this.seatName = seatName;
        this.category = category;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
