package com.example.cyp.popmovie2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by apple on 16/5/16.
 */


public class FavoriteFragment extends Fragment {

    //   "/movie/{id}/reviews"

    //http://api.themoviedb.org/3/account/{id}/favorite_movies?api_key=###&session_id=###

    private final String api_key = "?api_key=92741aee53714cbe1a7d87fc658bbaad";
    private int movieId;
    private ArrayList<Integer> movieIdFavorList = new ArrayList<>();
    private ArrayList<String> posterUrlList = new ArrayList<>();
    //Api {id} to get a jsonBean
    private String baseIdApi = "http://api.themoviedb.org/3/movie/";
    private String posterBaseUrl = "http://image.tmdb.org/t/p/w185";
    private DatabaseHelper dbHelper;
    private RecyclerView rvFavorite;
    private FavorBean jsonFavor = new FavorBean();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rvFavorite = (RecyclerView) inflater.inflate(R.layout.fragment_movie_list, container, false);
        dbHelper = new DatabaseHelper(getActivity(), "MovieId.db", null, 1);
        movieIdFavorList = getMovieIdList();

        setupRecyclerView(rvFavorite);
        return rvFavorite;
    }



    public static FavoriteFragment newInstance() {
        FavoriteFragment favoriteFragment = new FavoriteFragment();

        return favoriteFragment;
    }

    private ArrayList<Integer> getMovieIdList() {
        ArrayList<Integer> mList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Movie", null, null, null, null, null, null);
        if(cursor.moveToFirst()) {
            do {
                int mId = cursor.getInt(cursor.getColumnIndex("movieId"));
                movieIdFavorList.add(mId);
                String url = cursor.getString(cursor.getColumnIndex("posterUrl"));
                posterUrlList.add(url);
            } while (cursor.moveToNext());
        }
        return mList;
    }

    private void setupRecyclerView(RecyclerView rvFavorite) {

        rvFavorite.setLayoutManager(new LinearLayoutManager(rvFavorite.getContext()));
        rvFavorite.setAdapter(new FavorAdapter(getActivity(), posterUrlList));
    }

    public class FavorAdapter extends RecyclerView.Adapter<FavorAdapter.ViewHolder> {

        private ArrayList<String> mFavorValues;

        public FavorAdapter(Context context, ArrayList<String> movieUrls) {
            mFavorValues = movieUrls;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private View mFavorView;
            private SimpleDraweeView mFavordraweeView;

            public ViewHolder(View view) {
                super(view);
                mFavorView = view;
                mFavordraweeView = (SimpleDraweeView) view.findViewById(R.id.list_image_poster);
            }

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_movie_poster, parent, false);

            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            Uri uri = Uri.parse(mFavorValues.get(position));
            holder.mFavordraweeView.setImageURI(uri);

            holder.mFavorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    //store jsonData into a bundle
                    Bundle bundleToDetail = new Bundle();
                    // store MovieID into a bundle
                    bundleToDetail.putInt("position", position);
                    bundleToDetail.putParcelable("jsonData", jsonFavor);

                    //the normal way to store detailed data into a intent
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtras(bundleToDetail);
                    intent.putExtra("posterUrltodetailpage",mFavorValues.get(position));
//                    intent.putExtra("movie_id_todetailpage",movieIdList.get(position));

                    Log.d("Movie positon is",String.valueOf(position));
                    context.startActivity(intent);


                }
            });


        }

        @Override
        public int getItemCount() {
            return mFavorValues.size();
        }
    }



}
