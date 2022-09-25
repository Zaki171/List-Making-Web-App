package uk.ac.ucl.servlets;

import uk.ac.ucl.model.IModel;
import uk.ac.ucl.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/editItem.html")
@MultipartConfig
public class EditItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Part filePart = request.getPart("newImage");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        IModel model = ModelFactory.getModel();
        if(model.editItem(request.getParameter("id"),request.getParameter("text"),request.getParameter("url"),request.getParameter("image"),request.getParameter("linkToList"),request.getParameter("newText"),request.getParameter("newUrl"),fileName,request.getParameter("newLink"))){
            if(!(fileName.equals(""))){
                File file = new File("src/main/webapp/files",fileName);//We store the image/audio file here
                if(!file.exists()){
                    InputStream fileContent = filePart.getInputStream();
                    Files.copy(fileContent,file.toPath());
                }
            }
            request.setAttribute("message","Item edited!");
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/success.jsp");
            dispatch.forward(request, response);
        }
        else{
            request.setAttribute("message","Could not edit item!");
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/error.jsp");
            dispatch.forward(request, response);
        }
    }

}
