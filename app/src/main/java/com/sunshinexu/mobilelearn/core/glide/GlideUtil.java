package com.sunshinexu.mobilelearn.core.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class GlideUtil {
    public static void download(Context context, String url, ImageView imageView) {
        GlideApp.with(context).load(url).transition(DrawableTransitionOptions.withCrossFade()).diskCacheStrategy(DiskCacheStrategy.DATA).into(imageView);
    }
}
