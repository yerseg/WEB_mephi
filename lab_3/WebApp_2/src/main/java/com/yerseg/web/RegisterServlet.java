package com.yerseg.web;

import com.yerseg.web.entities.LoginPassword;
import org.apache.commons.codec.digest.MurmurHash3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String hashPassword = String.valueOf(MurmurHash3.hash32(request.getParameter("password")));
        if (!checkUser(login, hashPassword)) {
            addUser(login, hashPassword);
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/registration.jsp").forward(request, response);
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

    private void addUser(String login, String hashedPassword) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("loginpassword");
        EntityManager em = emf.createEntityManager();
        LoginPassword user = new LoginPassword();
        user.setLogin(login);
        user.setHashedPassword(hashedPassword);
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
}
