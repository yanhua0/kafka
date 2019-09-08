package org.gexin.tui;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;

import java.util.ArrayList;
import java.util.List;

public class App {
    private static String appId = "u9WeziFUzy9jSTFtVQUep3";
    private static String appKey = "tKFqM1ELbyAmyd73CWnOi3";
    private static String masterSecret = "1KReybVI6X5XviG26KzcfA";
    private static String url  = "http://sdk.open.api.igexin.com/apiex.htm";
    public static void main( String[] args ){
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
        LinkTemplate template = new LinkTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTitle("push");
        template.setText("hello,world~~~");
        template.setUrl("http://getui.com");
        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);

        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);

        IPushResult ret = push.pushMessageToApp(message);
        System.out.println(ret.getResponse().toString());
    }
}
