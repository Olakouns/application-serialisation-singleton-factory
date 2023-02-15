package entities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Users implements Serializable {
    private static final long serialVersionUID = 4684699198488893428L;
    private List<User> users;

    public Users() {
        users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void serialiserUser (String userFilename, Users users) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(userFilename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(users);
        }
    }

    public Users deserialiserUsers (String userFilename) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(userFilename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Users) ois.readObject();
        }
    }
}
