//package com.example.cyp.popmovie2;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by apple on 16/5/17.
// */
//public class MovieBean implements Parcelable {
//
//    private int page;
//    private int total_pages;
//    private int total_results;
//    /**
//     * adult : false
//     * backdrop_path : /dkMD5qlogeRMiEixC4YNPUvax2T.jpg
//     * genre_ids : [28,12,878,53]
//     * id : 135397
//     * original_language : en
//     * original_title : Jurassic World
//     * overview : Twenty-two years after the events of Jurassic Park, Isla Nublar now features a fully functioning dinosaur theme park, Jurassic World, as originally envisioned by John Hammond.
//     * release_date : 2015-06-12
//     * poster_path : /uXZYawqUsChGSj54wcuBtEdUJbh.jpg
//     * popularity : 88.551849
//     * title : Jurassic World
//     * video : false
//     * vote_average : 7.1
//     * vote_count : 435
//     */
//
//    private List<ResultsBean> results;
//
//    public int getPage() {
//        return page;
//    }
//
//    public void setPage(int page) {
//        this.page = page;
//    }
//
//    public int getTotal_pages() {
//        return total_pages;
//    }
//
//    public void setTotal_pages(int total_pages) {
//        this.total_pages = total_pages;
//    }
//
//    public int getTotal_results() {
//        return total_results;
//    }
//
//    public void setTotal_results(int total_results) {
//        this.total_results = total_results;
//    }
//
//    public List<ResultsBean> getResults() {
//        return results;
//    }
//
//    public void setResults(List<ResultsBean> results) {
//        this.results = results;
//    }
//
//    public static class ResultsBean {
//        private boolean adult;
//        private String backdrop_path;
//        private int id;
//        private String original_language;
//        private String overview;
//        private String release_date;
//        private String poster_path;
//        private double popularity;
//        private String title;
//        private double vote_average;
//        private int vote_count;
//
//        public boolean isAdult() {
//            return adult;
//        }
//
//        public void setAdult(boolean adult) {
//            this.adult = adult;
//        }
//
//        public String getBackdrop_path() {
//            return backdrop_path;
//        }
//
//        public void setBackdrop_path(String backdrop_path) {
//            this.backdrop_path = backdrop_path;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getOriginal_language() {
//            return original_language;
//        }
//
//        public void setOriginal_language(String original_language) {
//            this.original_language = original_language;
//        }
//
//        public String getOverview() {
//            return overview;
//        }
//
//        public void setOverview(String overview) {
//            this.overview = overview;
//        }
//
//        public String getRelease_date() {
//            return release_date;
//        }
//
//        public void setRelease_date(String release_date) {
//            this.release_date = release_date;
//        }
//
//        public String getPoster_path() {
//            return poster_path;
//        }
//
//        public void setPoster_path(String poster_path) {
//            this.poster_path = poster_path;
//        }
//
//        public double getPopularity() {
//            return popularity;
//        }
//
//        public void setPopularity(double popularity) {
//            this.popularity = popularity;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public double getVote_average() {
//            return vote_average;
//        }
//
//        public void setVote_average(double vote_average) {
//            this.vote_average = vote_average;
//        }
//
//        public int getVote_count() {
//            return vote_count;
//        }
//
//        public void setVote_count(int vote_count) {
//            this.vote_count = vote_count;
//        }
//
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(this.page);
//        dest.writeInt(this.total_pages);
//        dest.writeInt(this.total_results);
//        dest.writeList(this.results);
//
//    }
//
//    public MovieBean() {
//    }
//
//    protected MovieBean(Parcel in) {
//        this.page = in.readInt();
//        this.total_pages = in.readInt();
//        this.total_results = in.readInt();
//        this.results = new ArrayList<>();
//        in.readList(this.results, ResultsBean.class.getClassLoader());
//    }
//
//    public static final Parcelable.Creator<MovieBean> CREATOR = new Parcelable.Creator<MovieBean>() {
//        @Override
//        public MovieBean createFromParcel(Parcel source) {
//            return new MovieBean(source);
//        }
//
//        @Override
//        public MovieBean[] newArray(int size) {
//            return new MovieBean[size];
//        }
//    };
//}
