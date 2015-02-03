package cz.cvut.fel.jee.gourmeter.jms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.jms.JMSDestinationDefinition;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.cvut.fel.jee.gourmeter.helper.EmailMessage;

@JMSDestinationDefinition(name = "java:global/jms/myQueue", interfaceName = "javax.jms.Queue", destinationName = "queue1234", description = "My Queue")
@WebServlet(urlPatterns = { "/jmstest" })
public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	MessageSender sender;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>Pøíklad na použití JMS</h1>");
			out.println("<ol>"
					+ "<li>Dojde k vytvoøení entity EmailMessage, která se pomocí senderu vlozi do fronty</li>"
					+ "<li>MessageReceiver, který poslouchá, odchytne zprávu a zavolá metodu pro odeslání emailu</li>"
					+ "</ol>");

			EmailMessage em = new EmailMessage();
			Map<String, Object> params = new HashMap<>();
			params.put("login", "uzivatel123");
			em.setReceiverEmail("receiver@email.com");
			em.setTemplateName("InvitationMail");
			em.setParams(params);

			sender.sendMessage(em);
			out.format("Message sent to: %1$s.<br>", em.getReceiverEmail());
			out.println("Receiving message...<br>");

			out.println("</body>");
			out.println("</html>");
		}
	}

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}