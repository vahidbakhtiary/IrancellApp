package ir.alroid.myirancell.data.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Product {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String day;

    @ColumnInfo
    private String gigabyte;

    @ColumnInfo
    private String price;

    public Product() {
    }

    @Ignore
    public Product(int id, String day, String gigabyte, String price) {
        this.id = id;
        this.day = day;
        this.gigabyte = gigabyte;
        this.price = price;
    }

    @Ignore
    public Product(String day, String gigabyte, String price) {
        this.day = day;
        this.gigabyte = gigabyte;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getGigabyte() {
        return gigabyte;
    }

    public void setGigabyte(String gigabyte) {
        this.gigabyte = gigabyte;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", gigabyte='" + gigabyte + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
