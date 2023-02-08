package functions;

import com.google.cloud.firestore.Firestore;
import com.google.gson.JsonParser;
import org.json.JSONObject;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class AnayThread extends Thread{
    private Logger logger;
    private String category;
    private String[] keys = null;
    private Firestore db = null;
    private final boolean perigonEnabled = true;
    public AnayThread(Logger l, String cat, String[] keys, Firestore db){
        this.logger = l;
        this.category = cat;
        this.keys = keys;
        this.db = db;
    }
    public void run(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(apiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit periRetro = new Retrofit.Builder().baseUrl(perigonInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        int numPages = 10;
        int safetyMultiplier = 4;
        String apiKey = "2a2429ecaaa4496680cf6d23b9e8dc0a";
        String perigonKey = "7b841293-35dd-45a7-8b02-50af4a7ac683";


        logger.info("In thread for category " + category);


        ArrayList<ModelPerigonNews> news = new ArrayList<>();
        String descNews = null;
        if(category.equals("TrendingNews")){
            try {
                if (perigonEnabled){
                    news = periRetro.create(perigonInterface.class).getTrendingNews(perigonKey,"us", "Non-news", "Politics", "date","false", "false", "Opinion","Paid","Roundup","Press","apnews.com/hub/ap-top-news","npr.org","washingtonpost.com","nytimes.com","wsj.com","bbc.com/news/world/us_and_canada","economist.com","theatlantic.com","politico.com").execute().body().getArticles();
                    logger.info("Done");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            StringBuilder finalKey = new StringBuilder();
            for(int k = 0; k < keys.length;k++){
                if(k != keys.length -1){
                    finalKey.append(keys[k]).append(" OR ");
                }
                else{
                    finalKey.append(keys[k]);
                }
            }
            try {
                if(category.equals("EconomyNews")) {
                    news = periRetro.create(perigonInterface.class).getEconomyNews(perigonKey,"Business","Finance","us","false","false","Opinion","PaidNews","Roundup","PressRelease","Non-news","date","apnews.com/hub/ap-top-news","npr.org","washingtonpost.com","nytimes.com","wsj.com","bbc.com/news/world/us_and_canada","economist.com","theatlantic.com","politico.com").execute().body().getArticles();
                }
                else if (category.equals("EnvironmentNews")){
                    news = periRetro.create(perigonInterface.class).getEnvironmentNews(perigonKey,"Environment","Tech","Health","us","false","false","Opinion","PaidNews","Roundup","PressRelease","Non-news","date","apnews.com/hub/ap-top-news","npr.org","washingtonpost.com","nytimes.com","wsj.com","bbc.com/news/world/us_and_canada","economist.com","theatlantic.com","politico.com").execute().body().getArticles();
                }
                else if (category.equals("SocietyNews")){
                    logger.info("Entered Society");
                    news = periRetro.create(perigonInterface.class).getSocietyNews(perigonKey,"us","false","false","Opinion","PaidNews","Roundup","PressRelease","Non-news","date","apnews.com/hub/ap-top-news","npr.org","washingtonpost.com","nytimes.com","wsj.com","bbc.com/news/world/us_and_canada","economist.com","theatlantic.com","politico.com","Racial Injustice","Coronavirus","Social Issues","Abortion").execute().body().getArticles();
                }
                logger.info("Category: " + category);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        if(news == null){
            return;
        }

        // For Trending we only want political news

        // trim and handle the news
        String[] undesiredWords = new String[]{" sex "," shit "," ass ", " Sex ", " vagina ", " love-dovey ", " sexual ", " WWE ", " Flirty ", "TikTok", " erectile dysfunction ", " Black Friday ", " AEW ", "Cyber Monday"};
        String[] unwantedURLs = new String[]{"tmz","elle.com","yourtango", "buzzfeed.com", "tvshowbiz", "dailymail.co.uk", "theathletic.com", "bleedinggreen", "espn.com/nfl", "denverpost.com", "youtube.com", "ft.com", "eonline.com", "variety.com"};
        for(int x = 0; x < news.size();x++){
            if(news.get(x).getContent() == null){
                logger.info("Released article for null description from category " + category + " article " + x);
                news.remove(x);
                x--;
            }
            if(news.get(x).getUrl().contains("mccarthy-prevails-becomes-speaker-in-late-night-house-vote exception")){
                logger.info("Removed deadly article.");
                news.remove(x);
                x--;
            }
            else{
//                for (String s: undesiredWords){
//                    if(news.get(x).getContent().contains(s)){
//                        logger.info("Released article due to bad word '" + s + "' from category " + category + " number " + x + " with desc: " + news.get(x).getContent());
//                        news.remove(x);
//                        x--;
//                        break;
//                    }
//                    else if(news.get(x).getTitle().contains(s)){
//                        logger.info("Released article due to bad word '" + s + "' from category " + category + " number " + x + " with title: " + news.get(x).getTitle());
//                        news.remove(x);
//                        x--;
//                        break;
//                    }
//
//                }
            }
            for(String s: unwantedURLs){
                if(x >= 0 && news.get(x).getUrl().contains(s)){
                    logger.info("Released article due to bad URL '" + s + "' from category " + category + " number " + x + " with desc: " + news.get(x).getUrl());
                    news.remove(x);
                    x--;
                    break;
                }
            }

        }
        logger.info(String.valueOf(news.size()));
        ArrayList<ModelPerigonNews> newNews = new ArrayList<>();
        for (int i = 0; i < numPages; i++){
            if(news.get(i).getImageUrl().equals("")){
                // set it to a default image online
                if(news.get(i).getUrl().contains("apnews")){
                    news.get(i).setImageUrl("https://www.greenpeace.org/usa/wp-content/uploads/2018/08/ap-news-logo.png");
                }
                else if (news.get(i).getUrl().contains("politico")){
                    news.get(i).setImageUrl("https://newamericanleaders.org/wp-content/uploads/2016/12/news-logo-politico.png");
                }
            }
            newNews.add(news.get(i));
        }

        // go through each and if imageUrl is blank add a default

//        news = newNews;
        logger.info("After processing news: length of " + news.size() + " for category " + category);


        for (int x=0; x< numPages;x++){
            HashMap<String, Object> mainMap = new HashMap<>();
            ModelPerigonNews n = news.get(x);


            String url = n.getUrl();
            Elements ps = null;
            String summary;

            // bad words

            summary = n.getContent();
            try{

                if(url.contains("youtube")){
                }
                else{
                    String ps2 = n.getContent();
                    Document doc2 = Jsoup
                                .connect(url)
                                .timeout(3*1000)
                                .get();
                    System.out.println("Found");
                    ps = doc2.select("p");
                    ps2 = ps.text();
                    if(ps2.length() > 8000){
                            ps2 = ps2.substring(0,8000);
                        }
                    //adding to a json object
                    JSONObject json = new JSONObject();
                    json.put("text",ps2);
                    System.out.println("yes");

                    //connecting to nlpCloud, passing params, getting response after post


                    JsonParser parser = new JsonParser();
                    summary = parser.parse(summary).getAsJsonObject().get("summary_text").getAsString();
                    System.out.println(summary);

                }

                //getting text of full document

            }
            catch (HttpStatusException e){
                if(e.getStatusCode() == 429){
                    x--;
                    logger.info("Hit rate limit for request " + x);
                    try {
                        sleep(500);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            catch (SocketTimeoutException sc){
                if(x>=0){
                    System.out.println("Avoided Url " + news.get(x).getUrl());
                }
            }
            catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("Avoided bad " + news.get(x).getUrl());
            }

            mainMap.put("description", summary);
            mainMap.put("author", n.getAuthorsByline() + "   " + n.getPubDate());
            mainMap.put("title", n.getTitle());
            mainMap.put("url", n.getUrl());
            mainMap.put("urlToImage", n.getImageUrl());
            mainMap.put("publishedAt", n.getPubDate());
            mainMap.put("upVoteCt", n.getUpVoteCt());
            mainMap.put("downVoteCt", n.getDownVoteCt());
            mainMap.put("commentCt", n.getCommentCt());
            mainMap.put("votes", null);
            mainMap.put("comments", null);

            if(x < 10){
                db.collection(category).document("0"+x).set(mainMap);
                logger.info("Pushed article " + x + " of category " + category + " to FS " + db.getOptions().getProjectId());
            }
            else{
                db.collection(category).document(""+x).set(mainMap);
                logger.info("Pushed article " + x + " of category " + category + " to FS " + db.getOptions().getProjectId());
            }



        }



        logger.info("Exiting thread " + category);
    }
}
