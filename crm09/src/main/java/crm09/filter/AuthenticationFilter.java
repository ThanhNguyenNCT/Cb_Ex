package crm09.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName ="authentication", urlPatterns = {"/user-add", "/user-table", "/role-add", "/role-table", "/groupwork", "/groupwork-add",
		"/task", "/task-add"}) //kích hoạt khi người dùng gọi đường dẫn url
public class AuthenticationFilter extends HttpFilter{
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String servletPath = req.getServletPath();
		Cookie[] cookies = req.getCookies();
		if(cookies == null || cookies.length <=1) {
			res.sendRedirect("index.jsp?unauthorized=true&login=false");
			System.out.println("AuthenticationFilter: No cookies found, redirecting to index.jsp");
			return;
		}
		String roleName = "";
		for(Cookie c : cookies) {
			String name = c.getName();
			String value = c.getValue();
			if(name.equals("sUserRole")) {
				roleName = value;
				break;
			}
		}
		
		boolean isAllowed = false;
		
		switch(roleName) {
			case "ROLE_ADMIN":
				System.out.println("AuthenticationFilter: User has ROLE_ADMIN");
				isAllowed = true; 
				break;
			case "ROLE_LEAD":
				System.out.println("AuthenticationFilter: User has ROLE_LEAD");
				if(servletPath.equals("/user-table") || servletPath.equals("/groupwork") || servletPath.equals("/task") 
						|| servletPath.equals("/task-add")) {
					isAllowed = true; 
				} 
				break;
			case "ROLE_USER":
				System.out.println("AuthenticationFilter: User has ROLE_USER");
				if(servletPath.equals("/task")) {
					isAllowed = true; 
				}
				break;
			default:
				System.out.println("AuthenticationFilter: User has unknown role: " + roleName);
				break;
		}
		
		if(isAllowed) {
			chain.doFilter(req, res); // tiếp tục chuỗi lọc
			System.out.println("AuthenticationFilter: User is authenticated with role " + roleName);
		} else {
			res.sendRedirect("index.jsp?unauthorized=true");
			System.out.println("AuthenticationFilter: User is not authenticated, redirecting to index.jsp");
		}

	}
}
