//package org.test.config;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Field;
//
///**
// * aop日志
// */
////@Aspect
////@Component
//public class HttpAspect {
//
//    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);
//    @Pointcut("execution(public * org.test.web.*.*(..))")
//    public void log() {
//    }
//
//    @Before("log()")
//    public void doBefore(JoinPoint joinPoint) throws IllegalAccessException {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        Object[] obj=joinPoint.getArgs();
//        String sr=obj[0].getClass().getTypeName();//获取类名
//        Field[] fields=obj[0].getClass().getDeclaredFields();//获取类的所有参数
//        System.out.println(sr);
//        if(sr.equals("java.lang.String")){
//            System.out.println("字符串类型");
//            return;
//        }
//        for(Field f:fields){
//
//           System.out.println(f.getName()+"$");//获取参数的名字
//            if(f.getName().contains("name")){
//                f.setAccessible(true);//允许获取私有值
//                System.out.println("自动含有名字"+"@"+f.get(obj[0]));//获取参数值
//            }
//        }
//        logger.info("123"+obj[0].getClass());
//        logger.info("123"+obj[0].getClass().getTypeName());
//       //url
//        logger.info("url={}", request.getRequestURL());
//
//        //method
//        logger.info("method={}", request.getMethod());
//
//        //ip
//        logger.info("ip={}", request.getRemoteAddr());
//
//
//        //类方法
//        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//
//        //参数
//        logger.info("args={}", joinPoint.getArgs());
//
//    }
//
//    @After("log()")
//    public void doAfter() {
//        logger.info("方法执行完毕-------");
//    }
//
//    @AfterReturning(returning = "object", pointcut = "log()")
//    public void doAfterReturning(Object object) {
//        if(object!=null){
//            logger.info("response={}", object.toString());//防止报指针异常
//        }else{
//            logger.info("response={}", "null");
//        }
//
//    }
//    //    异常通知 ，将在方法抛出异常时触
//    @AfterThrowing(value = "log()",throwing ="e")
//    public void  afterThrowing(JoinPoint joinPoint,Exception e){
//        String name = joinPoint.getSignature().getName();
//        logger.error("返回通知：异常{}"+e+"方法名"+name);
//    }
//}
