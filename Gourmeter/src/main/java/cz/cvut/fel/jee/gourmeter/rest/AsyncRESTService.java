package cz.cvut.fel.jee.gourmeter.rest;

import javax.annotation.security.PermitAll;
import javax.ejb.Asynchronous;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;


@Path("")
@ApplicationScoped
public class AsyncRESTService {
    
	//private static final BlockingQueue<AsyncResponse> suspended = new ArrayBlockingQueue<AsyncResponse>(5);
 
    @Inject @Any
    Event<AsyncRequest> asyncListeners;
    
    /**
     * Asynchronous method will immediately return future object. After that the AsyncResponse is suspended and current thread is available for other request processing. 
     * 
     * @param ar
     */
    @Path("/topN/{n}")
    @Asynchronous
    @GET
    @PermitAll
    public void async(@Suspended AsyncResponse ar, @PathParam("n") int n){
    	
    	asyncListeners.fire(new AsyncRequest(ar, n));
    }
    
//    @GET
//    @PermitAll
//    public void readMessage(@Suspended AsyncResponse asyncResponse) throws InterruptedException {
//    	asyncResponse.setTimeoutHandler(new TimeoutHandler() {
//    		 
//            @Override
//            public void handleTimeout(AsyncResponse asyncResponse) {
//                asyncResponse.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE)
//                        .entity("Operation time out.").build());
//            }
//        });
//        asyncResponse.setTimeout(10, TimeUnit.SECONDS);
//        
//        suspended.put(asyncResponse);
//        
//    }
//
//    @POST
//    @PermitAll
//    public String postMessage(final String message) throws InterruptedException {
//        final AsyncResponse ar = suspended.take();
//        ar.resume(message); // resumes the processing of one GET request
//        return "Message sent";
//    }
}