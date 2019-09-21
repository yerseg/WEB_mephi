package com.yerseg.web;

import org.apache.commons.codec.digest.MurmurHash3;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebFilter(filterName = "UserFilter")
public class UserFilter implements Filter {

    protected SetOfSessionId setId;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        String uuid = "";
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies != null) {
            if (cookies.length != 0)
                for (Cookie cookie : cookies) {
                    if ("sessionId".equals(cookie.getName())) {
                        uuid = cookie.getValue();
                    }
                }
        }
        String[] addr = httpRequest.getRequestURI().split("/");
        if (setId.containsSessionId(uuid)) {
            httpRequest.getRequestDispatcher("/hello_inside.jsp").forward(req, resp);
        } else {
            if (addr.length != 0 && addr[addr.length - 1] != null) {
                switch (addr[addr.length - 1]) {
                    case "hello_inside.html":
                    case "hello_inside.jsp":
                        httpResponse.sendRedirect(httpRequest.getContextPath() + "/MainServlet/count_to_get_in.html");
                        break;
                    default:
                        chain.doFilter(req, resp);
                        break;

                }
            } else {
                httpResponse.sendRedirect((httpRequest.getContextPath() + "/MainServlet/count_to_get_in.html"));
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {
        setId = SetOfSessionId.getInstance();
    }

}
