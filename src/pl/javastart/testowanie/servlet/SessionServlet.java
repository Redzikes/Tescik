package pl.javastart.testowanie.servlet;

import com.sun.deploy.net.HttpRequest;
import pl.javastart.testowanie.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(true);

        User user=(User) session.getAttribute("user");
        if(user == null || (user.getFirstname() == null || user.getLastname()== null))
        {
            user= createUser(request);
            session.setAttribute("user",user);
        }
        sendResponse(response, user);

    }

    public User createUser(HttpServletRequest request)
    {
        User user=new User();
        user.setFirstname(request.getParameter("firstname"));
        user.setLastname(request.getParameter("lastname"));
        return user;
    }

    public void sendResponse(HttpServletResponse respone,User user) throws IOException
    {
        respone.setContentType("text/html");
        respone.setCharacterEncoding("UTF-8");
        PrintWriter write=respone.getWriter();

        write.println("<html>");
        write.println("<body>");
        if(user.getFirstname()==null || user.getLastname()==null)
        {
            write.println("<div>Witaj nieznany</div>");
        }
        else
        {
            write.println("<div>Witaj"+user.getFirstname()+" "+user.getLastname()+"</div>");
        }

        write.println("</body>");
        write.println("</html>");
    }

}
