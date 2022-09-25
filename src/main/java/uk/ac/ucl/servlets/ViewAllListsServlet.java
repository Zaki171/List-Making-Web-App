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
import java.util.List;


@WebServlet("/allLists.html")
public class ViewAllListsServlet extends HttpServlet
{

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    IModel model = ModelFactory.getModel();
    List<String> labels = model.getListLabels();
    request.setAttribute("labels", labels);
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/itemLists.jsp");
    dispatch.forward(request, response);
  }
}
