package manager;


import db.DBConnectionProvider;
import model.News;
import model.NewsCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsManager {

    private Connection connection = DBConnectionProvider.getProvider().getConnection();
    UserManager userManager = new UserManager();


    public void addNews(News news) {
        System.out.println("News manager class+AddNews method");
        try {
            String query = "INSERT INTO `news` (`category`,`title`,`description`,`text`,`pic_url`,`user_id`) " +
                    "VALUES(?,?,?,?,?,?);";
            PreparedStatement pStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            System.out.println("News manager class+AddNews method+PreparedStatement pStatement = " +
                    "connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);");
            pStatement.setString(1, news.getNewsCategory().name());
            pStatement.setString(2, news.getTitle());
            pStatement.setString(3, news.getDescription());
            pStatement.setString(4, news.getText());
            pStatement.setString(5, news.getPicUrl());
            pStatement.setInt(6, news.getUser().getId());

//            System.out.println("Add News");
//            System.out.println(query);
            pStatement.executeUpdate();
            ResultSet resultSet = pStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                news.setId(id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateNews(News news) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE news SET title = '%s', description = '%s', text='%s',pic_url='%s' WHERE id=" + news.getId(),
                    news.getTitle(), news.getDescription(), news.getText(), news.getPicUrl());
            System.out.println(query);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<News> getNews() {
        String sql = "SELECT * from news";
        List<News> newsList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                News news = News.builder()
                        .id(resultSet.getInt(1))         // 1
//                        .newsCategory(NewsCategory.valueOf(resultSet.getString("category")))  //2
                        .title(resultSet.getString(3))    //3
                        .description(resultSet.getString(4))   //5
                        .text(resultSet.getString(5))   //+++6
                        .picUrl(resultSet.getString(6))   //+++6
                        .userId(resultSet.getInt(7))   //+++6
//                        .user(userManager.getUserById(resultSet.getInt(8)))
                        .build();
                System.out.println("News manager class+ GetNews Method");
//                news.setUser(userManager.getUserById(news.getUserId()));  // ????
                newsList.add(news);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return newsList;
    }

//    public List<News> getAllNewsByUserId(int userId) throws SQLException{
//        String sql = "SELECT * from news where user_id=?";  ///???? 30-rd rope
//
//        PreparedStatement pStatement =connection.prepareStatement(sql);
//        pStatement.setInt(1,userId);
//        ResultSet resultSet = pStatement.executeQuery(); //////sql????
//
//        List<News> newsList = new ArrayList<>();
////        try {
////            Statement statement = connection.createStatement();
////            ResultSet resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                News news = News.builder()
//                        .id(resultSet.getInt("id"))         // 1
////                        .newsCategory(NewsCategory.valueOf(resultSet.getString("category")))  //2
//                        .title(resultSet.getString("title"))    //3
//                        //4 email
//                        .description(resultSet.getString("description"))   //5
//                        .text(resultSet.getString("text"))   //+++6
//                        .picUrl(resultSet.getString("picture"))   //+++6
//                        .userId(resultSet.getInt("user_id"))   //+++6
//                        .build();
//                news.setUser(userManager.getUserById(news.getUserId()));  // ????
//                newsList.add(news);
//            }
////        } catch (SQLException throwables) {
////            throwables.printStackTrace();
////        }
//        return newsList;
//    }

    public News getNewsById(int id) {
        String sql = "SELECT * FROM news WHERE id=" + id;
        System.out.println(sql);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return News.builder()
                        .id(resultSet.getInt(1))
//                        .newsCategory(NewsCategory.valueOf(resultSet.getString(2)))
                        .title(resultSet.getString(3))
                        .description(resultSet.getString(4))
                        .text(resultSet.getString(5))
                        .picUrl(resultSet.getString(6))
                        .user(userManager.getUserById(resultSet.getInt(7)))
                        .build();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    //    public List<News> getNewsByCategory(String category) {
//        System.out.println("News MANAGER GETNEWSBYCATEGORY "+category);
////        String sql = "SELECT * FROM news WHERE category=" + category;
//        String sql = "SELECT * FROM news WHERE category IN('"+category+"') ";
//        System.out.println(sql);
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
//            if (resultSet.next()) {
//                List<News> newsList = new ArrayList<>();
//                News news=News .builder()
//                        .id(resultSet.getInt(1))
//                        .newsCategory(NewsCategory.valueOf(resultSet.getString(2)))
//                        .title(resultSet.getString(3))
//                        .description(resultSet.getString(4))
//                        .text(resultSet.getString(5))
//                        .picUrl(resultSet.getString(6))
//                        .build();
//                newsList.add(news);
//                return newsList;
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return null;
//    }
    public List<News> getNewsByCategory(String category) {
        System.out.println("News MANAGER GETNEWSBYCATEGORY " + category);
        String sql = "SELECT * FROM news WHERE category IN('" + category + "') ";
        System.out.println(sql);
        List<News> newsList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                News news = News.builder()
                        .id(resultSet.getInt(1))
                        .newsCategory(NewsCategory.valueOf(resultSet.getString(2)))
                        .title(resultSet.getString(3))
                        .description(resultSet.getString(4))
                        .text(resultSet.getString(5))
                        .picUrl(resultSet.getString(6))
//                        .userId(resultSet.getInt(7))
                        .build();
                newsList.add(news);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return newsList;
    }

    public void deleteNews(int id) {
        String sql = "DELETE from news where id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getCategoryByNewsId(int id) throws SQLException {
        String categoryEnumNews;
        String sql = "SELECT * FROM news WHERE id=" + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println(sql);
//        List<News> newsArrayList = new ArrayList<>();
        News news = new News();
        while (resultSet.next()) {

            news.setId(resultSet.getInt(1));
            news.setNewsCategory(NewsCategory.valueOf(resultSet.getString(2)));
            news.setTitle(resultSet.getString(3));
            news.setDescription(resultSet.getString(4));
            news.setText(resultSet.getString(5));
            news.setPicUrl(resultSet.getString(6));
            news.setUserId(resultSet.getInt(7));
        }
        categoryEnumNews = news.getNewsCategory().name();
        System.out.println("NEWS MANAGER Stringov ENUM-Y = " + categoryEnumNews);
        return categoryEnumNews;
    }

}
