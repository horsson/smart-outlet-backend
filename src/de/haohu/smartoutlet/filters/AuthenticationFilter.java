package de.haohu.smartoutlet.filters;

import de.haohu.smartoutlet.manager.LoginManager;
import de.haohu.smartoutlet.model.AccessToken;
import de.haohu.smartoutlet.model.ResponseJsonData;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/api/*"})
public class AuthenticationFilter implements Filter {
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
       
        AccessToken accessToken = AccessToken.createToken(request);
        if (LoginManager.getInstance().isValidAccessToken(accessToken)){
            chain.doFilter(req,resp);
        } else {
            response.setContentType("application/json");
            ResponseJsonData errorResponse = new ResponseJsonData.Builder().statusCode(403).message("Unauthorized").build();
            String jsonString = errorResponse.toJson();
            response.setStatus(403);
            response.getWriter().println(jsonString);
            response.getWriter().flush();
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}