package com.sunshinexu.mobilelearn.dagger.module;

import com.sunshinexu.mobilelearn.BuildConfig;
import com.sunshinexu.mobilelearn.http.api.ApiService;
import com.sunshinexu.mobilelearn.http.interceptor.NetCacheInterceptor;
import com.sunshinexu.mobilelearn.utils.CacheUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HttpModule {
    @Singleton
    @Provides
    ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
    @Singleton
    @Provides
    Retrofit provideRetrofit(Retrofit.Builder builder, OkHttpClient client){
        return createRetrofit(builder,client,ApiService.BASE_URL);
    }

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder(){
        return new Retrofit.Builder();
    }
    //OkHttpClient 创建
    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder(){
        return new OkHttpClient.Builder();
    }
    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder){
        if (BuildConfig.DEBUG) {
            //消息拦截器，可看到接口返回的所有内容  BASIC 级别是请求/响应行
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        //设置网络缓存
        File cacheFile = new File(CacheUtils.getCacheDir());
        System.out.println("HttpModule 设置缓存路径" + cacheFile );
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);//100M
        builder.addNetworkInterceptor(new NetCacheInterceptor());
        builder.cache(cache);
        //设置超时时间
        builder.connectTimeout(8, TimeUnit.SECONDS);
        builder.readTimeout(15,TimeUnit.SECONDS);
        builder.writeTimeout(15,TimeUnit.SECONDS);
        //设置重新连接
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String baseUrl) {
        return builder
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
