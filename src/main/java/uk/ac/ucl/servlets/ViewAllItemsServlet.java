package uk.ac.ucl.servlets;

import uk.ac.ucl.dataStructure.Item;
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
import java.util.ArrayList;

@WebServlet("/viewAllItems.html")
public class ViewAllItemsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        IModel model = ModelFactory.getModel();
        ArrayList<Item> allItems = model.getAllItems();
        request.setAttribute("items",allItems);
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/allItems.jsp");
        dispatch.forward(request, response);
    }

}
