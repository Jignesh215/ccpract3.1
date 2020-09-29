
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class insertData extends HttpServlet {

     Connection con;
     PreparedStatement pst;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
             Class.forName("com.mysql.jdbc.Driver");
             
             con = DriverManager.getConnection("jdbc:mysql://student.czwocs99fte6.us-east-1.rds.amazonaws.com:3306/student?useSSL=false","admin","admin123");
//             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc","root","");
             
  
            String Name = request.getParameter("name");
            String Password = request.getParameter("pwd");
            String Email = request.getParameter("email");
            String Gender = request.getParameter("gender");
            String Country = request.getParameter("country");
            
            System.out.println("----connected");
//            Statement stat = con.createStatement();
//            stat.executeUpdate("insert into test values (0)");
             pst = con.prepareStatement("insert into userdata (name,password,email,sex,country) values(?,?,?,?,?)");
            pst.setString(1, Name);
             pst.setString(2, Password);
             pst.setString(3, Email);
             pst.setString(4, Gender);
             pst.setString(5, Country);
             int i = pst.executeUpdate();
             
             if(i==1){
                 response.sendRedirect("DisplayData");
             }
             
             out.println("Record Inserted");
            con.close();
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
    }

}
