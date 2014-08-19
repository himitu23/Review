package jp.ac.hiroshimacu.test1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");

        Cookie cookie[] = request.getCookies();
        if(cookie != null){
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<title>ʅ（‾◡◝）ʃ</title>");
	        out.println("<script>");
	        out.println("function returnpage(){"
	        		+ "location.href='../Review/practice.html';}");
	        out.println("</script>");
	        out.println("</head>");
	        out.println("<body>");
	        Connection conn = null;
	        String url = "jdbc:postgresql://localhost/review";
	        String user = "postgres";
	        String password = "password";
    		int cnt_claim=0;
    		int cnt_result=0;
    		int cnt_request=0;
	        try{
	        	Class.forName("org.postgresql.Driver").newInstance();
	        	conn = DriverManager.getConnection(url,user,password);
	        	String sql = "select * from user_review";
	        	Statement stmt = conn.createStatement();
	        	ResultSet rs = stmt.executeQuery(sql);

	           	out.println("<form action = 'Impress' method = 'get'>");
	        	out.println("<input type='Submit' value='感想' >");
	        	out.println("</form>");

	        	while(rs.next()){
	        		int code = rs.getInt("id");
	        		String used_result = rs.getString("used_result");
	        		String user_request = rs.getString("user_request");
	        		String user_claim = rs.getString("user_claim");
	        		if(!user_claim.equals("")){
	        			cnt_claim++;
	        		}
	        		if(!user_request.equals("")){
	        			cnt_request++;
	        		}
	        		if(!used_result.equals("")){
	        			cnt_result++;
	        		}
	        		out.println("<p>");
	        		out.println("ユーザーid : "+code+"<br>");
	        		out.println("感想 : "+used_result+"<br>");
	        		out.println("要望 : "+user_request+"<br>");
	        		out.println("苦情 : "+user_claim+"<br>");
	        		out.println("</p>");
	        	}
	        	rs.close();
	        	stmt.close();
	        }catch (ClassNotFoundException e){
	            out.println("ClassNotFoundException:" + e.getMessage());
	        }catch (SQLException e){
	            out.println("SQLException:" + e.getMessage());
	        }catch (Exception e){
	            out.println("Exception:" + e.getMessage());
	        }finally{
	            try{
	                if (conn != null){
	                    conn.close();
	                }
	            }catch (SQLException e){
	                out.println("SQLException:" + e.getMessage());
	            }
	        }  
	        out.println("感想 : "+cnt_result+"件<br>要望 : "+cnt_request+"件<br>苦情 : "+cnt_claim+"件<br>");   
	        out.println("<input type='button' value='前のページに戻る' name='Return' onclick='returnpage()'>");
	        out.println("</body>");
	        out.println("</html>");
        }
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        String pass_name = "user";
        String pass_password = "password";
        String user_n = request.getParameter("username");
        String user_p = request.getParameter("userpassword");
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>ʅ（‾◡◝）ʃ < ユーザーレビュー</title>");
        out.println("<script>");
        out.println("function returnpage(){"
        		+ "location.href='../Review/Loginpage';}");
        out.println("function returnhome(){"
        		+ "location.href='../Review/practice.html';}");
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");
        if(pass_name.equals(user_n) && pass_password.equals(user_p)){
        	Cookie cookie_name = new Cookie("name",user_n);
        	Cookie cookie_pass = new Cookie("pass",user_p);
        	response.addCookie(cookie_name);
        	response.addCookie(cookie_pass);
	        
        	out.println("<form action = 'Impress' method = 'get'>");
        	out.println("<input type='Submit' value='感想' >");
        	out.println("</form>");
        	
	        Connection conn = null;
	        String url = "jdbc:postgresql://localhost/review";
	        String user = "postgres";
	        String password = "password";
    		int cnt_claim=0;
    		int cnt_result=0;
    		int cnt_request=0;
	        try{
	        	Class.forName("org.postgresql.Driver").newInstance();
	        	conn = DriverManager.getConnection(url,user,password);
	        	String sql = "select * from user_review";
	        	Statement stmt = conn.createStatement();
	        	ResultSet rs = stmt.executeQuery(sql);
	        	while(rs.next()){
	        		int code = rs.getInt("id");
	        		String used_result = rs.getString("used_result");
	        		String user_request = rs.getString("user_request");
	        		String user_claim = rs.getString("user_claim");
	        		if(!user_claim.equals("")){
	        			cnt_claim++;
	        		}
	        		if(!user_request.equals("")){
	        			cnt_request++;
	        		}
	        		if(!used_result.equals("")){
	        			cnt_result++;
	        		}
	        		out.println("<p>");
	        		out.println("ユーザーid : "+code+"<br>");
	        		out.println("感想 : "+used_result+"<br>");
	        		out.println("要望 : "+user_request+"<br>");
	        		out.println("苦情 : "+user_claim+"<br>");
	        		out.println("</p>");
	        	}
	        	rs.close();
	        	stmt.close();
	        }catch (ClassNotFoundException e){
	            out.println("ClassNotFoundException:" + e.getMessage());
	        }catch (SQLException e){
	            out.println("SQLException:" + e.getMessage());
	        }catch (Exception e){
	            out.println("Exception:" + e.getMessage());
	        }finally{
	            try{
	                if (conn != null){
	                    conn.close();
	                }
	            }catch (SQLException e){
	                out.println("SQLException:" + e.getMessage());
	            }
	        } 
	        out.println("感想 : "+cnt_result+"件<br>要望 : "+cnt_request+"件<br>苦情 : "+cnt_claim+"件<br>");
	        out.println("<input type='button' value='Home画面に戻る' name='Return' onclick='returnhome()'>");
		}else{
			out.println("Can't connect");
	        out.println("<input type='button' value='前のページに戻る' name='Return' onclick='returnpage()'>");
		}
        out.println("</body>");
        out.println("</html>");
	}
}
