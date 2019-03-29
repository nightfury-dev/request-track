package framgia.co.edu.ftrr.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import framgia.co.edu.ftrr.dto.response.UserWsmResponse;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@PropertySource(value = "classpath:wsm.properties", encoding = "UTF-8")
public class WsmTokenUtils {

    @Value("wsm.get.info.link")
    private String wsmGetInfoLink;

    public UserWsmResponse getUserInfo(final String accessToken) {
        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody body = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("access_token", accessToken)
                    .build();
            Request request = new Request.Builder()
                    .url(wsmGetInfoLink)
                    .post(body)
                    .addHeader("cache-control", "no-cache")
                    .build();

            Response response = client.newCall(request).execute();

            ObjectMapper mapper = new ObjectMapper();
            UserWsmResponse userWsmResponse = mapper.readValue(response.body().string(), UserWsmResponse.class);
            return userWsmResponse;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
