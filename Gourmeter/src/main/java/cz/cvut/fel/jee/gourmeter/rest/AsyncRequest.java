package cz.cvut.fel.jee.gourmeter.rest;

import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AsyncRequest {

    private AsyncResponse ar;
    private final int n; // number of top items to be selected from DB
    
	public AsyncRequest(AsyncResponse ar, int n) {
		this.ar = ar;
		this.n = n;
	}
	
    public void sendMessage(Object result) {
        Response response = Response.ok(result,MediaType.APPLICATION_JSON).build();
        ar.resume(response);
    }

    public void errorHappened(Exception ex) {
        ar.resume(ex);
    }
    
    public int getN() {
		return n;
	}
}
