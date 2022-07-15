package rx.backoffice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.naming.Context;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import rx.practice.ejb.RemoteEJB;

@WebServlet("/callejb")
public class EJBInvokerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//@EJB(lookup="java:/ejb/RemoteEJB")
	//private RemoteEJB remoteEJB;
	@Inject
	private Context context;

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
        	//final Context context = new javax.naming.InitialContext();
        	final RemoteEJB bean = (RemoteEJB) context.lookup("ejb:/ejb-executor/RemoteEJBImpl!rx.practice.ejb.RemoteEJB");
        	System.out.println("::::Going to invoke test()");
        	bean.test();
        } catch (Exception e) {
        	for (Throwable t : e.getSuppressed()) {
        		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        		t.printStackTrace();
        	}
        }
		
		try (PrintWriter out = response.getWriter()) {
			out.println("<html><head>");
			out.println("<title>MyServlet</title>");
			out.println("</head><body>");
			out.println("<h1>My Servlet will invoke a remote ejb: no Context Factory defined</h1>");
			out.println("</body></html>");
		}
	}
}
