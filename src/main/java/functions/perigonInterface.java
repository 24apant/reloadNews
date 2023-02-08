package functions;

import com.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface perigonInterface {
    String PERIGON_KEY = "7b841293-35dd-45a7-8b02-50af4a7ac683";
    String BASE_URL = "https://api.goperigon.com/v1/";

    @GET("all")
    Call<PerigonNews> getTrendingNews(
        @Query("apiKey") String pKey,
        @Query("country") String country,
        @Query("excludeLabel") String exc,
        @Query("category") String cat,
        @Query("sortBy") String sB,
        @Query("paywall") String pw,
        @Query("showReprints") String reprints,
        @Query("excludeLabel") String Opinion,
        @Query("excludeLabel") String Paid,
        @Query("excludeLabel") String Roundup,
        @Query("excludeLabel") String Press,
        @Query("source") String apnews,
        @Query("source") String npr,
        @Query("source") String washpost,
        @Query("source") String nytimes,
        @Query("source") String wsj,
        @Query("source") String bbc,
        @Query("source") String economist,
        @Query("source") String atlantic,
        @Query("source") String politico
        );
    @GET("all")
    Call<PerigonNews> getSocietyNews(
        @Query("apiKey") String pKey,
        @Query("country") String country,
        @Query("paywall") String pw,
        @Query("showReprints") String reprints,
        @Query("excludeLabel") String Opinion,
        @Query("excludeLabel") String Paid,
        @Query("excludeLabel") String Roundup,
        @Query("excludeLabel") String Press,
        @Query("excludeLabel") String nonNews,
        @Query("sortBy") String sB,
        @Query("source") String apnews,
        @Query("source") String npr,
        @Query("source") String washpost,
        @Query("source") String nytimes,
        @Query("source") String wsj,
        @Query("source") String bbc,
        @Query("source") String economist,
        @Query("source") String atlantic,
        @Query("source") String politico,
        @Query("topic") String RacialInjustice,
        @Query("topic") String Coronavirus,
        @Query("topic") String SocialIssues,
        @Query("topic") String Abortion
        );

    @GET("all")
    Call<PerigonNews> getEconomyNews(
            @Query("apiKey") String pKey,
            @Query("category") String business,
            @Query("category") String finance,
            @Query("country") String country,
            @Query("paywall") String pw,
            @Query("showReprints") String reprints,
            @Query("excludeLabel") String Opinion,
            @Query("excludeLabel") String Paid,
            @Query("excludeLabel") String Roundup,
            @Query("excludeLabel") String Press,
            @Query("excludeLabel") String nonNews,
            @Query("sortBy") String sB,
            @Query("source") String apnews,
            @Query("source") String npr,
            @Query("source") String washpost,
            @Query("source") String nytimes,
            @Query("source") String wsj,
            @Query("source") String bbc,
            @Query("source") String economist,
            @Query("source") String atlantic,
            @Query("source") String politico
    );

    @GET("all")
    Call<PerigonNews> getEnvironmentNews(
            @Query("apiKey") String pKey,
            @Query("category") String Environment,
            @Query("category") String Tech,
            @Query("category") String Health,
            @Query("country") String country,
            @Query("paywall") String pw,
            @Query("showReprints") String reprints,
            @Query("excludeLabel") String Opinion,
            @Query("excludeLabel") String Paid,
            @Query("excludeLabel") String Roundup,
            @Query("excludeLabel") String Press,
            @Query("excludeLabel") String nonNews,
            @Query("sortBy") String sB,
            @Query("source") String apnews,
            @Query("source") String npr,
            @Query("source") String washpost,
            @Query("source") String nytimes,
            @Query("source") String wsj,
            @Query("source") String bbc,
            @Query("source") String economist,
            @Query("source") String atlantic,
            @Query("source") String politico
    );
}
