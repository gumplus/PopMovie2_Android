package com.example.cyp.popmovie2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by apple on 16/5/16.
 */


public class FavoriteFragment extends Fragment  {

    //   "/movie/{id}/reviews"

    //http://api.themoviedb.org/3/account/{id}/favorite_movies?api_key=###&session_id=###

    private static final String api_key = "?api_key=92741aee53714cbe1a7d87fc658bbaad";

    private ArrayList<Integer> movieIdList;
    private ArrayList<String> posterUrlList;
    private ArrayList<String> titleList;
    private ArrayList<String> overviewList;
    private ArrayList<Double> voteList;
    private ArrayList<String> releaseList;

    //Api {id} to get a jsonBean
    private String baseIdApi = "http://api.themoviedb.org/3/movie/";
    private String posterBaseUrl = "http://image.tmdb.org/t/p/w185";
    private DatabaseHelper dbFavorHelper;
    private RecyclerView rvFavorite;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rvFavorite = (RecyclerView) inflater.inflate(R.layout.fragment_movie_list, container, false);

        movieIdList = new ArrayList<>();
        posterUrlList = new ArrayList<>();
        titleList = new ArrayList<>();
        overviewList = new ArrayList<>();
        voteList = new ArrayList<>();
        releaseList = new ArrayList<>();

        dbFavorHelper = new DatabaseHelper(getActivity(), "Movie.db", null, 1);

        movieIdList = dbFavorHelper.getAllMovieId();
        posterUrlList = dbFavorHelper.getAllPosterUrl();
        titleList = dbFavorHelper.getAllTitle();
        overviewList = dbFavorHelper.getAllOverview();
        voteList = dbFavorHelper.getAllvoteAverage();
        releaseList = dbFavorHelper.getAllReleaseDate();



        setupRecyclerView(rvFavorite);
        return rvFavorite;
    }



    public static FavoriteFragment newInstance() {
        FavoriteFragment favoriteFragment = new FavoriteFragment();

        return favoriteFragment;
    }


    private void setupRecyclerView(RecyclerView rvFavorite) {

        rvFavorite.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvFavorite.setAdapter(new FavorAdapter(getActivity(), posterUrlList));
    }

    public class FavorAdapter extends RecyclerView.Adapter<FavorAdapter.ViewHolder> {

        private TypedValue mTypeValue = new TypedValue();
        private int mBackground;
        private ArrayList<String> mFavorValues;

        public FavorAdapter(Context context, ArrayList<String> movieUrls) {

            mFavorValues = movieUrls;
            //get Theme
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypeValue, true);
            //get the resourceId of background
            mBackground = mTypeValue.resourceId;
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
            view.setBackgroundResource(mBackground);
            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            Uri uri = Uri.parse(mFavorValues.get(position));
            holder.mFavordraweeView.setImageURI(uri);

            holder.mFavorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Context context = v.getContext();
//                    //store jsonData into a bundle
                    Bundle favorBundle = new Bundle();

                    favorBundle.putInt("movieId", movieIdList.get(position));
                    favorBundle.putString("posterUrl", posterUrlList.get(position));
                    favorBundle.putString("movieTitle", titleList.get(position));
                    favorBundle.putString("overview", overviewList.get(position));
                    favorBundle.putDouble("vote_average", voteList.get(position));
                    favorBundle.putString("release_date", releaseList.get(position));

                    Intent intent = new Intent(v.getContext(),DetailActivity.class);
                    intent.putExtras(favorBundle);
                    intent.putExtra("from","Favorite");
                    startActivity(intent);

//                    Toast.makeText(v.getContext(), "Detailpage Coming Soon " + "!", Toast.LENGTH_SHORT).show();
//                    private ArrayList<Integer> movieIdList;
//                    private ArrayList<String> posterUrlList;
//                    private ArrayList<String> titleList;
//                    private ArrayList<String> overviewList;
//                    private ArrayList<Double> voteList;
//                    private ArrayList<String> releaseList;

//                    bundleToDetail.putInt("position", position);
//                    bundleToDetail.putParcelable("jsonData", jsonFavor);
//
//                    //the normal way to store detailed data into a intent
//                    Intent intent = new Intent(context, DetailActivity.class);
//                    intent.putExtras(bundleToDetail);
//                    intent.putExtra("posterUrltodetailpage",mFavorValues.get(position));
////                    intent.putExtra("movie_id_todetailpage",movieIdList.get(position));
//
//                    Log.d("Movie positon is",String.valueOf(position));
//                    context.startActivity(intent);


                }
            });


        }

        @Override
        public int getItemCount() {
            return mFavorValues.size();
        }
    }



}
