package rx.dictionary.application;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="search", urlPatterns="/search")
public class SearchServlet extends HttpServlet {
	@Override
	protected void	doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException { 
		resp.setContentType("image/jpeg");
		try(OutputStream os = resp.getOutputStream()) {
			System.out.println("Going to write of IO");
			long bytes = Files.copy(Paths.get("resources","img","bucket.jpg"), os);
			System.out.println("Number of bytes read and wrote is " + bytes);
		}
	}
}
