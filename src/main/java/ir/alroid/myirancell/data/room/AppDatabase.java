package ir.alroid.myirancell.data.room;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ir.alroid.myirancell.data.room.dao.ProductDao;
import ir.alroid.myirancell.data.room.entity.IrancellService;
import ir.alroid.myirancell.data.room.entity.Product;

@Database(entities = Product.class, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProductDao getProductDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getINSTANCE(Application application) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(application, AppDatabase.class, "myIrancell.db")
// ------------->>  .allowMainThreadQueries()  <<-------------
                    .build();
        }

        return INSTANCE;
    }

}
