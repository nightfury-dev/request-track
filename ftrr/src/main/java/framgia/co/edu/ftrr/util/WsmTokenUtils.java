package framgia.co.edu.ftrr.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import framgia.co.edu.ftrr.common.StringsCommon;
import framgia.co.edu.ftrr.dto.response.UserWsmResponse;
import okhttp3.*;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@PropertySource(value = "classpath:wsm.properties", encoding = "UTF-8")
public class WsmTokenUtils {

    public static UserWsmResponse getUserInfo(final String accessToken) {
        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody body = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("access_token", accessToken)
                    .build();
            Request request = new Request.Builder()
                    .url(StringsCommon.WSM_GET_INFO_LINK)
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
