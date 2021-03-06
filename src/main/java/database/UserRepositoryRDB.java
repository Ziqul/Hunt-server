package main.java.database;

import main.java.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserRepositoryRDB implements UserRepository{

    private static UserRepositoryRDB instance = new UserRepositoryRDB();

    private Connection connection;


    private UserRepositoryRDB(){
        connection = new ConnectionManager().getConnection();
    }

    public static UserRepositoryRDB getInstance(){
        return instance;
    }

    @Override
    public User update(User user) {
        final String update = "UPDATE user SET login=?,password=?,score=? WHERE id_user=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setInt(3,user.getScore());
            preparedStatement.setInt(4,user.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User removeUser(Integer id) {
        final String remove = "DELETE FROM user WHERE id_user=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(remove);
            preparedStatement.setInt(1,id);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User create(User user) throws AlreadyExistingException{
        final String insert = "INSERT INTO user(login,password,score) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setInt(3, user.getScore());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User get(String login, String password) {
        final String getUser =
                "SELECT user.id_user AS id,user.login AS login, user.password AS password, user.score AS score " +
                "FROM user WHERE user.login=? AND user.password=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getUser);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setScore(resultSet.getInt("score"));
                System.out.println(user);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAll() {
//        final String getUser =
//                "SELECT user.id_user AS id,user.login AS login, " +
//                "user.password AS password, user.score AS score " +
//                "FROM user ";
//        ArrayList<ArrayList<User>> list = new ArrayList<>();
//        int i = 0;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(getUser);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            User user=new User();
//            while (resultSet.next()){
//                user.setId(resultSet.getInt("id"));
//                user.setLogin(resultSet.getString("login"));
//                user.setPassword(resultSet.getString("password"));
//                user.setScore(resultSet.getInt("score"));
//                list.add(new ArrayList<User>());
//                list.get(i).add(user);
//                i++;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }
}
