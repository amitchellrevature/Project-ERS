
import java.sql.*;

public class TicketDAO {

    Connection connection = null;

    public TicketDAO() {
        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void request(int id, String type, double amount, String description) throws SQLException {
        String sql = "insert into ticket (EmpID, type, amount, description, time, status) values (?, ?, ?, ?, SYSDATE(), \"pending\")";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, type);
        preparedStatement.setDouble(3, amount);
        preparedStatement.setString(4, description);
        preparedStatement.executeUpdate();
    }

    public int login(String username, String password) throws SQLException {
    	String sql = "select * from employee where username = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
    
    public String historyEmployee(int id) throws SQLException {
    	String sql = "select * from ticket where EmpID = " + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        String tickets = "";
        while (resultSet.next()) {
                tickets += "<section class=\\\"container p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success\\\"><div>" +
                        "Ticket: " + resultSet.getInt(1) + "<br>" +
                        "Type: " + resultSet.getString(3) + "<br>" +
                        "Amount: " + resultSet.getDouble(4) + "$<br>" +
                        "Description: " + resultSet.getString(5) + "<br>" +
                        "Time Submitted: " + resultSet.getTimestamp(6) + "<br>" +
                        "Status: " + resultSet.getString(7) + "<br>" +
                        "</div></section>";
        }
        return tickets;
    }
    
    public String historyAll() throws SQLException {
    	String sql = "select * from ticket";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        String tickets = "";
        while (resultSet.next()) {
                tickets += "<section class=\"container p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success\"><div>" +
                        "Ticket: " + resultSet.getInt(1) + "<br>" +
                        "Type: " + resultSet.getString(3) + "<br>" +
                        "Amount: " + resultSet.getDouble(4) + "<br>" +
                        "Description: " + resultSet.getString(5) + "<br>" +
                        "Time Submitted: " + resultSet.getTimestamp(6) + "<br>" +
                        "Status: " + resultSet.getString(7) + "<br>" +
                        "</div></section>";
        }
        return tickets;
    }
    
    public boolean manage(int id) throws SQLException {
    	String sql = "select * from employee where EmpID = " + id;
    	Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getBoolean(7);
    }
}