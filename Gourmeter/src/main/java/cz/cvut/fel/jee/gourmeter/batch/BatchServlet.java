package cz.cvut.fel.jee.gourmeter.batch;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.operations.JobSecurityException;
import javax.batch.operations.JobStartException;
import javax.batch.runtime.BatchRuntime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.cvut.fel.jee.gourmeter.ejb.FacilitySessionBean;

/**
 * Servlet implementation class BatchServlet
 */
@WebServlet("/batch/testBatch")
public class BatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory
			.getLogger(FacilitySessionBean.class);	

	public BatchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			JobOperator jo = BatchRuntime.getJobOperator();
			Properties p = new Properties();
			log.info("Starting Batch job...");
			out.println("Starting Batch job...");
			long id = jo.start("kontrolaDB", p);			
			log.info("The job was successfully submitted with id: " + id);
			out.println("The job was successfully submitted with id: " + id);

		} catch (JobStartException | JobSecurityException ex) {
			out.println("Error while trying to submit job!" + ex.getMessage());
			log.error("Error while trying to submit job!" + ex.getMessage());
			ex.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
