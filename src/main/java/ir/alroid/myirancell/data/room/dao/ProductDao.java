package ir.alroid.myirancell.data.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ir.alroid.myirancell.data.room.entity.Product;

@Dao
public interface ProductDao {

    @Insert
    void insert(Product product);

    @Delete
    void delete(Product product);

    @Update
    void update(Product product);

    @Query("SELECT * FROM Product ORDER BY id DESC")
    LiveData<List<Product>> select();

    @Query("SELECT * FROM Product WHERE id LIKE :id LIMIT 1")
    Product selectById(int id);

    @Query("UPDATE product SET day = :day , gigabyte = :gigabyte , price = :price WHERE id = :id")
    void updateProduct(int id, String day, String gigabyte, String price);
}
