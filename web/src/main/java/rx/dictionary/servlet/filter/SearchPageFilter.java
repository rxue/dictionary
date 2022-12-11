package rx.dictionary.servlet.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName = "JSFFilter", urlPatterns = {"/en/zh-CN/search.xhtml"})
public class SearchPageFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		RequestDispatcher rd = request.getRequestDispatcher("../../search.xhtml");
		rd.forward(request, response);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}

}
