package uk.ac.ucl.servlets;
import uk.ac.ucl.model.IModel;
import uk.ac.ucl.model.ModelFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/runAdd.html")
public class AddListServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        IModel model = ModelFactory.getModel();
        if(model.addList(request.getParameter("label"))){
            request.setAttribute("message","List added!");
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/success.jsp");
            dispatch.forward(request, response);
        }
        else{
            request.setAttribute("message","List already exists!");
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/error.jsp");
            dispatch.forward(request, response);
        }
    }

}
