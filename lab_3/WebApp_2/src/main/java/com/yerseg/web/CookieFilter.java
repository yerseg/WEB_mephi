package com.yerseg.web;

import com.yerseg.web.entities.LoginPassword;
import com.yerseg.web.entities.SessionId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter("/CookieFilter")
public class CookieFilter implements Filter {

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

        if (containsSessionId(uuid)) {

            try {
                String msg = httpRequest.getParameter("message");
                if (msg != null) {
                    httpRequest.setAttribute("message", msg);
                }
            } catch (NullPointerException ignored) {

            }
            String users = getUsers();
            httpRequest.setAttribute("users", users);
            httpRequest.getRequestDispatcher("/users_list.jsp").forward(req, resp);
        } else {
            if (httpRequest.getMethod().equals("POST") || httpRequest.getRequestURI().equals("/"))
                chain.doFilter(req, resp);
            else {
                if ("/WebApp_2_war_exploded/users_list.jsp".equals(httpRequest.getRequestURI())) {
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/");
                } else {
                    chain.doFilter(req, resp);
                }
            }
        }
    }

    private boolean containsSessionId(String uuid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sessionid");
        EntityManager em = emf.createEntityManager();
        boolean contains = false;
        List<SessionId> sessionId = em.createQuery("FROM SessionId").getResultList();
        for (SessionId id : sessionId) {
            if (id.getUuid().equals(uuid)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    private String getUsers() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sessionid");
        EntityManager em = emf.createEntityManager();
        StringBuilder users = new StringBuilder();
        try {
            List<LoginPassword> usersList = em.createQuery("FROM LoginPassword ").getResultList();
            int j = 1;
            for (LoginPassword u : usersList) {
                users.append("<br>").append(Integer.toString(j) + ". ").append(u.toString()).append("</br>");
                j+=1;
            }
        }
        catch (Exception ex){
            return users.toString();
        }
        return users.toString();
    }
}