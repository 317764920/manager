package com.lltech.manager.util;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.telephony.TelephonyManager;

import com.lcx.mysdk.utils.CommonUtil;
import com.lltech.manager.R;

import org.androidpn.client.Constants;
import org.androidpn.client.ServiceManager;

/**
 * @ClassName(类名) : Push
 * @Description(描述) : 推送
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年6月16日 上午10:48:27
 */
public class Push {
    /**
     * 设备类型
     */
    private static final String DEVICE = "0";
    /**
     * 用户类型
     */
    private static final String USER = "1";

    /**
     * @param context 上下文
     * @param user_id void 用户唯一标识，为空默认填入设备id
     * @throws :
     * @Description(功能描述) : 启动推送服务
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年6月16日 上午10:48:36
     */
    public static void start(Context context, String user_id) {
        setData(context, user_id);
        ServiceManager serviceManager = new ServiceManager(context);
        serviceManager.setNotificationIcon(R.mipmap.icon);
        serviceManager.setPackage("com.lltech.manager");
        serviceManager.startService();
//		serviceManager.setAlias("317764920");
    }

    /**
     * @param context 上下文
     * @throws :
     * @Description(功能描述) : 停止推送服务
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年6月16日 上午10:49:38
     */
    public static void stop(Context context) {
        clearData(context);
        ServiceManager serviceManager = new ServiceManager(context);
        serviceManager.setNotificationIcon(R.mipmap.icon);
        serviceManager.setPackage("com.lltech.manager");
        serviceManager.stopService();
    }

    /**
     * @param context
     * @param user_id void
     * @throws :
     * @Description(功能描述) :  清除登录数据
     * @author(作者) ：  liuchunxu
     * @date (开发日期)          :  2015年6月16日 下午2:39:43
     */
    private static void clearData(Context context) {
        Editor edit = context.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Context.MODE_MULTI_PROCESS).edit();
        edit.clear();
        edit.commit();
    }

    /**
     * @param context void
     * @throws :
     * @Description(功能描述) :  设置登录数据
     * @author(作者) ：  liuchunxu
     * @date (开发日期)          :  2015年6月26日 上午11:22:39
     */
    private static void setData(Context context, String user_id) {
        Editor edit = context.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Context.MODE_MULTI_PROCESS).edit();
        String[] urlparam = MyData.getUrl().split(":");
        edit.putString(Constants.XMPP_HOST, urlparam[0]);
//		edit.putInt(Constants.XMPP_PORT, Integer.parseInt(urlparam[1]));
        if (CommonUtil.isEmpty(user_id)) {
            TelephonyManager telephonemanage = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            edit.putString(Constants.XMPP_USERNAME, telephonemanage.getDeviceId());
            edit.putString(Constants.FLAG, DEVICE);
        } else {
            edit.putString(Constants.XMPP_USERNAME, user_id);
            edit.putString(Constants.FLAG, USER);
        }
        edit.commit();
    }
}
