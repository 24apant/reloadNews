package functions;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.functions.*;

import java.util.Map;
import java.util.logging.Logger;

import com.google.firebase.cloud.FirestoreClient;
import retrofit2.Retrofit;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;




public class reloadNews implements BackgroundFunction<reloadNews.PubSubMessage> {
    private static final Retrofit retrofit=null;
    private static final int numPages = 32;


    private static final Logger logger = Logger.getLogger(HelloWorld.class.getName());
    String[] categories = new String[]{"TrendingNews", "EconomyNews", "EnvironmentNews", "SocietyNews"};


    @Override
    public void accept(PubSubMessage pubSubMessage, Context context) throws Exception {
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .setProjectId("btcrelease")
                .build();

        if(FirebaseApp.getApps().size() == 0){
            FirebaseApp.initializeApp(options);
        }
        Firestore db = FirestoreClient.getFirestore();

        logger.info("Entered");
        String[] econKeys = new String[]{"Economy", "bitcoin", "crypto", "wall street"};

        AnayThread t1 = new AnayThread(logger, categories[0], null, db);
        AnayThread t2 = new AnayThread(logger, categories[1], econKeys, db);

        t1.start();

        logger.info("Started threads 1-2.");

        t1.join();
        t1.interrupt();

        t2.start();
        t2.join();
        t2.interrupt();
        logger.info("Threads 1-2 exited.");



    }
    public static class PubSubMessage{
        String data;
        Map<String, String> attributes;
        String messageId;
        String publishTime;
    }
}