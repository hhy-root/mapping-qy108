package com.aaa.six.annotation;

import com.aaa.six.service.IQYService;
import com.aaa.six.model.User;
import com.aaa.six.utils.DateUtils;
import com.aaa.six.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

<<<<<<< HEAD
import static com.aaa.six.staticstatus.TimeProperties.TIME_TYPE;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/28 16:32
 * @Description
 */
import static com.aaa.six.staticstatus.TimeProperties.*;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-05-28 10:46
 * @description:
 **/
>>>>>>> origin/master
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private IQYService iqyService;

    /**
<<<<<<< HEAD
     * @author hhy
     * @description
     *    定义一个切面
     *      之前学过的切面一般的情况下切类，还可以使用通配符，或者切整个包
     *      com.aaa.*.controller
     *      所以今天要切这个咱们自定义的注解
     *
     *      也就是说当检测到这个注解存在的时候，aop才会生效
     * @param: []
     * @date 2020/5/28 16:37
     * @return void
     * @throws
     */
    @Pointcut("@annotation(com.aaa.six.annotation.LoginLogAnnotation)")
    public void pointcut(){
        //TODO 什么都不写，只是定义具体切哪一块
    }

    /**
     * @author hhy
     * @description
     *    定义环形切点
     *    proceedingJoinPoint
     *      里面封装了目标路径中的所有参数，可以任意获取
     * @param: [proceedingJoinPoint]
     * @date 2020/5/28 16:44
     * @return java.lang.Object
     * @throws
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws ClassNotFoundException {

        //1.定义返回值
        Object result = null;
        try{
            result = proceedingJoinPoint.proceed();
        }catch (Throwable throwable ){
            throwable.printStackTrace();
        }

        //2.获取username信息，其实username信息在方法的参数中，
        // 也就是说只要获取到了目标方法的参数，就能拿到username值
        Object[] args = proceedingJoinPoint.getArgs();
        User user =null;
        for(Object arg : args){
            user = (User) arg;
        }
=======
     * @author lwq
     * @description
     *    定义一个切面
     * @param: []
     * @date 2020/5/28
     * @return void
     * @throws
     **/
    @Pointcut("@annotation(com.aaa.six.annotation.LoginLogAnnotation)")
    public void pointcut(){

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws ClassNotFoundException {
        // 1.定义返回值
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        // 2.获取username信息,其实username信息在方法的参数中，也就是说只要获取到了目标方法的参数就能拿到username的值
        Object[] args = proceedingJoinPoint.getArgs();
        User user = null;
        for(Object arg : args) {
            user = (User) arg;
        }
        System.out.println(user.getUsername());
>>>>>>> origin/master

        // 3.获取loginTime
        // 会使用到一个新的jar包，这个jar包是时间转换的一个工具类
        String dateTime = DateUtils.formatDate(new Date(), TIME_TYPE);

        // 4.获取ip地址
        // 先得获取Request对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        System.out.println(request);
        String ip = IPUtils.getIpAddr(request);

        // 5.获取opeationType和operationName
        // 获取目标方法所属类的这个名称
        // 这个className获取的是全限定名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        // 获取方法名(目标方法的方法名)
        String targetMethodName = proceedingJoinPoint.getSignature().getName();
        // 通过反射信息获取类的对象
        Class targetClass = Class.forName(className);
        // 获取类中的所有方法
        Method[] methods = targetClass.getMethods();
        String operationName = "";
        String operationType = "";
        // 使用循环来进行对比判断
        for(Method method : methods) {
            String methodName = method.getName();
            // 判断两个methodname是否相等
            if(methodName.equals(targetMethodName)) {
                // 需要考虑到方法的重载
                // 还需要对应方法中参数的个数
                Class[] parameterTypes = method.getParameterTypes();
                // 再次判断两个方法的参数个数是否相同
                if(parameterTypes.length == args.length) {
                    // 获取到了最终目标方法
                    operationName = method.getAnnotation(LoginLogAnnotation.class).operationName();
                    operationType = method.getAnnotation(LoginLogAnnotation.class).operationType();
                }
            }
        }

<<<<<<< HEAD
        Map map = new HashMap();
        map.put("username",user.getUsername());
        map.put("login_time",dateTime);
        map.put("ip",ip);
        map.put("operation_type",operationType);
        map.put("operation_name",operationName);

        iqyService.addLoginLog(map);

        return result;// 如果不return则永远不会跳转回目标controller

    }

=======

        Map map = new HashMap();
        map.put("username", user.getUsername());
        map.put("loginTime", dateTime);
        map.put("ip", ip);
        map.put("operationType", operationType);
        map.put("operationName", operationName);

        iqyService.addLoginLog(map);
        // 如果不return则永远不会跳转回目标controller
        return result;

    }
>>>>>>> origin/master
}
