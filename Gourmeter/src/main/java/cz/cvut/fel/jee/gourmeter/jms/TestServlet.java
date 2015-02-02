package cz.cvut.fel.jee.gourmeter.jms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.jms.JMSDestinationDefinition;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.cvut.fel.jee.gourmeter.helper.EmailMessage;

@JMSDestinationDefinition(
		name = "java:global/jms/myQueue",
		interfaceName = "javax.jms.Queue",
        destinationName="queue1234",
        description="My Queue")
@WebServlet(urlPatterns = {"/jmstest"})
public class TestServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	
	@EJB MessageSender sender;
	
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>JMS2 Send Message</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>JMS2 Send/Receive Message using JMS2 " + request.getContextPath() + "</h1>");

            EmailMessage em = new EmailMessage();
            em.setReceiverEmail("receiver@email.com");
            em.setTemplateName("someEmailTemplate");
            em.setParams(new HashMap<>());
            
            sender.sendMessage(em);
            out.format("Message sent to: %1$s.<br>", em.getReceiverEmail());
            out.println("Receiving message...<br>");
            
            // synchronne to posilat nechceme - pro vysledek koukni do logu
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}