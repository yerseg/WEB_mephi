package com.yerseg.web;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "UserFilter")
public class UserFilter implements Filter {

    private SetOfSessionId setId;

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

            try {
                String msg = httpRequest.getParameter("message");
                if (msg != null) {
                    httpRequest.setAttribute("message", msg);
                }
            } catch (NullPointerException ignored) {

            }

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
