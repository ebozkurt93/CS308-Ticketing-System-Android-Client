package android.ebozkurt.com.cs308ticket.domain;

/**
 * Created by erdem on 4.06.2017.
 */

public class Seat {

    private int position;
    private String category;
    private boolean isSold;

    public Seat(int position, String category, boolean isSold) {
        this.position = position;
        this.category = category;
        this.isSold = isSold;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public Seat() {

    }
}
