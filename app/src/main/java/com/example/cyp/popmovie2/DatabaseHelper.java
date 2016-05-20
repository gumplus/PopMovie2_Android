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
            + "movieId integer, "
            + "posterUrl String, "
            + "title String, "
            + "overview String, "
            + "vote_average double, "
            + "release_date String)";

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

    public boolean existMovie(Integer movieId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String SQL ="SELECT * FROM Movie WHERE movieId="+movieId+"";
        Cursor cursor = db.rawQuery(SQL, null);


        if(cursor.moveToFirst()) {

                    return  true;
        } else {

            return false;
        }

    }

//    Cursor c = db.rawQuery("select * from user where username=? and password = ?",
//            new Stirng[]{"用户名","密码"});




    public ArrayList<Integer> getAllMovieId() {
        ArrayList<Integer> MovieIdList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        //SQLite standard code not android one
        Cursor favorCursor = db.rawQuery("select * from Movie", null);
        favorCursor.moveToFirst();

        while(favorCursor.isAfterLast() ==false) {
            MovieIdList.add(favorCursor.getInt(favorCursor.getColumnIndex("movieId")));
            favorCursor.moveToNext();
        }
        favorCursor.close();
        return MovieIdList;

    }

    public ArrayList<String> getAllPosterUrl() {
        ArrayList<String> posterUrlList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        //SQLite standard code not android one
        Cursor favorCursor = db.rawQuery("select * from Movie", null);
        favorCursor.moveToFirst();

        while(favorCursor.isAfterLast() ==false) {
            posterUrlList.add(favorCursor.getString(favorCursor.getColumnIndex("posterUrl")));
            favorCursor.moveToNext();
        }
        favorCursor.close();
        return posterUrlList;

    }

    public ArrayList<String> getAllTitle() {
        ArrayList<String> titleList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor favorCursor = db.rawQuery("select * from Movie", null);
        favorCursor.moveToFirst();

        while(favorCursor.isAfterLast() ==false) {
            titleList.add(favorCursor.getString(favorCursor.getColumnIndex("title")));
            favorCursor.moveToNext();
        }
        favorCursor.close();
        return titleList;
    }

    public ArrayList<String> getAllOverview() {
        ArrayList<String> overviewList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor favorCursor = db.rawQuery("select * from Movie", null);
        favorCursor.moveToFirst();

        while(favorCursor.isAfterLast() ==false) {
            overviewList.add(favorCursor.getString(favorCursor.getColumnIndex("overview")));
            favorCursor.moveToNext();
        }
        favorCursor.close();
        return overviewList;
    }


    public ArrayList<Double> getAllvoteAverage() {
        ArrayList<Double> voteAverageList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor favorCursor = db.rawQuery("select * from Movie", null);
        favorCursor.moveToFirst();

        while(favorCursor.isAfterLast() ==false) {
            voteAverageList.add(favorCursor.getDouble(favorCursor.getColumnIndex("vote_average")));
            favorCursor.moveToNext();
        }
        favorCursor.close();
        return voteAverageList;
    }

    public ArrayList<String> getAllReleaseDate() {
        ArrayList<String> releaseDateList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor favorCursor = db.rawQuery("select * from Movie", null);
        favorCursor.moveToFirst();

        while(favorCursor.isAfterLast() ==false) {
            releaseDateList.add(favorCursor.getString(favorCursor.getColumnIndex("release_date")));
            favorCursor.moveToNext();
        }
        favorCursor.close();
        return releaseDateList;
    }



}
