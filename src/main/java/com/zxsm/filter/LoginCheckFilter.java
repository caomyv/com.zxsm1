package com.zxsm.filter;


import com.alibaba.fastjson.JSON;
import com.zxsm.common.BaseContext;
import com.zxsm.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {

    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        //1.获取本次请求的url；
        String requestURL=request.getRequestURI();
        log.info("拦截到请求：{}",requestURL);
        //1.1.定义不需要处理的请求路径
        String[] urls=new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };
        //2.判断是否需要拦截
        boolean check=check(urls,requestURL);

        //3.如果不需要处理，直接放行
        if (check){
            log.info("本次请求{}不需要处理",requestURL);
            filterChain.doFilter(request,response);
            return;
        }
        //4.判断登录状态，如果已经登录，放行
        if(request.getSession().getAttribute("employee")!=null){
            log.info("用户已登录，用户id为：{}",request.getSession().getAttribute("employee"));
            Long empID=(Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empID);
            filterChain.doFilter(request,response);
            return;
        }
        log.info("用户未登录");
        //5.如果未登录，则返回未登录，通过输出流方式向客户端响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    /**
     *路径匹配
     * @param requestURL
     * @return
     */

    private boolean check(String[] urls, String requestURL){
        for (String url: urls) {
            boolean match=PATH_MATCHER.match(url,requestURL);
            if (match){
                return true;
            }
        }
        return false;
    }
}
