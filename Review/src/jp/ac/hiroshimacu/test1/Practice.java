package jp.ac.hiroshimacu.test1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Practice
 */
@WebServlet("/Practice")
public class Practice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Practice() {
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
        
        String used_result = request.getParameter("used_result");
        String user_request = request.getParameter("request");
        String user_claim = request.getParameter("claim");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>投稿情報確認フォーム</title>");
        out.println("<script>");
        out.println("function returnpage(){"
        		+ "location.href='./Review/practice.html';}");
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");
        Connection conn = null;
        String url = "jdbc:postgresql://localhost/review";
        String user = "postgres";
        String password = "password";
        try{
        	Class.forName("org.postgresql.Driver").newInstance();
        	conn = DriverManager.getConnection(url,user,password);
        	String sql = "SELECT max(id) as maxid from user_review";
        	Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery(sql);
        	int max_id = 0;
        	while(rs.next()){
        		max_id = rs.getInt("maxid");
        	}
        	rs.close();
        	stmt.close();        	
        	sql = "insert into user_review(id,used_result,user_request,user_claim) values (?,?,?,?)";
        	PreparedStatement pstmt = conn.prepareStatement(sql);
        	pstmt.setInt(1, max_id+1);
        	pstmt.setString(2, used_result);
        	pstmt.setString(3, user_request);
        	pstmt.setString(4, user_claim);
        	int num = pstmt.executeUpdate();
            pstmt.close();
        }catch (ClassNotFoundException e){
            //out.println("ClassNotFoundException:" + e.getMessage());
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
        out.println("感想 : "+used_result+"<br>");
        out.println("要望 : "+user_request+"<br>");
        out.println("苦情 : "+user_claim+"<br>");
        out.println("を投稿しました。");
        out.println("<br/><br/>");
        out.println("<input type='button' value='前のページに戻る' name='Return' onclick='returnpage()'>");
        out.println("</body>");
        out.println("</html>");
	}

}
