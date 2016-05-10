//package com.example.cyp.popmovie2;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//import java.util.ArrayList;
//
///**
// * Created by apple on 16/5/10.
// */
//public class ParcelableUtil implements Parcelable {
//    private JsonBean jsonBean;
//
//    public JsonBean getJsonBean() {
//        return jsonBean;
//    }
//    public ParcelableUtil(JsonBean jsonBean) {
//        super();
//        this.jsonBean = jsonBean;
//    }
//
//    private ParcelableUtil(Parcel in) {
//        jsonBean = new JsonBean();
//        jsonBean.setPage(in.readInt());
//        jsonBean.setResults((ArrayList<JsonBean.Results>) in.readParcelableArray(JsonBean.Results.class.getClassLoader()));
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//
//
//    @Override
//    public void writeToParcel(Parcel parcel, int flags) {
//
//        parcel.writeInt(jsonBean.getPage());
//        parcel.writeParcelable((Parcelable) jsonBean.getResults(), PARCELABLE_WRITE_RETURN_VALUE);
//
//    }
//
//    public static final Parcelable.Creator<ParcelableUtil> CREATOR = new Parcelable.Creator<ParcelableUtil>() {
//        @Override
//        public ParcelableUtil createFromParcel(Parcel source) {
//            return new ParcelableUtil(source);
//        }
//
//        @Override
//        public ParcelableUtil[] newArray(int size) {
//            return new ParcelableUtil[size];
//        }
//    };
//
////    public static final Parcelable.Creator<ParcelableUtil> CREATOR =
////            new Parcelable.Creator<ParcelableUtil>() {
////
////        @Override
////        public ParcelableUtil createFromParcel(Parcel parcel) {
////            return new ParcelableUtil(parcel);
////        }
////
////        @Override
////        public ParcelableUtil[] newArray(int size) {
////            return new ParcelableUtil[size];
////        }
////    };
//
//
//}