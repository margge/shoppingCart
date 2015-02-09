package margge.com.shoppingcart.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersistenceHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE Data (name TEXT, price TEXT, stock INTEGER)";

    public PersistenceHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
        db.execSQL("INSERT into Data (name, price, stock) values ('iPhone 4', 500, 50)");
        db.execSQL("INSERT into Data (name, price, stock) values ('iPhone 5c', 420, 20)");
        db.execSQL("INSERT into Data (name, price, stock) values ('iPhone 5s', 410, 13)");
        db.execSQL("INSERT into Data (name, price, stock) values ('iPhone 6', 580, 17)");
        db.execSQL("INSERT into Data (name, price, stock) values ('iPhone 6 Plus', 220, 34)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Nexus 5', 110, 27)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Nexus 7', 250, 55)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Motorola G', 270, 23)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Motorola L', 310, 9)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Motorola X', 480, 34)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Samsung SII ', 900, 12)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Samsung SIII ', 670, 3)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Samsung SV ', 450, 6)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Samsung S5 ', 340, 2)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Samsung SIII ', 290, 15)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Samsung SIII ', 400, 18)");
        db.execSQL("INSERT into Data (name, price, stock) values ('HTC One', 470, 19)");
        db.execSQL("INSERT into Data (name, price, stock) values ('S Note 4', 270, 16)");
        db.execSQL("INSERT into Data (name, price, stock) values ('LG G3', 350, 11)");
        db.execSQL("INSERT into Data (name, price, stock) values ('S Xperia Z3', 330, 2)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Data");
        db.execSQL(sqlCreate);
        db.execSQL("INSERT into Data (name, price, stock) values ('iPhone 4', 500, 50)");
        db.execSQL("INSERT into Data (name, price, stock) values ('iPhone 5c', 420, 20)");
        db.execSQL("INSERT into Data (name, price, stock) values ('iPhone 5s', 410, 13)");
        db.execSQL("INSERT into Data (name, price, stock) values ('iPhone 6', 580, 17)");
        db.execSQL("INSERT into Data (name, price, stock) values ('iPhone 6 Plus', 220, 34)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Nexus 5', 110, 27)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Nexus 7', 250, 55)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Motorola G', 270, 23)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Motorola L', 310, 9)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Motorola X', 480, 34)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Samsung SII ', 900, 12)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Samsung SIII ', 670, 3)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Samsung SV ', 450, 6)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Samsung S5 ', 340, 2)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Samsung SIII ', 290, 15)");
        db.execSQL("INSERT into Data (name, price, stock) values ('Samsung SIII ', 400, 18)");
        db.execSQL("INSERT into Data (name, price, stock) values ('HTC One', 470, 19)");
        db.execSQL("INSERT into Data (name, price, stock) values ('S Note 4', 270, 16)");
        db.execSQL("INSERT into Data (name, price, stock) values ('LG G3', 350, 11)");
        db.execSQL("INSERT into Data (name, price, stock) values ('S Xperia Z3', 330, 2)");
    }
}