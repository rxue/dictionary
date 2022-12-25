package rx.dictionary.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/test")
public class TestServlet extends HttpServlet {
	private int counter = 0;
	protected void doGet(HttpServletRequest req,
            HttpServletResponse resp)
     throws ServletException,
            IOException {
		try(PrintWriter writer = resp.getWriter()) {
			System.out.println("::::::::::: test: current servlet instance hashcode is " + this.hashCode() + ", thread id is " + Thread.currentThread().getId());
			Thread.sleep(1000*10);
			writer.print("Thread 2 is " + Thread.currentThread().getId() + ", counter is " + counter++);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
