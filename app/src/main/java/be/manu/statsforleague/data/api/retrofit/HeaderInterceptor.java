package be.manu.statsforleague.data.api.retrofit;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static be.manu.statsforleague.data.api.Constants.ACCESS_TOKEN;
import static be.manu.statsforleague.data.api.Constants.API_KEY;

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request original = chain.request();
        Request request = original.newBuilder().header(API_KEY, ACCESS_TOKEN)
                .method(original.method(), original.body()).build();
        return chain.proceed(request);
    }
}
