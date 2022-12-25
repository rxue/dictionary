package rx.dictionary.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
