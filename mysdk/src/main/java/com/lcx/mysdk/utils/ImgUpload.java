package com.lcx.mysdk.utils;

import java.io.File;
import java.util.List;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

public class ImgUpload {
	// 参数类型+
	private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
	// 创建OkHttpClient实例
	private static OkHttpClient client = new OkHttpClient();

	public static MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);

	public static void upload(String url, List<String> paths, Callback callback) {
		for (int i = 0; i < paths.size(); i++) {
			File file = new File(paths.get(i));
			if (file != null) {
				builder.addFormDataPart("file", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
			}
		}
		RequestBody requestBody = builder.build();
		Request request = new Request.Builder().url(url).post(requestBody).build();
		// 发送异步请求，同步会报错，Android4.0以后禁止在主线程中进行耗时操作
		client.newCall(request).enqueue(callback);
	}

	public static void upload(String url, String path, Callback callback) {
		File file = new File(path);
		if (file != null) {
			builder.addFormDataPart("file", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
		}
		RequestBody requestBody = builder.build();
		Request request = new Request.Builder().url(url).post(requestBody).build();
		// 发送异步请求，同步会报错，Android4.0以后禁止在主线程中进行耗时操作
		client.newCall(request).enqueue(callback);
	}
}
