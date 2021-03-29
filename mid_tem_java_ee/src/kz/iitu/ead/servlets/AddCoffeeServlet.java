package kz.iitu.ead.servlets;


import kz.iitu.ead.db.DBManager;
import kz.iitu.ead.entities.Coffee;
import kz.iitu.ead.entities.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addcoffee")
public class AddCoffeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Users currentUser = (Users)request.getSession().getAttribute("USER");

        if(currentUser!=null){

            request.setCharacterEncoding("utf8");

            String name = request.getParameter("name");
            String description = request.getParameter("description");
            int price = Integer.parseInt(request.getParameter("price"));

            Coffee hotel = new Coffee(null, name, currentUser, description,  price);
            if(DBManager.addHotel(hotel)){
                response.sendRedirect("/addcoffee?success");
            }else{
                response.sendRedirect("/addcoffee?error");
            }

        }else{
            response.sendRedirect("/login");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Users currentUser = (Users)request.getSession().getAttribute("USER");
        if(currentUser!=null){
            request.getRequestDispatcher("/addcoffee.jsp").forward(request, response);
        }else{
            response.sendRedirect("/login");
        }

    }
}
