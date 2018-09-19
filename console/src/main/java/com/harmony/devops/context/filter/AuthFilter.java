package com.harmony.devops.context.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebFilter
public class AuthFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;

        Map<String, String[]> params =  request.getParameterMap();
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                sb.append(key + "=" +  value + "&");
            }
        }
        logger.info("request uri:" + request.getRequestURI() + " params:" + sb.toString());

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        //这里填写你允许进行跨域的主机ip
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        //允许的访问方法
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }
}
