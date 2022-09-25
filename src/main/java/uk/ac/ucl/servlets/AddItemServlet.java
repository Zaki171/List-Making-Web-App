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
import java.io.*;
import java.nio.file.*;



@WebServlet("/additem.html")
@MultipartConfig
public class AddItemServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        IModel model = ModelFactory.getModel();
        if(model.addItem(request.getParameter("id"),request.getParameter("description"),request.getParameter("url"),fileName,request.getParameter("listToLink"))){
            if(!(fileName.equals(""))){
                File file = new File("src/main/webapp/files",fileName);//We store the image/audio file here
                if(!file.exists()){
                    InputStream fileContent = filePart.getInputStream();
                    Files.copy(fileContent,file.toPath());
                }
            }
            request.setAttribute("message","Item added!");
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/success.jsp");
            dispatch.forward(request, response);

        }
        else {
            request.setAttribute("message", "Could not add item!");
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/error.jsp");
            dispatch.forward(request, response);
        }

    }



}
