package com.example.cyp.popmovie2;

import java.util.ArrayList;

/**
 * Created by apple on 16/5/14.
 */
//{
//        "id": 550,
//        "results": [
//        {
//        "id": "533ec654c3a36854480003eb",
//        "iso_639_1": "en",
//        "key": "SUXWAEX2jlg",
//        "name": "Trailer 1",
//        "site": "YouTube",
//        "size": 720,
//        "type": "Trailer"
//        }
//        ]
//        }
public class VideoBean  {
    private int id;
    private ArrayList<VideoResults> results ;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<VideoResults> getResults() {
        return results;
    }

    public void setResults(ArrayList<VideoResults> results) {
        this.results = results;
    }

    public static class VideoResults {
        public String key;
        public String name;
        public String site;
        public String size;
        public String type;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }


        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}



