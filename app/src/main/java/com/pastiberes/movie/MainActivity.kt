package com.pastiberes.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pastiberes.movie.Adapter.GenreAdapter
import com.pastiberes.movie.Model.Genre
import com.pastiberes.movie.Model.GenreData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        API.getGenre(

        ).enqueue(object :
            Callback<Genre> {
            override fun onResponse(call: Call<Genre>, response: Response<Genre>) {
                if (response.code() == 200) {
                    val adapter =
                        GenreAdapter(
                            response.body()?.genres,
                            this@MainActivity
                        )
                    gridview?.adapter = adapter
                } else {
                    Toast.makeText(this@MainActivity, "Gagal mendapatkan genre", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Genre>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Gagal menghubungkan ke server", Toast.LENGTH_SHORT).show()
            }
        })

    }
}