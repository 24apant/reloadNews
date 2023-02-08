package functions;

import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.HashMap;
import java.util.Map;

public interface nlpInterface {
    String BASE_URL = "https://api.nlpcloud.io/v1/bart-large-cnn/";
    @Headers("Authorization: Token 735d920ffcb807882d24507677ebb824a602d845")
    @POST("summarization")
    Call<ResponseBody> getSummary(
            @Body ModelJSON json
    );
}
