import java.sql.*;

public class Sys2DBv1 {
    private static final String DB_URL = "jdbc:postgresql://your-rds-endpoint:5432/your-database";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "0000";

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java -jar sys2db.jar [command] [arguments]");
            return;
        }

        String command = args[0];

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            switch (command) {
                case "newconnection":
                    if (args.length < 2) {
                        System.out.println("Missing argument: firstname");
                        break;
                    }
                    String firstname = args[1];
                    insertNewConnection(connection, firstname);
                    break;
                case "readconnections":
                    int limit = args.length >= 2 ? Integer.parseInt(args[1]) : 5;
                    readConnections(connection, limit);
                    break;
                default:
                    System.out.println("Unknown command: " + command);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    private static void insertNewConnection(Connection connection, String firstname) {
        try {
            String query = "INSERT INTO connection (id, firstname, connection_datetime) VALUES (?, ?, CURRENT_TIMESTAMP)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, java.util.UUID.randomUUID());
            statement.setString(2, firstname);
            statement.executeUpdate();
            System.out.println("New connection inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting new connection: " + e.getMessage());
        }
    }

    private static void readConnections(Connection connection, int limit) {
        try {
            String query = "SELECT * FROM connection ORDER BY connection_datetime DESC LIMIT ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("Last " + limit + " connections:");
            while (resultSet.next()) {
                String id = resultSet.getObject("id").toString();
                String firstname = resultSet.getString("firstname");
                Timestamp connectionDateTime = resultSet.getTimestamp("connection_datetime");
                System.out.println("ID: " + id + ", Firstname: " + firstname + ", Connection DateTime: " + connectionDateTime);
            }
        } catch (SQLException e) {
            System.out.println("Error reading connections: " + e.getMessage());
        }
    }
}
