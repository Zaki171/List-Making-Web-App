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

@WebServlet("/delItem.html")
public class DeleteItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        IModel model = ModelFactory.getModel();
        model.delItem(request.getParameter("id"),request.getParameter("text"),request.getParameter("url"),request.getParameter("image"),request.getParameter("linkToList"));
        request.setAttribute("message","Item deleted!");
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/success.jsp");
        dispatch.forward(request, response);
    }

}
