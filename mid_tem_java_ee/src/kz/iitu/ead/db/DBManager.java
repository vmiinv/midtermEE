package kz.iitu.ead.db;

import kz.iitu.ead.entities.Coffee;
import kz.iitu.ead.entities.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class    DBManager {

    private static Connection connection;

    static {
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zhanel_db?serverTimezone=UTC&useUnicode=true", "root", "");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean addUser(Users user){
        int rows = 0;
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO users (email, password, full_name, picture ) " +
                    "VALUES (?, ?, ?, ?)");

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setString(4, user.getPicture());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;
    }

    public static boolean updateUserPicture(Users user){
        int rows = 0;
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE users SET picture = ? WHERE id = ?");

            statement.setString(1, user.getPicture());
            statement.setLong(2, user.getId());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;
    }

    public static boolean updateUserProfile(Users user){
        int rows = 0;
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE users SET full_name = ? WHERE id = ?");

            statement.setString(1, user.getFullName());
            statement.setLong(2, user.getId());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;
    }

    public static boolean updateUserPassword(Users user){
        int rows = 0;
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE users SET password = ? WHERE id = ?");

            statement.setString(1, user.getPassword());
            statement.setLong(2, user.getId());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;
    }

    public static Users getUserByEmail(String email){

        Users user = null;

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                user = new Users(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("picture")
                );
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public static boolean addHotel(Coffee hotel){
        int rows = 0;
        try{

            PreparedStatement statement = connection.prepareStatement("INSERT INTO coffees (id, name, author_id,  description,  price) " +
                    "VALUES (NULL, ?, ?, ?, ?)");

            statement.setString(1, hotel.getName());
            statement.setLong(2, hotel.getAuthor().getId());
            statement.setString(3, hotel.getDescription());
            statement.setInt(4, hotel.getPrice());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;
    }

    public static ArrayList<Coffee> getAllHotels(){

        ArrayList<Coffee> coffees = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT h.id, h.name, h.description,  h.price, h.author_id, u.full_name, u.picture " +
                    "FROM coffees h " +
                    "INNER JOIN users u ON u.id = h.author_id " +
                    "ORDER BY h.price ASC");

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){

                coffees.add(
                        new Coffee(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                new Users(
                                        resultSet.getLong("author_id"),
                                        null, null,
                                        resultSet.getString("full_name"),
                                        resultSet.getString("picture")
                                ),
                                resultSet.getString("description"),
                                resultSet.getInt("price")
                        )
                );
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return coffees;
    }

    public static Coffee getCoffee(Long id){

        Coffee coffee = null;

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT h.id, h.name, h.description,  h.price,  h.author_id, u.full_name, u.picture " +
                    "FROM coffees h " +
                    "INNER JOIN users u ON u.id = h.author_id " +
                    "WHERE h.id = ? ");

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                coffee = new Coffee(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        new Users(
                                resultSet.getLong("author_id"),
                                null, null,
                                resultSet.getString("full_name"),
                                resultSet.getString("picture")
                        ),
                        resultSet.getString("description"),
                        resultSet.getInt("price")
                );
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return coffee;
    }

}
