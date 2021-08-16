//package filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter(urlPatterns = {"/addNews","/deleteNews","/updateNews","/home","/singleNews","/log"})
//public class AFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req= (HttpServletRequest) servletRequest;
//        HttpServletResponse resp= (HttpServletResponse) servletResponse;
//        Object user =req.getSession().getAttribute("user");
//
//        if (user==null){
//            resp.sendRedirect("/");
//        }else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
