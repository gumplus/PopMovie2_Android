package com.example.cyp.popmovie2;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by cyp via apple on 16/5/5.
 */

// a class for the reply of tmdb api  by gson  parse way

public class JsonBean implements Parcelable {

    private int page;
    private ArrayList<Results> results = new ArrayList<>();

    public JsonBean() {

    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }


    public static class Results implements Parcelable {
        public int id;
        public double popularity;
        public double vote_average;
        public String title;
        public String poster_path;
        public String backdrop_path;
        public String overview;
        public String release_date;
        public String original_language;

        public Results() {

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }




        //ArrayList<Results> to be Parcelable

        private Results(Parcel in) {
            this.id = in.readInt();
            this.vote_average = in.readDouble();
            this.title = in.readString();
            this.poster_path = in.readString();
            this.overview = in.readString();
            this.release_date = in.readString();

        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

            dest.writeInt(id);
            dest.writeDouble(vote_average);
            dest.writeString(title);
            dest.writeString(poster_path);
            dest.writeString(overview);
            dest.writeString(release_date);
        }

        //package the inner class of Results to be a CREATOR
        public static final Parcelable.Creator<Results> CREATOR = new Parcelable.Creator<Results>() {
            @Override
            public Results createFromParcel(Parcel source) {
                return new Results(source);
            }

            @Override
            public Results[] newArray(int size) {
                return new Results[size];
            }
        };

        //end of the
    }



    //JsonBean to be Parcelable

    public JsonBean(Parcel in) {
        this.page = in.readInt();
//        this.results = new ArrayList<>();
        in.readTypedList(results, Results.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeTypedList(results);
    }

    public static final Parcelable.Creator<JsonBean> CREATOR = new Parcelable.Creator<JsonBean>() {
        @Override
        public JsonBean createFromParcel(Parcel source) {
            return new JsonBean(source);
        }

        @Override
        public JsonBean[] newArray(int size) {
            return new JsonBean[size];
        }
    };




}
