package functions;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.functions.BackgroundFunction;
import com.google.cloud.functions.Context;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import retrofit2.Retrofit;

import java.util.Map;
import java.util.logging.Logger;


public class reloadNews2 implements BackgroundFunction<reloadNews2.PubSubMessage> {
    private static final Retrofit retrofit=null;
    private static final int numPages = 32;


    private final String sources = "cnn,the-verge,abc-news,associated-press,bbc-news,bleacher-report,business-insider,cbc-news,fox-news,google-news,reuters,the-hill,the-wall-street-journal,vice-news,wired";

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
        String[] socKeys = new String[]{"sexism","LGBTQ","abortion","Abortion","racism","affirmative action","Antifa","Affordable Care Act","covid","filibuster","gerrymandering","voter fraud","immigration"};
        String[] environmentKeys = new String[]{"climate", "climate change", "health", "pollution", "ems", "global warming", "green", "epa", "sustainability", "health"};

        AnayThread t3 = new AnayThread(logger, categories[2], environmentKeys, db);
        AnayThread t4 = new AnayThread(logger, categories[3], socKeys, db);

        t3.start();

        logger.info("Started threads 3-4.");

        t3.join();
        t3.interrupt();

        t4.start();
        t4.join();
        t4.interrupt();

        logger.info("Threads 3-4 exited.");
    }
    public static class PubSubMessage{
        String data;
        Map<String, String> attributes;
        String messageId;
        String publishTime;
    }
}