import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class student extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String FirstName = request.getParameter("FirstName");
        String MiddleName  = request.getParameter("MiddleName");
        String LastName = request.getParameter("LastName");
        String Address = request.getParameter("Address");
        String City = request.getParameter("City");
        String State = request.getParameter("State");
        String AdharNumber = request.getParameter("AdharNumber");
        String EmailId = request.getParameter("EmailId");
        String Major = request.getParameter("Major");
        String Minors[] = request.getParameterValues("Minor");
        String Minor="";

        int Date = Integer.parseInt (request.getParameter ("Date"));
        int Month = Integer.parseInt (request.getParameter ("Month"));
        int Year = Integer.parseInt (request.getParameter ("Year"));
        int PinCode = Integer.parseInt (request.getParameter ("PinCode"));
        String StudentMobileNumber = request.getParameter ("StudentMobileNumber");
        String FatherMobileNumber = request.getParameter ("FatherMobileNumber");
        String MotherMobileNumber = request.getParameter ("MotherMobileNumber");

        for (int i=0;i<Minors.length;i++)
        {
            Minor+=Minors[i]+" ";
        }

        try{
        
        //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");

	//creating connection with the database 
          Connection  con=DriverManager.getConnection
                     ("jdbc:mysql://localhost/student","root","admin123");

        PreparedStatement ps=con.prepareStatement
                  ("insert into student values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        ps.setString(1,FirstName);
        ps.setString(2,MiddleName);
        ps.setString(3,LastName);
        ps.setInt(4,Date);
        ps.setInt(5,Month);
        ps.setInt(6,Year);
        ps.setString(7,Address);
        ps.setString(8,City);
        ps.setString(9,State);
        ps.setInt(10,PinCode);
        ps.setString(11,AdharNumber);
        ps.setString(12,EmailId);
        ps.setString(13,StudentMobileNumber);
        ps.setString(14,FatherMobileNumber);
        ps.setString(15,MotherMobileNumber);
        ps.setString(16,Major);
        //ps.setArray(17,con.createArrayOf("Varchar",(Minor value).toArray()));
        ps.setString(17,Minor);
            

        


        int i=ps.executeUpdate();
        
          if(i>0)
          {
            out.println("You are sucessfully registered");
          }
        
        }
        catch(Exception se)
        {
            se.printStackTrace();
        }
	
      }

public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
response.setContentType("text/html");  
PrintWriter out = response.getWriter();  
          
String AdharNumber=request.getParameter("AdharNumber");  

try{  
//loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");

    //creating connection with the database 
          Connection  con=DriverManager.getConnection
                     ("jdbc:mysql://localhost/student","root","admin123");
PreparedStatement ps=con.prepareStatement("select * from student where AdharNumber=?");  
ps.setString(1,AdharNumber);  
              
out.print("<table width=50% border=1>");  
out.print("<caption>Result:</caption>");  
  
ResultSet rs=ps.executeQuery();  
              
/* Printing column names */  
ResultSetMetaData rsmd=rs.getMetaData();  
int total=rsmd.getColumnCount();  
out.print("<tr>");  
for(int i=1;i<=total;i++)  
{  
out.print("<th>"+rsmd.getColumnName(i)+"</th>");  
}  
  
out.print("</tr>");  
              
/* Printing result */  
  
while(rs.next())  
{  
out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getInt(4)+"</td><td>"+rs.getInt(5)+"</td><td>"+rs.getInt(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getInt(10)+"</td><td>"+rs.getString(11)+"</td><td>"+rs.getString(12)+"</td><td>"+rs.getString(13)+"</td><td>"+rs.getString(14)+"</td><td>"+rs.getString(15)+"</td><td>"+rs.getString(16)+"</td><td>"+rs.getString(17)+"</td></tr>");  
                  
}  
  
out.print("</table>");  
              
}catch (Exception e2) {e2.printStackTrace();}  
          
finally{out.close();}  
  
}
  }