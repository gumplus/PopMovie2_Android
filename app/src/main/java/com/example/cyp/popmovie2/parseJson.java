//package com.example.cyp.popmovie2;
//
//import android.os.Bundle;
//
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//
///**
// * Created by apple on 16/5/11.
// */
//public class ParseJson {
//
//    // Set your own api_key
//    private final String api_key = "?api_key=92741aee53714cbe1a7d87fc658bbaad";
//    //the base api of popular
//    private String popApi = "http://api.themoviedb.org/3/movie/popular";
//    //the base api of toprated
//    private String ratedAPi = "http://api.themoviedb.org/3/movie/top_rated";
//    //the base part api of absolutePath of movie poster
//    private String posterUrl = "http://image.tmdb.org/t/p/w185";
//
//    private String apiUrl;
//
//    private Bundle jsonBundle = new Bundle();
//
//
//
//    public ParseJson(String apiBaseUrl) {
//        this.apiUrl = apiBaseUrl ;
//    }
//
//    OkHttpClient okHttpClient = new OkHttpClient();
//
//
//    //这里需要 识别区分 查询api （pop or toprated）
//    Request request = new Request.Builder()
//            .url(apiUrl + api_key)
//            .build();
//
//
//    okhttp3.Call call = okHttpClient.newCall(request);
//    call.enqueue(new Callback() {
//
//    })
//
//
//
//
//    //build a static function for getting a jsonbean
//    public static JsonBean getBean(String apiUrl) {
//        apiUrl += api_key;
//        ParseJson parseJson = new ParseJson();
//        JsonBean jBean = parseJson.getBean(apiUrl);
//        return jBean;
//    }
//
//
//
//
//
////
////    okHttpClient.newCall(request).enqueue(new Callback() {
////
////        public void onFailure(Call call, IOException e) {
////            e.printStackTrace();
////        }
////
////        public void onResponse(Call call, Response response) throws IOException {
////
////            if(!response.isSuccessful()) throw new IOException("Wrong Code" + response);
////
////            Gson gson = new Gson();
////            JsonBean jsonBean = new JsonBean();
////
////            try {
////                jsonBean = gson.fromJson(response.body().string(), JsonBean.class);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////
////
////            ArrayList<JsonBean.Results> result = jsonBean.getResults();
////
////            //把jsonbean  和  result Object直接传递到 bundle 存储
////            bundle.putParcelable("jsonbean_key", jsonBean);
////
////            Log.d("result.size", String.valueOf(result.size()));
////
////            for (int i =0; i< result.size();i++) {
////
////                //get poster path:
////                popPosterPath = result.get(i).getPoster_path();
////                Log.d("popPosterPath", popPosterPath);
////
////                //get movie id list:
////                Log.d("result.getId()", String.valueOf(result.get(i).getId()));
////                movieIdList.add(result.get(i).getId());
////
////                popFinalUrl = posterBaseUrl + popPosterPath;
////                Log.d("popFinalUrl", popFinalUrl);
////
////                posterUrls.add(popFinalUrl);
////                Log.d("posterUrls",  posterUrls.toString());
////
////            }
////        }
////    });
//
//}
