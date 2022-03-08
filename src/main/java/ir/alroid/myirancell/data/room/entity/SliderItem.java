package ir.alroid.myirancell.data.room.entity;

import android.graphics.drawable.Drawable;

public class SliderItem {
    private String description;
    private String imageUrl;
    private Drawable imgRes;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Drawable getImgRes() {
        return imgRes;
    }

    public void setImgRes(Drawable imgRes) {
        this.imgRes = imgRes;
    }
}
