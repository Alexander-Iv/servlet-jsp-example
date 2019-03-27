package alexander.ivanov.models;

import alexander.ivanov.models.object.User;
import alexander.ivanov.models.relational.Storage;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserMapping implements Mapping<User> {
    private static List<User> users;

    @Override
    public List<User> findAll() {
        users = new ArrayList<>();
        Storage.execute("SELECT * FROM all_users");
        try {
            while (Storage.getResultSet().next()) {

                /*Logger.getGlobal().warning(
                        Storage.getResultSet().getInt("id") + " " +
                                Storage.getResultSet().getString("name") + " " +
                                Storage.getResultSet().getString("password"));*/

                users.add(new User(
                        Storage.getResultSet().getInt("id"),
                        Storage.getResultSet().getString("name"),
                        Storage.getResultSet().getString("password")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void add(User obj) {
        /*Storage.executeUpdate("INSERT INTO all_users(name, password) VALUES('"
                + obj.getName() +"', '"
                + BCrypt.hashpw(obj.getPassword(), BCrypt.gensalt()) +"')");*/
        Storage.executeUpdate("INSERT INTO all_users(name, password) VALUES(?,?)",
                new Object[] {obj.getName(), BCrypt.hashpw(obj.getPassword(), BCrypt.gensalt())});
    }

    @Override
    public List<User> get(User obj) {
        users = new ArrayList<>();
        if (isExist(obj)) {
            try {
                while (Storage.getResultSet().next()) {
                    users.add(new User(
                                Storage.getResultSet().getInt("id"),
                                Storage.getResultSet().getString("name"),
                                Storage.getResultSet().getString("password")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public void del(User obj) {

    }

    @Override
    public boolean isExist(User obj) {
        /*Storage.execute("SELECT * FROM all_users u WHERE"
                        *//*+ " u.id = " + obj.getId()*//*
                        + " upper(u.name) = " + "'" + obj.getName().toUpperCase() + "'");*/
        Storage.execute("SELECT * FROM all_users u WHERE upper(u.name) = ?"
                , new Object[] {obj.getName().toUpperCase()});
        try {
            while (Storage.getResultSet().next()) {
                /*Logger.getGlobal().warning(
                        Storage.getResultSet().getInt("id") + " " +
                                Storage.getResultSet().getString("name") + " " +
                                Storage.getResultSet().getString("password") + " " +
                                obj.getPassword() + " " +
                                BCrypt.hashpw(Storage.getResultSet().getString("password"), BCrypt.gensalt(12)) + " " +
                                BCrypt.gensalt(20) + " " +
                                Storage.getResultSet().getString("password")
                                );*/
                /*if (BCrypt.checkpw(
                        obj.getPassword(),
                        Storage.getResultSet().getString("password")))*/
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
