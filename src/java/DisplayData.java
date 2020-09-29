import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class DisplayData extends HttpServlet {

     Connection con;
     PreparedStatement pst;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://student.czwocs99fte6.us-east-1.rds.amazonaws.com:3306/student?useSSL=false","admin","admin123");
  
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>"); 
            out.println("<title>Data</title>"); 
            out.println("</head>");
            out.println("<body bgcolor=' WhiteSmoke'>");
            out.println("<center><h1>User Data</h1><center>");
            out.println("<center><table border=1><thead>");
    
             out.println("</tr><th>Name</th><th>Password</th><th>Email</th><th>Gender</th><th>Country</th></tr>");
             
             Statement st = con.createStatement();
              ResultSet rs = st.executeQuery("select * from userdata");
             
              while(rs.next()){
             
              out.print(""
                        + "</tr><td height='50px' width='30%' align='center'>"+rs.getString("name")+"</td><td height='50px' width='80px' align='center'>"+rs.getString("password")+"</td><td height='50px' width='80px' align='center'>"+rs.getString("email")+"</td><td height='50px' width='80px' align='center'>"+rs.getString("sex")+"</td><td height='50px' width='80px' align='center'>"+rs.getString("country")+"</td></tr>");
                   
              }
              out.println("</thead></table></center>");
              
              out.print("<br><a href='index.html'>Back To Register</a>");
          
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e){ System.out.println(e);}  
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
