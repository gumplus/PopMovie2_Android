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

import java.util.ArrayList;

/**
 * Created by cyp via apple on 16/5/5.
 *学习的根本，是如何处理信息的能力，也就是对现象的分析，归纳和延伸的能力。
 * What
 * When
 * Why
 * How
 */
public class MovieListFragment extends Fragment {

    private ArrayList<String> posterUrls;

    private static ArrayList<Integer> movieIdList;

    public static JsonBean jsonData = new JsonBean();


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {


        //put the recyclerView into the container of MovieListFragment
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.fragment_movie_list, container, false);

        setupRecyclerView(rv);
        return rv;
    }

    

    private void setupRecyclerView(RecyclerView recyclerView) {

        Bundle bundle = getArguments();
        posterUrls = bundle.getStringArrayList("movie_urls");
        movieIdList = bundle.getIntegerArrayList("movie_id");

        jsonData =  bundle.getParcelable("jsonbean_key");

        if(jsonData != null ){
            Log.d("jsonData","is not null");
            ArrayList<JsonBean.Results> resultList = jsonData.getResults();
        }


        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));

        recyclerView.setAdapter(new MovieViewAdapter(getActivity(), posterUrls));
    }


    // return my custom fragment
    public static MovieListFragment newInstance(Bundle args) {
        MovieListFragment mlf = new MovieListFragment();
        mlf.setArguments(args);
        return mlf;
    }

    //My Custom RecyclerView Adapter

    public static class MovieViewAdapter extends RecyclerView.Adapter<MovieViewAdapter.ViewHolder> {

        private final TypedValue mTypeValue = new TypedValue();
        private int mBackground;
        private ArrayList<String> mValues;


        public MovieViewAdapter(Context context,ArrayList<String> movieUrls) {

            mValues = movieUrls;
            Log.d("mValues",mValues.toString());

            //get Theme
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypeValue, true);
            //get the resourceId of background
            mBackground = mTypeValue.resourceId;

        }



        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
//            public final ImageView mImageView;
            public final SimpleDraweeView draweeView;


            public ViewHolder(View view) {
                super(view);
                mView = view;

//                The imageView for Glide below
//                mImageView = (ImageView) view.findViewById(R.id.list_movie_poster);

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

//             And Now I replace of Glide libs with Fresco.

//            Glide.with(viewHolder.mImageView.getContext())
//                    .load(mValues.get(position)).fitCenter().into(viewHolder.mImageView);

            Uri uri = Uri.parse(mValues.get(position));

            viewHolder.draweeView.setImageURI(uri);


            viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("posterUrltodetailpage",mValues.get(position));
                    intent.putExtra("movie_id_todetailpage",movieIdList.get(position));
                    intent.putExtra("jsonData", jsonData);
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
