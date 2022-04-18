package rx.dictionary.application;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import rx.dictionary.GlossaryAlbum;

@WebServlet(name="search", urlPatterns="/searchx")
public class SearchServlet extends HttpServlet {
	@Inject
	private GlossaryAlbum glossaryAlbum;
	@Override
	protected void	doGet(HttpServletRequest req, HttpServletResponse resp) {
		Cookie[] cookies = req.getCookies();
		if (cookies != null)
			for (Cookie c : cookies) 
				System.out.println("got cookie data from browser: " + c.getName() + ":" + c.getValue());
		String word = req.getParameter("word");
		if (!StringUtils.isEmpty(word)) {
			resp.setContentType("image/jpeg");
			Cookie cookie = new Cookie("testsecured","valuex");
			cookie.setSecure(true);
			resp.addCookie(cookie);
			Cookie insecCookie = new Cookie("insecuredcookie","value insecured");
			insecCookie.setSecure(false);
			resp.addCookie(insecCookie);
			Cookie zeroCookie = new Cookie("zerocookie","value zerp");
			zeroCookie.setMaxAge(0);
			resp.addCookie(zeroCookie);
			ServletContext ctx = getServletContext();
			resp.reset();
			try(OutputStream os = resp.getOutputStream();
					InputStream is = ctx.getResourceAsStream(glossaryAlbum.getImagePath(word))) {
		        transfer(is, os);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
