package dao;

import entities.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DaoFactory {

    private static String className;
    private static String packageName;

    private DaoFactory() throws Exception {
        super();
        Class<?> objectFileDaoImpl = null;
        config();
        objectFileDaoImpl = Class.forName(packageName + "." + className);
        iDao =  (IDao<User>) objectFileDaoImpl.getConstructor().newInstance();
    }

    private static IDao<User> iDao ;

    public static IDao<User> getUserInstance() throws Exception {
        if (iDao == null){
            new DaoFactory();
        }
        return iDao;
    }

    protected static void config() throws Exception {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("application.properties"));
            className = properties.getProperty("class_name");
            packageName = properties.getProperty("package_nane");
        } catch (IOException e) {
            throw new Exception("Error while loading application.properties file", e);
        }
    }

}
