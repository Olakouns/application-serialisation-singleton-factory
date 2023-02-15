import dao.IDao;
import dao.ObjectFileUserDaoImpl;
import entities.User;
import exceptions.DAOException;

public class DemoObjectFileUser {
    public static void main(String[] args) {
        IDao<User> dao;
        dao = new ObjectFileUserDaoImpl();
        try {
            dao.create(new User(1, "miraide", "123"));
            System.out.println("La liste des utilisateurs: " +dao.list());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
