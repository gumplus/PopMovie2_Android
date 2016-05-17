package com.example.cyp.popmovie2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by apple on 16/5/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;

    public static final String CREATE_MovieBase = "create table Movie ("
            + "id integer primary key autoincrement, "
            + "posterUrl String, "
            + "title String, "
            + "overview String, "
            + "vote_average double, "
            + "release_date String, "
            + "movieId integer)";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version) {
        super(context, name ,cursorFactory, version);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MovieBase);
        Toast.makeText(mContext, "Database create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Movie");
        onCreate(db);
    }


}
