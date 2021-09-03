
import java.sql.*;
import java.text.DecimalFormat;

public class TicketDAO {
	
	DecimalFormat df = new DecimalFormat("0.00");

    public TicketDAO() {}

    public void request(int id, String type, double amount, String description) throws SQLException {
        String sql = "insert into ticket (EmpID, type, amount, description, time, status) values (?, ?, ?, ?, SYSDATE(), \"Pending\")";
        PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, type);
        preparedStatement.setDouble(3, amount);
        preparedStatement.setString(4, description);
        preparedStatement.executeUpdate();
    }
    
    public int login(String username, String password) throws SQLException {
    	String sql = "select * from employee where username = ? and password = ?";
        PreparedStatement preparedStatement =  ConnectionFactory.getConnection().prepareStatement(sql);
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
        Statement statement =  ConnectionFactory.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        String tickets = "";
        while (resultSet.next()) {
                tickets += "<section class=\\\"container p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success\\\"><div>" +
                        "Ticket: " + resultSet.getInt(1) + "<br>" +
                        "Type: " + resultSet.getString(3) + "<br>" +
                        "Amount: $" + df.format(resultSet.getDouble(4)) + "<br>" +
                        "Description: " + resultSet.getString(5) + "<br>" +
                        "Time Submitted: " + resultSet.getTimestamp(6) + "<br>" +
                        "Status: " + resultSet.getString(7) + "<br>" +
                        "</div></section>";
        }
        return tickets;
    }
    
    public String historyAll(String filter) throws SQLException {
    	String sql = "select * from ticket";
        Statement statement = ConnectionFactory.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        String tickets = "";
        while (resultSet.next()) {
        	
        		switch(filter) {
        		
        		case "All":
	                tickets += "<section class=\"container text-warning w-50 p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success\">" +
	                        "Ticket: " + resultSet.getInt(1) + "<br>" +
	                        "Type: " + resultSet.getString(3) + "<br>" +
	                        "Amount: $" + df.format(resultSet.getDouble(4)) + "<br>" +
	                        "Description: " + resultSet.getString(5) + "<br>" +
	                        "Time Submitted: " + resultSet.getTimestamp(6) + "<br>" +
	                        "Status: " + resultSet.getString(7) + "<br>";
	                
	                if(resultSet.getString(7).equalsIgnoreCase("pending")) {
	             
		                tickets += "Action: <div class='text-center my-2'>"
		                		+ "<form action='Reimburse' method='post'>"
		                        + "<select name='action' class='form-control'>"
		                        + "<option value='Approved'>Approve</option>"
		                        + "<option value='Rejected'>Reject</option>"
		                        + "</select>"
		                        + "<input type='hidden' name='id' value='" + resultSet.getInt(1) + "'>"
		                        + "<input type='submit' class='btn btn-success my-2' value='Continue'>"
		                        + "</form></div><br>";
	                }
	               
	                tickets += "</section>";
	                break;
        		case "Pending":
        			
        			if(resultSet.getString(7).equalsIgnoreCase("pending")) {
	                
	        			tickets += "<section class=\"container text-warning w-50 p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success\">" +
		                        "Ticket: " + resultSet.getInt(1) + "<br>" +
		                        "Type: " + resultSet.getString(3) + "<br>" +
		                        "Amount: $" + df.format(resultSet.getDouble(4)) + "<br>" +
		                        "Description: " + resultSet.getString(5) + "<br>" +
		                        "Time Submitted: " + resultSet.getTimestamp(6) + "<br>" + 
		                        "Status: " + resultSet.getString(7) + "<br>"
		                         +"Action: <div class='text-center my-2'>"
			                     + "<form action='Reimburse' method='post'>"
					             + "<select name='action' class='form-control'>"
					             + "<option value='Approved'>Approve</option>"
					             + "<option value='Rejected'>Reject</option>"
					             + "</select>"
					             + "<input type='hidden' name='id' value='" + resultSet.getInt(1) + "'>"
					             + "<input type='submit' class='btn btn-success my-2' value='Continue'>"
					             + "</form></div><br>"
					             + "</section>";
	                }
	                break;
        		case "Approved":
	                
        			if(resultSet.getString(7).equalsIgnoreCase("approved")) {
        				
	        			tickets += "<section class=\"container text-warning w-50 p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success\">" +
		                        "Ticket: " + resultSet.getInt(1) + "<br>" +
		                        "Type: " + resultSet.getString(3) + "<br>" +
		                        "Amount: $" + df.format(resultSet.getDouble(4)) + "<br>" +
		                        "Description: " + resultSet.getString(5) + "<br>" +
		                        "Time Submitted: " + resultSet.getTimestamp(6) + "<br>" +
		                        "Status: " + resultSet.getString(7) + "<br>"
		                        + "</section>";
        			}
	                break;
        		case "Rejected":
	                
        			if(resultSet.getString(7).equalsIgnoreCase("rejected")) {
        				
	        			tickets += "<section class=\"container text-warning w-50 p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success\">" +
		                        "Ticket: " + resultSet.getInt(1) + "<br>" +
		                        "Type: " + resultSet.getString(3) + "<br>" +
		                        "Amount: $" + df.format(resultSet.getDouble(4)) + "<br>" +
		                        "Description: " + resultSet.getString(5) + "<br>" +
		                        "Time Submitted: " + resultSet.getTimestamp(6) + "<br>" +
		                        "Status: " + resultSet.getString(7) + "<br>"
		                        + "</section>";
        			}
        			break;		                
        		}
        }
        return tickets;
    }
    
    public boolean manage(int id) throws SQLException {
    	String sql = "select * from employee where EmpID = " + id;
    	Statement statement = ConnectionFactory.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getBoolean(7);
    }
    
    public void approveTicket(int id) throws SQLException {
    	
        String sql = "update ticket set Status = ? where TicketID = " + id;
        PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, "Approved");
        preparedStatement.executeUpdate();
    }
    
    public void rejectTicket(int id) throws SQLException {
    	
        String sql = "update ticket set Status = ? where TicketID = " + id;
        PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, "Rejected");
        preparedStatement.executeUpdate();
    }
}