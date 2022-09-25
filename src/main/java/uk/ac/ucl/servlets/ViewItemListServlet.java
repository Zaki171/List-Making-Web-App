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


@WebServlet("/viewItemListInfo.html")
public class ViewItemListServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        IModel model = ModelFactory.getModel();
        ArrayList<Item> items = model.getItemsInList(request.getParameter("id"));
        request.setAttribute("items", items);
        request.setAttribute("id",request.getParameter("id"));
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/itemsDisplayAndAdd.jsp");
        dispatch.forward(request, response);
    }
}