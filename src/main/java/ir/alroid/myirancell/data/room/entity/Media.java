package ir.alroid.myirancell.data.room.entity;

import android.graphics.drawable.Drawable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Media {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String tittle;

    @ColumnInfo
    private String imgId;

    private Drawable imgDrawable;

    public Media() {
    }

    public Media(int id, String tittle, String imgId) {
        this.id = id;
        this.tittle = tittle;
        this.imgId = imgId;
    }

    public Media(String tittle, String imgId) {
        this.tittle = tittle;
        this.imgId = imgId;
    }

    public Media(Drawable imgDrawable) {
        this.imgDrawable = imgDrawable;
    }

    public Drawable getImgDrawable() {
        return imgDrawable;
    }

    public void setImgDrawable(Drawable imgDrawable) {
        this.imgDrawable = imgDrawable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", tittle='" + tittle + '\'' +
                ", imgId='" + imgId + '\'' +
                '}';
    }
}
