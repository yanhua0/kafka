package NettyDemo.echo.client;

import com.test.netty.Person;
import com.test.netty.utils.ByUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class HttpClientDemo {
    public static void main(String[] args) throws ClientProtocolException,
            IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpHost proxy = new HttpHost("0.0.0.0",
                11101);
        httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
                proxy);
        HttpPost httppost = new HttpPost("0.0.0.0");
        Person person=new Person();
        person.setUsername("111");
        ByteArrayEntity entity = new ByteArrayEntity(ByUtils.ObjectToByte(person));
        httppost.setEntity(entity);
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity receiveEntity = response.getEntity();
        System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine());
        if (receiveEntity != null) {
            System.out.println("Response content length: " + receiveEntity.getContentLength());
        }
        System.out.println("success");
    }
}
