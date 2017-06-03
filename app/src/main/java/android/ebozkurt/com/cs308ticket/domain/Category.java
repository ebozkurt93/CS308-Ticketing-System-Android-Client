package android.ebozkurt.com.cs308ticket.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("startSeat")
    @Expose
    private String startSeat;
    @SerializedName("endSeat")
    @Expose
    private String endSeat;
    @SerializedName("name")
    @Expose
    private String name;

    public Category(String startSeat, String endSeat, String name, String price) {
        this.startSeat = startSeat;
        this.endSeat = endSeat;
        this.name = name;
        this.price = price;
    }

    public Category() {
    }

    @SerializedName("price")
    @Expose
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartSeat() {
        return startSeat;
    }

    public void setStartSeat(String startSeat) {
        this.startSeat = startSeat;
    }

    public String getEndSeat() {
        return endSeat;
    }

    public void setEndSeat(String endSeat) {
        this.endSeat = endSeat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", startSeat='" + startSeat + '\'' +
                ", endSeat='" + endSeat + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}