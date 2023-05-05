package com.bav.ordermanagementsystem.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient mInstance;
    private AppDatabase appDatabase;

    private final String host = "localhost";
    private final String database = "androiddb";
    private final int port = 5432;
    private final String user = "postgres";
    private final String pass = "1234565";

    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "MyDatabase")
                .addCallback(rdc)
                .build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    RoomDatabase.Callback rdc = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            db.execSQL("INSERT INTO `Employee` VALUES(1, 'Test', 'Test', '9271001010', 'employee', 'testtest1', 'testtest1')");
            db.execSQL("INSERT INTO `Employee` VALUES(2, 'Manager', 'Manager', '9271001011', 'manager', 'testtest2', 'testtest2')");
            db.execSQL("INSERT INTO `Client` VALUES(1, 'Test', 'Test', 'Test', 'Test@mail.ru', '9271001011', 'testtest', 'testtest')");
        }
    };
}
