package rx.dictionary.servlet.filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

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
