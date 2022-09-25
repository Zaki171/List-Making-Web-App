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

@WebServlet("/searchItem.html")
public class SearchForItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        IModel model = ModelFactory.getModel();
        ArrayList<Item> results = model.searchForItem(request.getParameter("id"),request.getParameter("item"));
        request.setAttribute("items",results);
        request.setAttribute("id",request.getParameter("id"));
        request.setAttribute("search",true);//we use itemsDisplayAndAdd JSP for displaying items for the full list and when searching for an item
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/itemsDisplayAndAdd.jsp");
        dispatch.forward(request, response);
    }
}
