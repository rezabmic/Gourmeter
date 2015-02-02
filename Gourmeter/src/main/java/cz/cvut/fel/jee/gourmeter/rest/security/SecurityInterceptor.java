package cz.cvut.fel.jee.gourmeter.rest.security;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.util.Base64;

import cz.cvut.fel.jee.gourmeter.ejb.UserSessionLocal;

@Provider
public class SecurityInterceptor implements ContainerRequestFilter {
	
	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME = "Basic";
	private static final String INVOKER_PROPERTY = "org.jboss.resteasy.core.ResourceMethodInvoker";
	private static final ServerResponse ACCESS_DENIED = new ServerResponse("Access denied for this resource", 401, new Headers<Object>());;
	private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("Nobody can access this resource", 403, new Headers<Object>());;
	private static final ServerResponse SERVER_ERROR = new ServerResponse("INTERNAL SERVER ERROR", 500, new Headers<Object>());;

	@Inject
	private UserSessionLocal userSession;
	
	@Override
	public void filter(ContainerRequestContext ctx) {
		ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) ctx.getProperty(INVOKER_PROPERTY);
		Method method = methodInvoker.getMethod();
		// Access allowed for all
		if (method.isAnnotationPresent(PermitAll.class)) {
			return;
		}
		// Access denied for all
		if (method.isAnnotationPresent(DenyAll.class)) {
			ctx.abortWith(ACCESS_FORBIDDEN);
			return;
		}

		// Get request headers
		final MultivaluedMap<String, String> headers = ctx.getHeaders();
		// Fetch authorization header
		final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

		// If no authorization information present; block access
		if (authorization == null || authorization.isEmpty()) {
			ctx.abortWith(ACCESS_DENIED);
			return;
		}

		// Get encoded username and password
		final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

		// Decode username and password
		String usernameAndPassword = null;
		try {
			usernameAndPassword = new String(Base64.decode(encodedUserPassword));
		} catch (IOException e) {
			ctx.abortWith(SERVER_ERROR);
			return;
		}

		// Split username and password tokens
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		// Verify user access
		if (method.isAnnotationPresent(RolesAllowed.class)) {
			RolesAllowed allowed = method.getAnnotation(RolesAllowed.class);
			Set<String> rolesAllowed = new HashSet<String>(Arrays.asList(allowed.value()));

			// Is user valid?
			if (!isUserAllowed(username, password, rolesAllowed)) {
				ctx.abortWith(ACCESS_DENIED);
				return;
			}
		}
	}

	private boolean isUserAllowed(	final String username,
									final String password,
									final Set<String> rolesAllowed) {
		try {
			userSession.authenticateUser(username, password, rolesAllowed);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}