package alexander.ivanov.models.relational;

import org.postgresql.core.SqlCommand;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Storage {
    private static final String DB_URL = "jdbc:postgresql://appls-srv:5432/applications";
    private static final String DB_USER = "webapp";
    private static final String DB_USER_PASSWORD = "1111";
    private static final String DB_DRIVER = "org.postgresql.Driver";


    private static final Storage storage;
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    static {
        storage = new Storage();
    }

    /*public static Storage getInst() {
        return storage;
    }*/

    private static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(DB_DRIVER);
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_USER_PASSWORD);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                throw new IllegalStateException("Не удалось подключиться к Базе данных" + "\n" + e.getMessage());
            }
        }
        return connection;
    }

    private static Statement getStatement() {
        if (statement == null) {
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IllegalStateException("Не удалось получить оператоор" + "\n" + e.getMessage());
            }
        }
        return statement;
    }

    private static PreparedStatement getPreparedStatement(String sql) {
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Не удалось получить оператоор" + "\n" + e.getMessage());
        }
        return preparedStatement;
    }

    private static ResultSet executeQuery(String sql) {
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Не удалось выполнить запрос" + "\n" + e.getMessage());
        }
        return resultSet;
    }

    public static int executeUpdate(String sql) {
        int res = 0;
        try {
            getConnection();
            getStatement();
            res = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Не удалось выполнить запрос" + "\n" + e.getMessage());
        }
        return res;
    }

    public static int executeUpdate(String sql, Object[] args) {
        int res = 0;
        StringBuilder msg = new StringBuilder(sql);
        try {
            getConnection();
            getPreparedStatement(sql);

            for(int i=0; i < args.length; i++) {
                msg.append(" args[" + (i+1) + "]=" + args);
                preparedStatement.setObject(i+1, (String)args[i]);
            }
            Logger.getGlobal().warning(msg.toString());

            res = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Не удалось выполнить запрос" + "\n" + e.getMessage()
                    + "\nmsg=" + msg);
        }
        return res;
    }

    public static void execute(String sql) {
        try {
            getConnection();
            getStatement();
            executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Ошибка при обращении к базе данных. Обратитесь к администратору." + e.fillInStackTrace());
        } /*finally {
            return getInst();
        }*/
    }

    public static void execute(String sql, Object[] args) {
        StringBuilder msg = new StringBuilder(sql);
        try {
            getConnection();
            getStatement();

            getPreparedStatement(sql);

            /*for (Object o: args) {
                msg.append("\n" + o.toString());
            }*/

            for(int i=0; i < args.length; i++) {
               msg.append(" args[" + (i+1) + "]=" + args);
               preparedStatement.setObject(i+1, (String)args[i]);
            }
            Logger.getGlobal().warning(msg.toString());

            resultSet = preparedStatement.executeQuery();
            /*executeQuery(sql);*/
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Ошибка при обращении к базе данных. Обратитесь к администратору."
                    + e.fillInStackTrace()
                    + "\nmsg=" + msg);
        } /*finally {
            return getInst();
        }*/
    }

    public static ResultSet getResultSet() {
        return resultSet;
    }
}
