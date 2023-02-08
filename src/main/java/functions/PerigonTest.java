package functions;
import com.beust.ah.A;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import java.io.IOException;

import java.util.logging.Logger;
import com.google.firebase.cloud.FirestoreClient;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;




public class PerigonTest implements HttpFunction {
    private static final Logger logger = Logger.getLogger(HelloWorld.class.getName());
    String[] categories = new String[]{"TrendingNews", "EconomyNews", "EnvironmentNews", "SocietyNews"};
    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException, InterruptedException {

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
        String[] socKeys = new String[]{"sexism","LGBTQ","abortion","Abortion","racism","affirmative action","Antifa","Affordable Care Act","covid","filibuster","gerrymandering","voter fraud","immigration"};
        String[] environmentKeys = new String[]{"climate", "climate change", "health", "pollution", "ems", "global warming", "green", "epa", "sustainability", "health"};


        AnayThread t1 = new AnayThread(logger, categories[0], null, db);
        //AnayThread t2 = new AnayThread(logger, categories[1], econKeys, db);

        t1.start();
        //t2.start();

        logger.info("Started threads 1-4.");

        t1.join();
        //t2.join();
        //logger.info("Threads 1-2 exited.");


//        AnayThread t3 = new AnayThread(logger, categories[2], environmentKeys, db);
//        AnayThread t4 = new AnayThread(logger, categories[3], socKeys, db);
//
//        t3.start();
//        t4.start();
//
//        logger.info("Started threads 3-4.");
//
//        t3.join();
//        t4.join();
//
//        logger.info("Threads 3-4 exited.");
    }


}