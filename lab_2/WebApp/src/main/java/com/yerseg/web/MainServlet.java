package com.yerseg.web;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.apache.commons.codec.digest.MurmurHash3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    protected Multimap<Integer, String> multiMap;

    protected SetOfSessionId setId;

    public void init() {
        multiMap = HashMultimap.create();
        setId = SetOfSessionId.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Random random = new Random(System.currentTimeMillis());
        int number1 = -125 + random.nextInt(347 - 125 + 1);
        int number2 = -125 + random.nextInt(347 - 125 + 1);
        int seed = (int) (System.currentTimeMillis() % 100000);
        String hash = String.valueOf(MurmurHash3.hash32(number1, number2, seed));
        multiMap.put(number1 + number2, hash);
        request.setAttribute("number1", number1);
        request.setAttribute("number2", number2);
        request.setAttribute("hashNum", hash);

        try {
            String msg = (String) request.getParameter("message");
            if (msg != null) {
                request.setAttribute("message", msg);
            }
        } catch (NullPointerException ex) {

        }

        request.getRequestDispatcher("/count_to_get_in.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userAnswer = Integer.parseInt(request.getParameter("answer"));
        String hash = request.getParameter("hash");
        if (checkUserAnswer(userAnswer, hash)) {
            UUID uuid = UUID.randomUUID();
            setId.putSessionId(uuid.toString());
            Cookie cookie = new Cookie("sessionId", uuid.toString());
            response.addCookie(cookie);
            response.sendRedirect(request.getContextPath() + "/hello_inside.html");
        } else {
            response.sendRedirect(request.getContextPath() + "/count_to_get_in.html");
        }
    }

    protected boolean checkUserAnswer(int answer, String hash) {
        if (multiMap.containsKey(answer)) {
            if (multiMap.get(answer).contains(hash)) {
                return true;
            }
        }
        return false;
    }
}
