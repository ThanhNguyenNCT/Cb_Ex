package new_servlet09;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/submit")
public class SubmitController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		req.getRequestDispatcher("/Info.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		int count = Integer.parseInt(req.getParameter("count"));
		
		List<Students> students = new ArrayList<Students>();
		for (int i = 1; i <= count; i++) {
			String name = req.getParameter("name" + i);
			double score = Double.parseDouble(req.getParameter("score" + i));
			String major = req.getParameter("major" + i);
			students.add(new Students(name, score, major));
		}
		req.setAttribute("students", students);
		req.getRequestDispatcher("/Info.jsp").forward(req, resp);
	}
}	
