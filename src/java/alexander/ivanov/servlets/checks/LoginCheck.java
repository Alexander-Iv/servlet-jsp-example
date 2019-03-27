package alexander.ivanov.servlets.checks;

import alexander.ivanov.models.UserMapping;
import alexander.ivanov.models.object.User;

import java.util.ArrayList;

public class LoginCheck {
    private ArrayList<String> errMsgs = new ArrayList<>();
    private String errors = "";

    public ArrayList<String> checkAttributes(String user, String password) {
        if(user == null || user.isEmpty()) errMsgs.add("User can't be empty!");
        if(password == null || password.isEmpty()) errMsgs.add("Password can't be empty!");

        /*Logger.getGlobal().warning("password = " + password
                + " confirm = " + confirm
                + " password.equals(confirm) = " + password.equals(confirm)
                + " password.compareTo(confirm) = " + password.compareTo(confirm)*//*
        );*/

        if (errMsgs.isEmpty()) {
            if (!new UserMapping().isExist(new User(user, password))) errMsgs.add("User does not exist!");
        }

        return errMsgs;
    }

    public String arrayToString(String separator) {
        for (String str: errMsgs) {
            errors += str + separator;
        }
        return errors;
    }

    public boolean hasErrors() {
        if (errMsgs == null || errMsgs.isEmpty()) return false;
        return true;
    }
}
