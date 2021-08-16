package manager;


import db.DBConnectionProvider;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private Connection connection = DBConnectionProvider.getProvider().getConnection();

    public void addUser(User user) {
        try {
            String query = "INSERT INTO `user` (`name`,`surname`,`password`) " +
                    "VALUES(?,?,?);";
            PreparedStatement pStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, user.getName());
            pStatement.setString(2, user.getSurname());
            pStatement.setString(3, user.getPassword());
            System.out.println(query);
            pStatement.executeUpdate();
            ResultSet generatedKeys = pStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                user.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void updateUser(User user) {
//        try {
//            Statement statement = connection.createStatement();
//            String query = String.format("UPDATE user SET name = '%s', surname = '%s',password='%s' WHERE id=" + user.getId(),
//                    user.getName(), user.getSurname(), user.getPassword());
//            System.out.println(query);
//            statement.executeUpdate(query);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    public List<User> getAllUsers() {
//        String sql = "SELECT * from user";
//        List<User> users = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                User user = User.builder()
//                        .id(resultSet.getInt("id"))
//                        .name(resultSet.getString("name"))
//                        .surname(resultSet.getString("surname"))
//                        .password(resultSet.getString("password"))
//                        .build();
//                users.add(user);
//            }
//        } catch (SQLException e) {
//           e.printStackTrace();
//        }
//        return users;
//    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM user WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return User.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .password(resultSet.getString(4))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByNameAndPassword(String name, String password) {
        String sql = "SELECT * FROM user WHERE name ='" + name + "' and password = '" + password + "'";
        System.out.println(sql + " public User getUserByNameAndPassword(String name, String password)");
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
//            System.out.println("resolset-ic heto");
            if (resultSet.next()) {
                return User.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .password(resultSet.getString(4))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public void deleteUser(int id) {
//        String sql = "DELETE from user where id = " + id;
//        try {
//            Statement statement = connection.createStatement();
//            statement.executeUpdate(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

}
