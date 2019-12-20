package com.yerseg.web;

import com.yerseg.web.entities.LoginPassword;
import com.yerseg.web.entities.SessionId;
import org.apache.commons.codec.digest.MurmurHash3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String msg = request.getParameter("message");
            if (msg != null) {
                request.setAttribute("message", msg);
            }
        } catch (NullPointerException ignored) {
        }
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String hashPassword = String.valueOf(MurmurHash3.hash32(request.getParameter("password")));
        UUID uuid;
        if (checkUser(login, hashPassword)) {
            if (!checkUserId(login).equals("")) {
                uuid = UUID.fromString(checkUserId(login));
            } else {
                uuid = UUID.randomUUID();
                addUserId(login, uuid.toString());
            }

            Cookie cookie = new Cookie("sessionId", uuid.toString());
            response.addCookie(cookie);
            response.sendRedirect(request.getContextPath() + "/users_list.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    private boolean checkUser(String login, String hashedPassword) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("loginpassword");
        EntityManager em = emf.createEntityManager();
        try {
            LoginPassword user = em.createQuery("SELECT u FROM LoginPassword u WHERE u.login = :login", LoginPassword.class)
                    .setParameter("login", login)
                    .getSingleResult();
            return user.getHashedPassword().equals(hashedPassword);
        } catch (Exception ex) {
            return false;
        }
    }

    private void addUserId(String login, String uuid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sessionid");
        EntityManager em = emf.createEntityManager();
        SessionId user = new SessionId();
        user.setUuid(uuid);
        user.setLogin(login);
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    private String checkUserId(String login) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sessionid");
        EntityManager em = emf.createEntityManager();
        try {
            SessionId user = em.createQuery("SELECT u FROM SessionId u WHERE u.login = :login", SessionId.class)
                    .setParameter("login", login)
                    .getSingleResult();
            if (user.getLogin().equals(login))
                return user.getUuid();
            else
                return "";
        } catch (Exception ex) {
            return "";
        }
    }
}
