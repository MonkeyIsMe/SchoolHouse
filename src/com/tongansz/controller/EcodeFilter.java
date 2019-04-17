package com.tongansz.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
public class EcodeFilter implements Filter {
    // 定义编码方式 默认为空
    private String encoding =null;
    public void destroy() {
    }
    // 自动调用过滤方法 doFilter
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // 如果编码不为空就过滤请求中的数据
        if (encoding != null) {
            // 设置request字符编码
            request.setCharacterEncoding(encoding);
            // 设置response字符编码
            response.setContentType("text/html;charset=" + encoding);
        }
        // 传递给下一个过滤器或者下一个servlet/jsp 
        chain.doFilter(request, response);
    }
    public void init(FilterConfig config) throws ServletException {
        //将web.xml中的过滤器的配置编码取出来赋值给  encoding
        encoding=config.getInitParameter("encoding");
    }
}
