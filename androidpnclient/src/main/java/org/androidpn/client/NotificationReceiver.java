/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.androidpn.client;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Broadcast receiver that handles push notification messages from the server. This should be
 * registered as receiver in AndroidManifest.xml.
 * 
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public final class NotificationReceiver extends BroadcastReceiver {

	private static final String LOGTAG = LogUtil.makeLogTag(NotificationReceiver.class);

	private SharedPreferences sharedPrefs;

	// private NotificationService notificationService;

	public NotificationReceiver() {
	}

	// public NotificationReceiver(NotificationService notificationService) {
	// this.notificationService = notificationService;
	// }

	@Override
	public void onReceive(Context context, Intent intent) {
		sharedPrefs = context.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
		Log.d(LOGTAG, "NotificationReceiver.onReceive()...");
		String action = intent.getAction();
		Log.d(LOGTAG, "action=" + action);

		if ((sharedPrefs.getString(Constants.PACKEGE, Constants.PACKEGE) + Constants.ACTION_SHOW_NOTIFICATION).equals(action)) {
			String notificationId = intent.getStringExtra(Constants.NOTIFICATION_ID);
			String notificationApiKey = intent.getStringExtra(Constants.NOTIFICATION_API_KEY);
			String notificationTitle = intent.getStringExtra(Constants.NOTIFICATION_TITLE);
			String notificationMessage = intent.getStringExtra(Constants.NOTIFICATION_MESSAGE);
			String notificationUri = intent.getStringExtra(Constants.NOTIFICATION_URI);
			String notificationImgUrl = intent.getStringExtra(Constants.NOTIFICATION_IMGURL);

			Log.d(LOGTAG, "notificationId=" + notificationId);
			Log.d(LOGTAG, "notificationApiKey=" + notificationApiKey);
			Log.d(LOGTAG, "notificationTitle=" + notificationTitle);
			Log.d(LOGTAG, "notificationMessage=" + notificationMessage);
			Log.d(LOGTAG, "notificationUri=" + notificationUri);
			Log.d(LOGTAG, "notificationImgUrl=" + notificationImgUrl);

			Notifier notifier = new Notifier(context);
			notifier.notify(notificationId, notificationApiKey, notificationTitle, notificationMessage, notificationUri, notificationImgUrl);
		} else if ((sharedPrefs.getString(Constants.PACKEGE, Constants.PACKEGE) + Constants.ACTION_NOTIFICATION_CLICKED).equals(action)) {
			String notificationId = intent.getStringExtra(Constants.NOTIFICATION_ID);
			String notificationApiKey = intent.getStringExtra(Constants.NOTIFICATION_API_KEY);
			String notificationTitle = intent.getStringExtra(Constants.NOTIFICATION_TITLE);
			String notificationMessage = intent.getStringExtra(Constants.NOTIFICATION_MESSAGE);
			String notificationUri = intent.getStringExtra(Constants.NOTIFICATION_URI);
			String notificationImgUrl = intent.getStringExtra(Constants.NOTIFICATION_IMGURL);

			Log.d(LOGTAG, "notificationId=" + notificationId);
			Log.d(LOGTAG, "notificationApiKey=" + notificationApiKey);
			Log.d(LOGTAG, "notificationTitle=" + notificationTitle);
			Log.d(LOGTAG, "notificationMessage=" + notificationMessage);
			Log.d(LOGTAG, "notificationUri=" + notificationUri);
			Log.d(LOGTAG, "notificationImgUrl=" + notificationImgUrl);

			// Intent detailsIntent = new Intent();
			// detailsIntent.setClassName("com.lltech.sqzy",
			// "com.lltech.sqzy.activity.LoginActivity");
			// detailsIntent.putExtras(intent.getExtras());
			// detailsIntent.putExtra(Constants.NOTIFICATION_ID,
			// notificationId);
			// detailsIntent.putExtra(Constants.NOTIFICATION_API_KEY,
			// notificationApiKey);
			// detailsIntent.putExtra(Constants.NOTIFICATION_TITLE,
			// notificationTitle);
			// detailsIntent.putExtra(Constants.NOTIFICATION_MESSAGE,
			// notificationMessage);
			// detailsIntent.putExtra(Constants.NOTIFICATION_URI,
			// notificationUri);
			// detailsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// detailsIntent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
			//
			// try {
			// context.startActivity(detailsIntent);
			// } catch (Exception e) {
			// Log.e(LOGTAG, "没有打开应用");
			// }

		} else if ((sharedPrefs.getString(Constants.PACKEGE, Constants.PACKEGE) + Constants.ACTION_NOTIFICATION_CLEARED).equals(action)) {
		}

	}

}
