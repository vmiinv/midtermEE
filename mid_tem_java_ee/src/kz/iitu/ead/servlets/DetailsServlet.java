package kz.iitu.ead.servlets;



import kz.iitu.ead.db.DBManager;
import kz.iitu.ead.entities.Coffee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/details")
public class DetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Coffee coffee = null;
        try{

            Long id = Long.parseLong(request.getParameter("id"));
            coffee = DBManager.getCoffee(id);

        }catch (Exception e){

        }

        if(coffee!=null){
            request.setAttribute("coffee", coffee);
            request.getRequestDispatcher("/details.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }
}
