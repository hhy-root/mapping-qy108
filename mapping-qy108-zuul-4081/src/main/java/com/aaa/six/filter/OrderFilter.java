package com.aaa.six.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/6/4 7:54
 * @Description
 */
public class OrderFilter extends ZuulFilter {

    public String filterType() {
        return "pre";
    }

    public int filterOrder() {
        return 0;
    }

    public boolean shouldFilter() {
        return false;
    }

    public Object run() throws ZuulException {
        return null;
    }
}
