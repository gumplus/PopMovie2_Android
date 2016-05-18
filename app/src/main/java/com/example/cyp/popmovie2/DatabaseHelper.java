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

//        ContentValues values = new ContentValues();
////
////        "id": 207703,
////                "original_language": "en",
////                "original_title": "Kingsman: The Secret Service",
////                "overview": "Kingsman: The Secret Service tells the story of a super-secret spy organization that recruits an unrefined but promising street kid into the agency's ultra-competitive training program just as a global threat emerges from a twisted tech genius.",
////                "release_date": "2015-02-13",
////                "poster_path": "/oAISjx6DvR2yUn9dxj00vP8OcJJ.jpg",
////                "popularity": 17.71923,
////                "title": "Kingsman: The Secret Service",
////                "video": false,
////                "vote_average": 7.7,
////                "vote_count": 1044
//
//        values.put("posterUrl", "http://image.tmdb.org/t/p/w185/oAISjx6DvR2yUn9dxj00vP8OcJJ.jpg");
//        values.put("title", "Kingsman: The Secret Service");
//        values.put("overview", "Kingsman: The Secret Service tells " +
//                "the story of a super-secret spy organization that recruits an unrefined " +
//                "but promising street kid into the agency's ultra-competitive training program " +
//                "just as a global threat emerges from a twisted tech genius.");
//        values.put("vote_average", 7.7);
//        values.put("release_date", "2015-02-13");
//        values.put("movieId", 207703);
//
//        db.insert("Movie", null, values);

        Toast.makeText(mContext, "Database create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Movie");
        onCreate(db);
    }


}
