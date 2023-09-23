import com.google.gson.GsonBuilder
import com.pastiberes.movie.Model.Genre
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

object API {
    private const val baseURL = "https://api.themoviedb.org/3/"

    private var retrofit: Retrofit? = null

    fun getInstance(): Retrofit {
        if (retrofit == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    val originalRequest: Request = chain.request()
                    val newRequest: Request = originalRequest.newBuilder()
                        .addHeader("accept", "application/json")
                        .addHeader(
                            "Authorization",
                            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1OTIwYmIxNWViYmY3NWRkZGJjNTVlYjA2NGU2MDQ5MSIsInN1YiI6IjY1MGU5MTg4ZTFmYWVkMDBjNjE1YjIyNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.mwPkXuAdh_bsmigb9OuX-VON2YzLOL0ieUMU7ofU9e8"
                        )
                        .build()
                    chain.proceed(newRequest)
                })
                .addInterceptor(interceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build()

            val gson = GsonBuilder().setLenient().create()
            retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit!!
    }

    fun getGenre(): Call<Genre> {
        val service = getInstance().create(MovieAPI::class.java)
        return service.getGenre()
    }


}

interface MovieAPI {
    @GET("genre/movie/list?language=en")
    fun getGenre(): Call<Genre>
}
