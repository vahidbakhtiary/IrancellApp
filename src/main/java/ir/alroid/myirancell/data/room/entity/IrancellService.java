package ir.alroid.myirancell.data.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class IrancellService {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String imgId;

    @ColumnInfo
    private int imgRes;

    public IrancellService() {
    }

    @Ignore
    public IrancellService(int id, String name, String imgId) {
        this.id = id;
        this.name = name;
        this.imgId = imgId;
    }

    @Ignore
    public IrancellService(String name, int imgRes) {
        this.name = name;
        this.imgRes = imgRes;
    }

    @Ignore
    public IrancellService(int imgRes) {
        this.imgRes = imgRes;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
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

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imgId='" + imgId + '\'' +
                '}';
    }

}
