package com.gzz.console.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sevlet/demo")
public class DemoSevlet extends HttpServlet {

	private static final long serialVersionUID = -8977091674045458433L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Hello Sevlet demo 999 - 004");
        response.getWriter().println("Hello Sevlet demo 999 - 005");
        response.getWriter().println("Hello Sevlet demo 999 - 006");
        response.getWriter().flush();
        response.getWriter().close();
    }
}
