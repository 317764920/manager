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

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * 
 * @ClassName(类名) : NotificationPacketListener
 * @Description(描述) : 修改
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年6月19日 上午10:28:17
 *
 */
public class NotificationPacketListener implements PacketListener {

	private static final String LOGTAG = LogUtil.makeLogTag(NotificationPacketListener.class);

	private final XmppManager xmppManager;

	private SharedPreferences sharedPrefs;

	public NotificationPacketListener(XmppManager xmppManager) {
		this.xmppManager = xmppManager;
		sharedPrefs = xmppManager.getContext().getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
	}

	@Override
	public void processPacket(Packet packet) {
		Log.d(LOGTAG, "NotificationPacketListener.processPacket()...");
		Log.d(LOGTAG, "packet.toXML()=" + packet.toXML());

		if (packet instanceof NotificationIQ) {
			NotificationIQ notification = (NotificationIQ) packet;

			if (notification.getChildElementXML().contains("androidpn:iq:notification")) {
				String notificationId = notification.getId();
				String notificationApiKey = notification.getApiKey();
				String notificationTitle = notification.getTitle();
				String notificationMessage = notification.getMessage();
				// String notificationTicker = notification.getTicker();
				String notificationUri = notification.getUri();
				String notificationImgUrl = notification.getImgUrl();

				Intent intent = new Intent((sharedPrefs.getString(Constants.PACKEGE, Constants.PACKEGE) + Constants.ACTION_SHOW_NOTIFICATION));
				intent.putExtra(Constants.NOTIFICATION_ID, notificationId);
				intent.putExtra(Constants.NOTIFICATION_API_KEY, notificationApiKey);
				intent.putExtra(Constants.NOTIFICATION_TITLE, notificationTitle);
				intent.putExtra(Constants.NOTIFICATION_MESSAGE, notificationMessage);
				intent.putExtra(Constants.NOTIFICATION_URI, notificationUri);
				intent.putExtra(Constants.NOTIFICATION_IMGURL, notificationImgUrl);
				// intent.setData(Uri.parse((new StringBuilder(
				// "notif://notification.androidpn.org/")).append(
				// notificationApiKey).append("/").append(
				// System.currentTimeMillis()).toString()));

				// 回执
				ConfirmIQ confirmIQ = new ConfirmIQ();
				confirmIQ.setUuid(notificationId);
				confirmIQ.setUsername(xmppManager.getUsername());
				confirmIQ.setType(IQ.Type.SET);
				XMPPConnection connection = xmppManager.getConnection();
				connection.sendPacket(confirmIQ);
				Log.d("Confirm", "回执");
				Log.d("Confirm", confirmIQ.toXML());
				xmppManager.getContext().sendBroadcast(intent);
			}
		}

	}

}
