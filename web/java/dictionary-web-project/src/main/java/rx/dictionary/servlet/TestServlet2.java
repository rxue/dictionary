package rx.dictionary.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test2")
public class TestServlet2 extends HttpServlet {
	private int counter = 100;
	protected void doGet(HttpServletRequest req,
						 HttpServletResponse resp)
     throws ServletException,
            IOException {
		try(PrintWriter writer = resp.getWriter()) {
			writer.print("current servlet instance id is " + this.hashCode() + ", Thread is " + Thread.currentThread().getId() + ", counter is " + counter++);
		}
	}
}
