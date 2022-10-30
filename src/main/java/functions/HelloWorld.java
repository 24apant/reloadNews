package functions;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Logger;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;




public class HelloWorld implements HttpFunction {



    private static final Logger logger = Logger.getLogger(HelloWorld.class.getName());
    String[] categories = new String[]{"TrendingNews", "EconomyNews", "EnvironmentNews", "SocietyNews"};


    // Simple function to return "Hello World"
    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        int numPages = 32;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(apiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .build();

        Firestore db = firestoreOptions.getService();
        logger.info("Entered");
        String[] econKeys = new String[]{"Economy", "bitcoin", "crypto", "wall street"};
        String[] socKeys = new String[]{"sexism","LGBTQ","abortion","Abortion","racism","affirmative action","Antifa","Affordable Care Act","covid","filibuster","gerrymandering","voter fraud","immigration"};
        String[] environmentKeys = new String[]{"climate", "climate change", "health", "pollution", "ems", "global warming", "green", "epa", "sustainability", "health"};



        for(String category: categories){
           if(category.equals("TrendingNews")){
               retrofit.create(apiInterface.class).getNews("us", 32, "2a2429ecaaa4496680cf6d23b9e8dc0a").enqueue(new Callback<TrendingNews>() {
                   @Override
                   public void onResponse(Call<TrendingNews> call,Response<TrendingNews> response) {
                       ArrayList<ModelNews> news;
                       logger.info("Got callback. --------------");
                       if (response.isSuccessful()) {
                           if (response.body() != null) {
                               news = response.body().getArticles();
                               logger.info("Category: " + category + "\n" + String.valueOf(response.body().getArticles()));
                           } else {
                               news = new ArrayList<>();
                               logger.info("Category " + category + " failed.");
                           }
                           for(int x = 0; x < news.size();x++){
                                   HashMap<String, Object> mainMap = new HashMap<>();
                                   ModelNews n = news.get(x);
                                   mainMap.put("author", n.getAuthor() + "   " + n.getPublishedAt());
                                   mainMap.put("description", n.getDescription());
                                   mainMap.put("title", n.getTitle());
                                   mainMap.put("url", n.getUrl());
                                   mainMap.put("urlToImage", n.getUrlToImage());
                                   mainMap.put("publishedAt", n.getPublishedAt());
                                   mainMap.put("upVoteCt", n.getUpVoteCt());
                                   mainMap.put("downVoteCt", n.getDownVoteCt());
                                   mainMap.put("commentCt", n.getCommentCt());
                                   mainMap.put("votes", null);
                                   mainMap.put("comments", null);

                                   if(x < 10){
                                       db.collection(category).document("0"+x).set(mainMap);

                                   }
                                   else{
                                       db.collection(category).document(""+x).set(mainMap);
                                   }
                                   logger.info("Pushed article " + x + " of category " + category);

                           }



                        }
                   }

                   @Override
                   public void onFailure(Call<TrendingNews> call,Throwable t) {

                   }
               });
           }
           else{
               String[] keys;
               if(category.equals("EconomyNews")){
                   keys=econKeys;
               }
               else if(category.equals("EnvironmentNews")){
                    keys=environmentKeys;
               }
               else if(category.equals("SocietyNews")){
                    keys=socKeys;
               }
               else{
                   return;
               }
               String finalKey = "";
               for(int k = 0; k < keys.length;k++){
                   if(k != keys.length -1){
                       finalKey += keys[k] + " OR ";
                   }
                   else{
                       finalKey += keys[k];
                   }
               }
               logger.info("Category: " + category + "  Final Key:   " + finalKey.length());
                   //add all keywords to a string
               retrofit.create(apiInterface.class).getKeywordNews(numPages, finalKey, "2a2429ecaaa4496680cf6d23b9e8dc0a").enqueue(new Callback<TrendingNews>() {
                       @Override
                       public void onResponse( Call<TrendingNews> call,  Response<TrendingNews> response) {
                           ArrayList<ModelNews> news;
                           if (response.isSuccessful()) {
                               logger.info("Got callback. ----------------------");
                               if (response.body() != null) {
                                   news = response.body().getArticles();
                                   logger.info("Category: " + category + "\n" + String.valueOf(response.body().getArticles()));
                               } else {
                                   news = new ArrayList<>();
                                   logger.info("Category " + category + " failed.");
                               }
                               for(int x = 0; x < news.size();x++){
                                   HashMap<String, Object> mainMap = new HashMap<>();
                                   ModelNews n = news.get(x);
                                   mainMap.put("author", n.getAuthor() + "   " + n.getPublishedAt());
                                   mainMap.put("description", n.getDescription());
                                   mainMap.put("title", n.getTitle());
                                   mainMap.put("url", n.getUrl());
                                   mainMap.put("urlToImage", n.getUrlToImage());
                                   mainMap.put("publishedAt", n.getPublishedAt());
                                   mainMap.put("upVoteCt", n.getUpVoteCt());
                                   mainMap.put("downVoteCt", n.getDownVoteCt());
                                   mainMap.put("commentCt", n.getCommentCt());
                                   mainMap.put("comments",null);
                                   mainMap.put("votes",null);

                                   if(x < 10){
                                       db.collection(category).document("0"+x).set(mainMap);

                                   }
                                   else{
                                       db.collection(category).document(""+x).set(mainMap);
                                   }
                                   logger.info("Pushed article " + x + " of category " + category);
                               }


                           }
                       }

                       @Override
                       public void onFailure( Call<TrendingNews> call,  Throwable t) {

                       }
                   });

           }
        }




    }


}