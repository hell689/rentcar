package net.rentcar.servlet;

import domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (url.equalsIgnoreCase("index.html") ||
                url.equalsIgnoreCase("login.html") ||
                url.equalsIgnoreCase("registration.html")) {
            filterChain.doFilter(request, response);
        } else {
            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("currentUser");
            if (Objects.nonNull(currentUser)) {
                filterChain.doFilter(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/index.html");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
