package edu.hfut.back_end.Utils;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SendUtils {
    protected static final Logger LOG = LoggerFactory.getLogger(SendUtils.class);

    // demo App defined in resources/jpush-api.conf
    protected static final String APP_KEY = "8a44ad1f8ca8a48a829f31f5";
    protected static final String MASTER_SECRET = "fbb049a74628bce5da8d30c7";
    /*protected static final String APP_KEY = "f8d8ccde2fc3cd5c8329e44f";
    protected static final String MASTER_SECRET = "7d764960df68ef8bdec1754e";*/

    public static final String ALERT = "测试广播";

    public static void main(String[] args) {

        testSendPush1();

    }


    public static void testSendPush1() {
        ClientConfig clientConfig = ClientConfig.getInstance();
        final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
        final PushPayload payload = buildPushObject_all_alias_alert();
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);
            System.out.println(result);
            // 如果使用 NettyHttpClient，需要手动调用 close 方法退出进程
            // If uses NettyHttpClient, call close when finished sending request, otherwise process will not exit.
            // jpushClient.close();
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            LOG.error("Sendno: " + payload.getSendno());

        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
            LOG.error("Sendno: " + payload.getSendno());
        }
    }

    public static PushPayload buildPushObject_all_alias_alert() {
        Map<String, String> extras = new HashMap<String, String>();
        extras.put("test", "测试参数");
        // you can set anything you want in this builder, read the document to avoid collision.
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.all())
//                .setMessage(Message.newBuilder()
//                        .setMsgContent("Hi, JPush")
//                        .build())
                .setNotification(Notification.newBuilder()
                        .setAlert("1111")
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle("标题")
                                .addExtras(extras).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .addExtra("extra_key", "extra_value").build())
                        .build())
//                .setSMS(SMS.newBuilder()
//                        .setDelayTime(1000)
//                        .setTempID(2000)
//                        .addPara("Test", 1)
//                        .setActiveFilter(true)
//                        .build())
//                .setNotification3rd(Notification3rd.newBuilder()
//                        .setContent("Hi, JPush")
//                        .setTitle("msg testing")
//                        .setChannelId("channel1001")
//                        .setUriActivity("cn.jpush.android.ui.OpenClickActivity")
//                        .setUriAction("cn.jpush.android.intent.CONNECTION")
//                        .setBadgeAddNum(1)
//                        .setBadgeClass("com.test.badge.MainActivity")
//                        .setSound("sound")
//                        .addExtra("news_id", 124)
//                        .addExtra("my_key", "a value")
//                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .setTimeToLive(43200)
                        .build())
                .build();

    }


    public static PushResult testSendPush() {
        ClientConfig clientConfig = ClientConfig.getInstance();
        final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
        final PushPayload payload = buildPushObject_all_alias_alert();
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("获得结果" + result);
            System.out.println(result);

            return result;
        } catch (APIConnectionException e) {
            LOG.error("连接错误 ", e);
            LOG.error("发送号码为: " + payload.getSendno());

        } catch (APIRequestException e) {
            LOG.error("极光服务器回复错误 ", e);
            LOG.error("Sendno: " + payload.getSendno());
        }
        return null;
    }


    public static PushPayload buildPushObject_android_and_ios() {
        Map<String, String> extras = new HashMap<String, String>();
        extras.put("内容1", "我就是内容1111");
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias("haha"))
                .setNotification(Notification.newBuilder()
                        .setAlert("弹框内容")
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle("弹框标题")
                                .addExtras(extras).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .addExtra("extra_key", "extra_value").build())
                        .build())
                .build();
    }

    public static PushPayload buildPushObject_alias_alert(String alias, String name, String message, BigInteger orderId) {
        Map<String, String> extras = new HashMap<String, String>();
        extras.put("orderId", orderId.toString());
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        extras.put("time", format);
        // you can set anything you want in this builder, read the document to avoid collision.
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                        .setAlert(name)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(message)
                                .addExtras(extras).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .addExtra("extra_key", "extra_value").build())
                        .build())
                .build();

    }

    public static PushResult SendPush(String alias, String name, String message, BigInteger orderId) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
        final PushPayload payload = buildPushObject_alias_alert(alias, name, message, orderId);
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("获得结果" + result);
            return result;
        } catch (APIConnectionException e) {
            LOG.error("连接错误 ", e);
            LOG.error("发送号码为: " + payload.getSendno());
        } catch (APIRequestException e) {
            LOG.error("极光服务器回复错误 ", e);
            LOG.error("Sendno: " + payload.getSendno());
        }
        return null;
    }


}
