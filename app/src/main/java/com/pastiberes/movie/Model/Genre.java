package com.pastiberes.movie.Model;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genre {

    @SerializedName("genres")
    @Expose
    private ArrayList<GenreData> genres;

    public ArrayList<GenreData> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<GenreData> genres) {
        this.genres = genres;
    }

}