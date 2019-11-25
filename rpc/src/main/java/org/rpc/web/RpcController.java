package org.rpc.web;

import org.rpc.config.ReqUrl;
import org.rpc.config.RpcBean;
import org.rpc.config.RpcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
public class RpcController {
    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private RpcBean rpcBean;

    @GetMapping("/rpc")
    public Object r() {

        return rpcBean.toString();
    }

    @GetMapping("/api")
    public String r2() throws NoSuchFieldException, IllegalAccessException {
        String ss = "xxx";
        System.out.println(rpcBean);

        Class clazz = RpcTest.class;
        Method[] methods=clazz.getDeclaredMethods();
        List<ReqUrl> reqUrls=new ArrayList<>();
        for (Method method : methods) {
            ReqUrl reqUrl=method.getAnnotation(ReqUrl.class);
            if(Objects.nonNull(reqUrl)) {
                reqUrls.add(reqUrl);
            }
        }
       // Annotation annotation[] = clazz.getAnnotationsByType(ReqUrl.class);
        for (ReqUrl reqUrl: reqUrls) {
            String value2 = reqUrl.path().substring(2, reqUrl.path().length() - 1);

            System.out.println(reqUrl.path());
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(reqUrl);
            Field value = invocationHandler.getClass().getDeclaredField("memberValues");
            value.setAccessible(true);
            Map<String, String> mem = (Map<String, String>) value.get(invocationHandler);
            mem.put("path", rpcBean.getService().get(value2));
            ss = reqUrl.path();
        }
        return ss;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Class clazz = RpcBean.class;
        System.out.println(clazz.getName());
        Method[] method=clazz.getDeclaredMethods();
        ReqUrl annotation = method[0].getAnnotation(ReqUrl.class);
//System.out.println(annotation.path());
            //System.out.println(annotation.length);

           // System.out.println(annotation[0].annotationType());



    }
}
