import dao.*;
import entities.User;
import exceptions.DAOException;

import java.util.List;

public class TestConnection {
    public static void main(String[] args) {



//        System.out.println(us);
//        userDao

        try {
            IDao<User> userDao =  DaoFactory.getUserInstance();

//            CREATE TWO USER
//            userDao.create(new User("olakouns", "mot2P@ss"));
//            userDao.create(new User("kounasso", "pass1234"));

//            GET USER LIST
            List<User> userList = userDao.list();

            userList.forEach(System.out::println);

//            if (userList.size() >= 1) {
////                GET ONE USER
//                User redUser = userDao.read(userList.get(0).getId());
//                System.out.println("-----READ USER------");
//                System.out.println(redUser);
//
////                UPDATE USER
//                redUser.setLogin("Bassithou");
//                userDao.update(redUser);
//
////                DELETE USER
////                userDao.delete(redUser.getId());
//            }
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }
}
