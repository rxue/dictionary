package rx.dictionary.application;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="search", urlPatterns="/search")
public class SearchServlet extends HttpServlet {
	@Override
	protected void	doGet(HttpServletRequest req, HttpServletResponse resp) { 
		resp.setContentType("image/jpeg");
		ServletContext ctx = getServletContext();
		try(OutputStream os = resp.getOutputStream();
				InputStream is = ctx.getResourceAsStream("resources/img/bucket.jpg")) {
	        byte[] allBytes = is.readAllBytes();
			//long bytes = Files.copy(Paths.get("resources", "img","bucket.jpg"), os);
	        os.write(allBytes, 0, allBytes.length);
			System.out.println("Number of bytes read and wrote is " + allBytes.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
