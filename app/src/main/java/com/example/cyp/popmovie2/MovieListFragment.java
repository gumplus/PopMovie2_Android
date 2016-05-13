package com.example.cyp.popmovie2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cyp via apple on 16/5/5.
 * What
 * When
 * Why
 * How
 */
public class MovieListFragment extends Fragment {

    private final String api_key = "?api_key=92741aee53714cbe1a7d87fc658bbaad";
    private final String apiFromPop = "http://api.themoviedb.org/3/movie/popular";
    private final String apifromRated = "http://api.themoviedb.org/3/movie/top_rated";

    private String posterBaseUrl = "http://image.tmdb.org/t/p/w185";
    private String posterPath;
//    private int movieId;

    public static JsonBean jsonTransfer = new JsonBean();
//    public static JsonBean popJson = new JsonBean();
//    public static JsonBean ratedJson = new JsonBean();



    private OkHttpClient okHttp = new OkHttpClient();
    private Gson gson = new Gson();
    //the base api of popular or topRatedApi
    private static String whichApi;

    private RecyclerView rv;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {


        //put the recyclerView into the container of MovieListFragment
        rv = (RecyclerView) inflater.inflate(R.layout.fragment_movie_list, container, false);
        parseApi(whichApi);

        setupRecyclerView(rv);
        return rv;
    }


    private void parseApi(final String whichApi) {

        Request request = new Request.Builder()
                .url(whichApi + api_key)
                .build();

        okHttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    if(!response.isSuccessful()) throw new IOException("Wrong Code" + response);
                    jsonTransfer = gson.fromJson(response.body().string(), JsonBean.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                switch (whichApi) {
//                    case apiFromPop:
//                        popJson = jsonTransfer;
//                    case apifromRated:
//                        ratedJson = jsonTransfer;
//                    default:
//                        Log.d("whichAPi","is wrong.");
//                        break;
//                }

            }
        });


    }
    

    private void setupRecyclerView(RecyclerView recyclerView) {

        ArrayList<String> posterUrlsList = new ArrayList<>();
//        ArrayList<Integer> movieIdList = new ArrayList<>();
        ArrayList<JsonBean.Results> resultList;

        resultList = jsonTransfer.getResults();
        if (resultList != null) {
            Log.d("resultList","is not null");
        }

        for (int i = 0; i < resultList.size();i++) {

            posterPath = resultList.get(i).getPoster_path();
            posterUrlsList.add(posterBaseUrl + posterPath);

//            movieId = resultList.get(i).getId();
//            movieIdList.add(movieId);
//            if (movieIdList != null) {
//                Log.d("movieIdList","is not null");
//            }
        }

        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));

        if(posterUrlsList != null) {
            Log.d("posterUrlsList", "is not null");
        }
        recyclerView.setAdapter(new MovieViewAdapter(getActivity(), posterUrlsList));
    }


    // return my custom fragment

    public static MovieListFragment newInstance(String Api) {
        whichApi = Api;
        MovieListFragment mlf = new MovieListFragment();
        return mlf;
    }

    //My Custom RecyclerView Adapter

    public static class MovieViewAdapter extends RecyclerView.Adapter<MovieViewAdapter.ViewHolder> {

        private final TypedValue mTypeValue = new TypedValue();
        private int mBackground;
        private ArrayList<String> mValues;


        public MovieViewAdapter(Context context,ArrayList<String> movieUrls) {

            mValues = movieUrls;
            Log.d("mValues",String.valueOf(mValues));

            //get Theme
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypeValue, true);
            //get the resourceId of background
            mBackground = mTypeValue.resourceId;

        }



        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;

            public final SimpleDraweeView draweeView;


            public ViewHolder(View view) {
                super(view);
                mView = view;

                draweeView = (SimpleDraweeView) view.findViewById(R.id.list_image_poster);
            }
        }


        public String getValueAt(int position) {
            return mValues.get(position);
        }



        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_movie_poster,parent,false);
            view.setBackgroundResource(mBackground);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {


                Log.d("mValues.get(i)", mValues.get(position));


            Uri uri = Uri.parse(mValues.get(position));
            viewHolder.draweeView.setImageURI(uri);


            viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context = v.getContext();

                    //store jsonData into a bundle
                    Bundle bundleToDetail = new Bundle();
                    // store MovieID into a bundle
                    bundleToDetail.putInt("position", position);
                    bundleToDetail.putParcelable("jsonData", jsonTransfer);

                    //the normal way to store detailed data into a intent
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtras(bundleToDetail);
                    intent.putExtra("posterUrltodetailpage",mValues.get(position));
//                    intent.putExtra("movie_id_todetailpage",movieIdList.get(position));

                    Log.d("Movie positon is",String.valueOf(position));
                    context.startActivity(intent);
                }
            });


        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

    }

}