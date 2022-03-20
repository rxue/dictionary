package rx.dictionary.application;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebServlet(name="search", urlPatterns="/search")
public class SearchServlet extends HttpServlet {
	@Override
	protected void	doGet(HttpServletRequest req, HttpServletResponse resp) {
		String word = req.getParameter("word");
		if (!StringUtils.isEmpty(word)) {
			resp.setContentType("image/jpeg");
			ServletContext ctx = getServletContext();
			try(OutputStream os = resp.getOutputStream();
					InputStream is = ctx.getResourceAsStream(getImagePath(word))) {
		        transfer(is, os);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private static String getImagePath(String word) {
		return "resources/img/" + word + ".jpg";
	}
	
	/**
	 * Mostly copied from https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/io/InputStream.java 
	 * 
	 * => once code updated to Java 9 or higher, this can be fully replaced by InputStream.transfer, which is since Java 9
	 * 
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	private static long transfer(InputStream in, OutputStream out) throws IOException {
		final int defaultBufferSize = 8192;
		Objects.requireNonNull(out, "out");
        long transferred = 0;
        byte[] buffer = new byte[defaultBufferSize];
        int read;
        while ((read = in.read(buffer, 0, defaultBufferSize)) >= 0) {
            out.write(buffer, 0, read);
            transferred += read;
        }
        return transferred;
	}
}
