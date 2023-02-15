import entities.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserialisationDemo {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("user.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            User user = (User) ois.readObject();
            System.out.println(user + " désérialisé avec succès ...");
            System.out.println("Login : " + user.getLogin());
            System.out.println("Password : " + user.getPassword());
            System.out.println("Identifiant : " + user.getId());
        } catch (IOException ioe) {
            System.out.println("An error occured : " + ioe.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("User class not found : " + e.getMessage());
        }
    }
}
