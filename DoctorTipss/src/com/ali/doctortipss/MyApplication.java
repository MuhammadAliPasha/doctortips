package com.ali.doctortipss;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class MyApplication extends Application{
	static DisplayImageOptions options;
	@SuppressLint("NewApi")
	@Override
    public void onCreate() {
        super.onCreate();
        
        
//			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDialog().build());
//			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyDeath().build());
		
//        // Create global configuration and initialize ImageLoader with this configuration
//        File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext());
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
//        .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
//        .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null)
//        .taskExecutor(...)
//        .taskExecutorForCachedImages(...)
//        .threadPoolSize(3) // default
//        .threadPriority(Thread.NORM_PRIORITY - 1) // default
//        .tasksProcessingOrder(QueueProcessingType.FIFO) // default
//        .denyCacheImageMultipleSizesInMemory()
//        .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
//        .memoryCacheSize(2 * 1024 * 1024)
//        .memoryCacheSizePercentage(13) // default
//        .discCache(new UnlimitedDiscCache(cacheDir)) // default
//        .discCacheSize(50 * 1024 * 1024)
//        .discCacheFileCount(100)
//        .discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
//        .imageDownloader(new BaseImageDownloader(context)) // default
//        .imageDecoder(new BaseImageDecoder()) // default
//        .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
//        .writeDebugLogs()
//        .build();
//        ImageLoader.getInstance().init(config);
        initImageLoader(getApplicationContext());
    }
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you may tune some of them,
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.
		 options = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.placeholder)
			.showImageForEmptyUri(R.drawable.placeholder)
			.showImageOnFail(R.drawable.placeholder)
			.cacheInMemory(true)
			.cacheOnDisc(true)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.defaultDisplayImageOptions(options)
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
	
}
