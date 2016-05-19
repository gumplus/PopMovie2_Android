package com.example.cyp.popmovie2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

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

    public boolean insertMovie (int movieId, String posterUrl, String title, String overview, double vote_average, String release_date) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("movieId", movieId);
        values.put("posterUrl", posterUrl);
        values.put("title", title);
        values.put("overview", overview);
        values.put("vote_average", vote_average);
        values.put("release_date", release_date);

        db.insert("Movie", null, values);
        return  true;
    }

    public Integer deleteMovie (Integer movieId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Movie", "movieId = ?", new String[] { Integer.toString(movieId)});
    }



    public ArrayList<Integer> getAllMovieId() {
        ArrayList<Integer> MovieIdList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //SQLite standard code not android one
        Cursor favorCursor = db.rawQuery("select * from Movie", null);
        favorCursor.moveToFirst();

        while(favorCursor.isAfterLast() ==false) {
            MovieIdList.add(favorCursor.getInt(favorCursor.getColumnIndex("movieId")));
            favorCursor.moveToNext();
        }
        return MovieIdList;

    }

    public ArrayList<String> getAllPosterUrl() {
        ArrayList<String> MovieIdList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //SQLite standard code not android one
        Cursor favorCursor = db.rawQuery("select * from Movie", null);
        favorCursor.moveToFirst();

        while(favorCursor.isAfterLast() ==false) {
            MovieIdList.add(favorCursor.getString(favorCursor.getColumnIndex("posterUrl")));
            favorCursor.moveToNext();
        }
        return MovieIdList;

    }


}
