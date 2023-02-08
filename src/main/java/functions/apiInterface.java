package functions;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface apiInterface {
    String BASE_URL = "https://newsapi.org/v2/";

    @GET("top-headlines")
    Call<TrendingNews> getSortedNews(
            @Query("country") String country,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String apiKey,
            @Query("sortBy") String sortBy

    );
    @GET("top-headlines")
    Call<TrendingNews> getCategorizedNews(
            @Query("apiKey") String apiKey,
            @Query("country") String country,
            @Query("category") String keyword,
            @Query("pageSize")  int pageSize,
            @Query("language") String lang
            );

    @GET("everything")
    Call<TrendingNews> getKeywordNews(
            @Query("apiKey") String apiKey,
            @Query("q") String keywords,
            @Query("pageSize") int pageSize
    );
}
