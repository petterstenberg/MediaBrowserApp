package se.petterstenberg.mediabrowserapp.rest;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.petterstenberg.mediabrowserapp.models.ProgramsResponse;

public class ApiClientImpl implements ApiClient {

    private final ApiService mService;
    private final Context mContext;

    public ApiClientImpl(Context context) {
        mContext = context;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.sr.se")
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        mService = retrofit.create(ApiService.class);
    }

    @NonNull
    private OkHttpClient createOkHttpClient() {
        Cache cache = new Cache(mContext.getCacheDir(), 1024 * 1024 * 10);

        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        HttpUrl originalUrl = originalRequest.url();

                        HttpUrl newUrl = originalUrl.newBuilder()
                                .addQueryParameter("format", "json")
                                .build();

                        Request newRequest = originalRequest.newBuilder()
                                .url(newUrl)
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();
    }

    @Override
    public void getPrograms(int page, final Callback<ProgramsResponse> callback) {
        Call<ProgramsResponse> call = mService.getPrograms(page);
        call.enqueue(new retrofit2.Callback<ProgramsResponse>() {
            @Override
            public void onResponse(Call<ProgramsResponse> call, Response<ProgramsResponse> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ProgramsResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
