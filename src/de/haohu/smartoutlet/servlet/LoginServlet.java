package de.haohu.smartoutlet.servlet;

import com.google.gson.Gson;
import de.haohu.smartoutlet.manager.LoginManager;
import de.haohu.smartoutlet.model.AccessToken;
import de.haohu.smartoutlet.model.LoginEntry;
import de.haohu.smartoutlet.model.ResponseJsonData;

import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Revoke the access_token
		InputStreamReader isr = new InputStreamReader(request.getInputStream());
		Gson gson = new Gson();
		LoginEntry loginEntry = gson.fromJson(isr, LoginEntry.class);

		if ("horsson".equals(loginEntry.getUsername()) && "19820311".equals(loginEntry.getPassword())){
			LoginManager loginManager = LoginManager.getInstance();
			AccessToken accessToken = loginManager.addLoginEntry(loginEntry);
			Cookie cookie = new Cookie("access_token",accessToken.getAccessTokenValue());
			response.addCookie(cookie);
			response.setStatus(200);
			response.getWriter().println("OK");
		} else {
            ResponseJsonData errorResponse = new ResponseJsonData.Builder()
                    .statusCode(403).message("Wrong username or password").build();
			String respString = errorResponse.toJson();
			response.setContentType("application/json");
			response.setContentLength(respString.length());
			response.setStatus(403);
			response.getWriter().println(respString);
		}
	}

}
