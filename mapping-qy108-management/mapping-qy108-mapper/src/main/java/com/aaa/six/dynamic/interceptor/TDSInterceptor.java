package com.aaa.six.dynamic.interceptor;

import com.aaa.six.dynamic.annotation.TDS;
import com.aaa.six.dynamic.datasource.DynamicDataSourceContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/31 15:31
 * @Description
 */
@Component
public class TDSInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        TDS annotation = handlerMethod.getMethod().getAnnotation(TDS.class);
        if(null == annotation) {
            annotation = handlerMethod.getMethod().getDeclaringClass().getAnnotation(TDS.class);
        }
        if(null != annotation && !"".equals(annotation.value())) {
            DynamicDataSourceContextHolder.setDatasourceType(annotation.value());
        }
        return true;
    }
}

