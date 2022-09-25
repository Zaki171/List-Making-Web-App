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

@WebServlet("/renameList.html")
public class RenameListServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        IModel model = ModelFactory.getModel();
        if(model.renameList(request.getParameter("id"),request.getParameter("newName"))){
            request.setAttribute("message","List renamed!");
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/success.jsp");
            dispatch.forward(request, response);
        }
        else{
            request.setAttribute("message","List already exists!");//Cannot have two lists with the same name
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/error.jsp");
            dispatch.forward(request, response);
        }
    }

}
