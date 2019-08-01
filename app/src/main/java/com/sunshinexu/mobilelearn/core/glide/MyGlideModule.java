package com.sunshinexu.mobilelearn.core.glide;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

@GlideModule
public class MyGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_RGB_565));
        int memoryCache  =  1024 * 1024 * 25;
        //自定义内存缓存大小
        builder.setMemoryCache(new LruResourceCache(memoryCache));
        //自定义内置磁盘缓存大小
        int diskCache = 1024 * 1024 * 80;
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,diskCache));
        int bitmapPool = 1024 * 1024 * 25;
        builder.setBitmapPool(new LruBitmapPool(bitmapPool));
    }
}
