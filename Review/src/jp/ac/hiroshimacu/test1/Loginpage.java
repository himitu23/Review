package jp.ac.hiroshimacu.test1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Loginpage
 */
@WebServlet("/Loginpage")
public class Loginpage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Loginpage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");

        out.println("<html>");
        out.println("<head>");
        out.println("<title>ログインページ</title>");
        out.println("<script>");
        out.println("function returnpage(){"
        		+ "location.href='http://localhost:8080/Review/practice.html';}");
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form name='login' action='Login'>");
        out.println("ユーザー名 : <input type='text' value='' name='username'><br>");
        out.println("パスワード  : <input type='password' name='userpassword'><br>");
        out.println("<input type='submit' value='認証' name='login'>");
        out.println("</form>");
        out.println("<input type='button' value='前のページに戻る' name='Return' onclick='returnpage()'>");
        out.println("</body>");
        out.println("</html>");

	}

}
