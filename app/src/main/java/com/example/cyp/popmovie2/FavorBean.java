package com.example.cyp.popmovie2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by apple on 16/5/16.
 */

public class FavorBean implements Parcelable {
    private boolean adult;

    private String backdrop_path;

    private int id;

    private String imdb_id;

    private String original_language;

    private String original_title;

    private String overview;

    private double popularity;

    private String poster_path;


    private String release_date;

    private int revenue;

    private int runtime;

    private String title;

    private boolean video;

    private double vote_average;

    private int vote_count;

    public void setAdult(boolean adult){
        this.adult = adult;
    }
    public boolean getAdult(){
        return this.adult;
    }
    public void setBackdrop_path(String backdrop_path){
        this.backdrop_path = backdrop_path;
    }
    public String getBackdrop_path(){
        return this.backdrop_path;
    }


    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setImdb_id(String imdb_id){
        this.imdb_id = imdb_id;
    }
    public String getImdb_id(){
        return this.imdb_id;
    }
    public void setOriginal_language(String original_language){
        this.original_language = original_language;
    }
    public String getOriginal_language(){
        return this.original_language;
    }
    public void setOriginal_title(String original_title){
        this.original_title = original_title;
    }
    public String getOriginal_title(){
        return this.original_title;
    }
    public void setOverview(String overview){
        this.overview = overview;
    }
    public String getOverview(){
        return this.overview;
    }
    public void setPopularity(double popularity){
        this.popularity = popularity;
    }
    public double getPopularity(){
        return this.popularity;
    }
    public void setPoster_path(String poster_path){
        this.poster_path = poster_path;
    }
    public String getPoster_path(){
        return this.poster_path;
    }

    public void setRelease_date(String release_date){
        this.release_date = release_date;
    }
    public String getRelease_date(){
        return this.release_date;
    }
    public void setRevenue(int revenue){
        this.revenue = revenue;
    }
    public int getRevenue(){
        return this.revenue;
    }
    public void setRuntime(int runtime){
        this.runtime = runtime;
    }
    public int getRuntime(){
        return this.runtime;
    }


    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setVideo(boolean video){
        this.video = video;
    }
    public boolean getVideo(){
        return this.video;
    }
    public void setVote_average(double vote_average){
        this.vote_average = vote_average;
    }
    public double getVote_average(){
        return this.vote_average;
    }
    public void setVote_count(int vote_count){
        this.vote_count = vote_count;
    }
    public int getVote_count(){
        return this.vote_count;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeString(this.backdrop_path);
        dest.writeInt(this.id);
        dest.writeString(this.imdb_id);
        dest.writeString(this.original_language);
        dest.writeString(this.original_title);
        dest.writeString(this.overview);
        dest.writeDouble(this.popularity);
        dest.writeString(this.poster_path);
        dest.writeString(this.release_date);
        dest.writeInt(this.revenue);
        dest.writeInt(this.runtime);
        dest.writeString(this.title);
        dest.writeByte(this.video ? (byte) 1 : (byte) 0);
        dest.writeDouble(this.vote_average);
        dest.writeInt(this.vote_count);
    }

    public FavorBean() {
    }

    protected FavorBean(Parcel in) {
        this.adult = in.readByte() != 0;
        this.backdrop_path = in.readString();
        this.id = in.readInt();
        this.imdb_id = in.readString();
        this.original_language = in.readString();
        this.original_title = in.readString();
        this.overview = in.readString();
        this.popularity = in.readDouble();
        this.poster_path = in.readString();
        this.release_date = in.readString();
        this.revenue = in.readInt();
        this.runtime = in.readInt();
        this.title = in.readString();
        this.video = in.readByte() != 0;
        this.vote_average = in.readDouble();
        this.vote_count = in.readInt();
    }

    public static final Parcelable.Creator<FavorBean> CREATOR = new Parcelable.Creator<FavorBean>() {
        @Override
        public FavorBean createFromParcel(Parcel source) {
            return new FavorBean(source);
        }

        @Override
        public FavorBean[] newArray(int size) {
            return new FavorBean[size];
        }
    };
}