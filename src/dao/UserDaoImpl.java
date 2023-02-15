package dao;

import config.DBManager;
import entities.User;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IDao<User> {

    @Override
    public void create(User entity) throws DAOException {
        try (Connection connection = DBManager.getConnection()) {
            String query = "Insert into T_Users (login, password) values (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, entity.getLogin());
            ps.setString(2, entity.getPassword());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public User read(int id) throws DAOException {
        try (Connection connection = DBManager.getConnection()) {
            String query = "Select * From T_Users where id=?";
            PreparedStatement ps = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                String login = rs.getString("login");
                String password = rs.getString("password");
                return new User(id, login, password);
            }
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> list() throws DAOException {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBManager.getConnection()) {
            String query = "Select * From T_Users";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int identifiant = rs.getInt("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                User user = new User (identifiant, login, password);
                users.add(user);
            }
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        return users;
    }

    @Override
    public void update(User entity) throws DAOException {
        try (Connection connection = DBManager.getConnection()) {
            String query = "Update T_Users Set login=?, password=? Where id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, entity.getLogin());
            ps.setString(2, entity.getPassword());
            ps.setInt(3, entity.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try (Connection connection = DBManager.getConnection()) {
            String query = "Delete From T_Users Where id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
    }
}
