package com.jones.newsapp.data.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.jones.newsapp.data.api.NewsDao;
import com.jones.newsapp.model.News;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {News.class}, version = 1, exportSchema = false)
public abstract class NewsRoomDatabase extends RoomDatabase {

    public static final int NUMBER_OF_THREADS = 2;
    public static volatile NewsRoomDatabase INSTANCE;
    public static ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract NewsDao newsDao();

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    public static NewsRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (NewsRoomDatabase.class) {
                INSTANCE = Room.databaseBuilder(context, NewsRoomDatabase.class, "fav_database")
                        .addCallback(callback)
                        .build();
            }
        }
        return INSTANCE;
    }

}
