package com.pastiberes.movie.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.pastiberes.movie.Model.GenreData;
import com.pastiberes.movie.R;

import java.util.ArrayList;


public class GenreAdapter extends BaseAdapter {

    ArrayList<GenreData> dataSet;
    private Context context;

    public GenreAdapter(ArrayList<GenreData> tempData, Context context) {
        this.context = context;
        this.dataSet = tempData;
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSet.size();
    }

    @Override
    public long getItemId(int position) {
        return dataSet.size();
    }

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row, null);

        GenreData genre = dataSet.get(position);

        TextView namaGenre = view.findViewById(R.id.namaGenre);
        namaGenre.setText(genre.getName());

        return view;
    }
}
