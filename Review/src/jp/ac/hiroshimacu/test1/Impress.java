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
 * Servlet implementation class Impress
 */
@WebServlet("/Impress")
public class Impress extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Impress() {
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
		

	        out.println("<html>");
	        out.println("<head>");
	        out.println("<title>Immpress Only</title>");
	        out.println("<script>");
	        out.println("function returnpage(){"
	        		+ "location.href='../Review/Login';}");
	        out.println("</script>");
	        out.println("</head>");
	        out.println("<body>");
	        Connection conn = null;
	        String url = "jdbc:postgresql://localhost/review";
	        String user = "postgres";
	        String password = "password";

    		int cnt_result=0;

	        try{
	        	Class.forName("org.postgresql.Driver").newInstance();
	        	conn = DriverManager.getConnection(url,user,password);
	        	String sql = "select id,used_result from user_review";
	        	Statement stmt = conn.createStatement();
	        	ResultSet rs = stmt.executeQuery(sql);



	        	while(rs.next()){
	        		int code = rs.getInt("id");
	        		String used_result = rs.getString("used_result");
	        		if(!used_result.equals("")){
	        			cnt_result++;
	        		}
	        		out.println("<p>");
	        		if(!used_result.equals("")){
	        			out.println("ユーザーid : "+code+"<br>");
	        			out.println("感想 : "+used_result+"<br>");
	        		}
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
	        out.println("感想 : " + cnt_result + "件<br>");   
	        out.println("<input type='button' value='前のページに戻る' name='Return' onclick='returnpage()'>");
	        out.println("</body>");
	        out.println("</html>");
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
