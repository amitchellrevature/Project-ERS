package requests;
import java.sql.*;

import java.text.DecimalFormat;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

interface TicketsDAO {
	
    public void request(Tickets ticket) throws SQLException;
    
    public int login(String username, String password) throws SQLException;
    
    public List<Tickets> historyEmployee(int empID) throws SQLException;
    
    public List<Tickets> historyAll() throws SQLException;
    
    public boolean manage(int id) throws SQLException;
    
    public void changeTicket(Tickets ticket) throws SQLException;
    
//    public void rejectTicket(int id) throws SQLException;
}

public class TicketsImpl implements TicketsDAO {
	
	DecimalFormat df = new DecimalFormat("0.00");

    public TicketsImpl() {}

    public void request(Tickets ticket) throws SQLException {
    	
    	SessionFactory factory = ConnectionFactory.configHibernate().buildSessionFactory();
    	Session session = factory.openSession();
    	Transaction transaction = session.beginTransaction();
    	session.save(ticket);
    	transaction.commit();
    	session.close();
    	
//        String sql = "insert into ticket (EmpID, type, amount, description, time, status) values (?, ?, ?, ?, SYSDATE(), \"Pending\")";
//        PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql);
//        preparedStatement.setInt(1, id);
//        preparedStatement.setString(2, type);
//        preparedStatement.setDouble(3, amount);
//        preparedStatement.setString(4, description);
//        preparedStatement.executeUpdate();
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
    
    public List<Tickets> historyEmployee(int empID) throws SQLException {
    	
       	SessionFactory factory = ConnectionFactory.configHibernate().buildSessionFactory();
    	Session session = factory.openSession();
    	String table = "from Tickets where EmpID= :empId";
		Query query = session.createQuery(table);
    	query.setParameter("empId", empID);
    	List<Tickets> allTickets = query.list();
    	session.close();
    	if(allTickets.size() == 0) {
    		return null;
    	}
    	return allTickets;
    	
//    	String sql = "select * from ticket where EmpID = " + id;
//        Statement statement =  ConnectionFactory.getConnection().createStatement();
//        ResultSet resultSet = statement.executeQuery(sql);
//        String tickets = "";
//        while (resultSet.next()) {
//                tickets += "<section class=\"text-warning w-50 container p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success\"><div>" +
//                        "Ticket: " + resultSet.getInt(1) + "<br>" +
//                        "Type: " + resultSet.getString(3) + "<br>" +
//                        "Amount: $" + df.format(resultSet.getDouble(4)) + "<br>" +
//                        "Description: " + resultSet.getString(5) + "<br>" +
//                        "Time Submitted: " + resultSet.getTimestamp(6) + "<br>" +
//                        "Status: " + resultSet.getString(7) + "<br>" +
//                        "</div></section>";
//        }
    }
    
    public List<Tickets> historyAll() throws SQLException {
    	
    	SessionFactory factory = ConnectionFactory.configHibernate().buildSessionFactory();
    	Session session = factory.openSession();
    	String table = "from Tickets";
    	Query query = session.createQuery(table);
    	List<Tickets> allTickets = query.list();
    	session.close();
    	if(allTickets.size() == 0) {
    		return null;
    	}
    	return allTickets;
    	
//    	String sql = "select * from ticket";
//      Statement statement = ConnectionFactory.getConnection().createStatement();
//      ResultSet resultSet = statement.executeQuery(sql);
//      String tickets = "";
//      while (resultSet.next()) {
//	    	switch(filter) {
//	
//	    	case "All":
//	    	    tickets += "<section class=\"container text-warning w-50 p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success\">" +
//	    	            "Ticket: " + resultSet.getInt(1) + "<br>" +
//	    	            "Type: " + resultSet.getString(3) + "<br>" +
//	    	            "Amount: $" + df.format(resultSet.getDouble(4)) + "<br>" +
//	    	            "Description: " + resultSet.getString(5) + "<br>" +
//	    	            "Time Submitted: " + resultSet.getTimestamp(6) + "<br>" +
//	    	            "Status: " + resultSet.getString(7) + "<br>";
//	    	    
//	    	    if(resultSet.getString(7).equalsIgnoreCase("pending")) {
//	    	 
//	    	        tickets += "Action: <div class='text-center my-2'>"
//	    	        		+ "<form action='Reimburse' method='post'>"
//	    	                + "<select name='action' class='form-control'>"
//	    	                + "<option value='Approved'>Approve</option>"
//	    	                + "<option value='Rejected'>Reject</option>"
//	    	                + "</select>"
//	    	                + "<input type='hidden' name='id' value='" + resultSet.getInt(1) + "'>"
//	    	                + "<input type='submit' class='btn btn-success my-2' value='Continue'>"
//	    	                + "</form></div><br>";
//	    	    }
//	    	   
//	    	    tickets += "</section>";
//	    	    break;
//	    	case "Pending":
//	    		
//	    		if(resultSet.getString(7).equalsIgnoreCase("pending")) {
//	    	    
//	    			tickets += "<section class=\"container text-warning w-50 p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success\">" +
//	    	                "Ticket: " + resultSet.getInt(1) + "<br>" +
//	    	                "Type: " + resultSet.getString(3) + "<br>" +
//	    	                "Amount: $" + df.format(resultSet.getDouble(4)) + "<br>" +
//	    	                "Description: " + resultSet.getString(5) + "<br>" +
//	    	                "Time Submitted: " + resultSet.getTimestamp(6) + "<br>" + 
//	    	                "Status: " + resultSet.getString(7) + "<br>"
//	    	                 +"Action: <div class='text-center my-2'>"
//	    	                 + "<form action='Reimburse' method='post'>"
//	    		             + "<select name='action' class='form-control'>"
//	    		             + "<option value='Approved'>Approve</option>"
//	    		             + "<option value='Rejected'>Reject</option>"
//	    		             + "</select>"
//	    		             + "<input type='hidden' name='id' value='" + resultSet.getInt(1) + "'>"
//	    		             + "<input type='submit' class='btn btn-success my-2' value='Continue'>"
//	    		             + "</form></div><br>"
//	    		             + "</section>";
//	    	    }
//	    	    break;
//	    	case "Approved":
//	    	    
//	    		if(resultSet.getString(7).equalsIgnoreCase("approved")) {
//	    			
//	    			tickets += "<section class=\"container text-warning w-50 p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success\">" +
//	    	                "Ticket: " + resultSet.getInt(1) + "<br>" +
//	    	                "Type: " + resultSet.getString(3) + "<br>" +
//	    	                "Amount: $" + df.format(resultSet.getDouble(4)) + "<br>" +
//	    	                "Description: " + resultSet.getString(5) + "<br>" +
//	    	                "Time Submitted: " + resultSet.getTimestamp(6) + "<br>" +
//	    	                "Status: " + resultSet.getString(7) + "<br>"
//	    	                + "</section>";
//	    		}
//	    	    break;
//	    	case "Rejected":
//	    	    
//	    		if(resultSet.getString(7).equalsIgnoreCase("rejected")) {
//	    			
//	    			tickets += "<section class=\"container text-warning w-50 p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success\">" +
//	    	                "Ticket: " + resultSet.getInt(1) + "<br>" +
//	    	                "Type: " + resultSet.getString(3) + "<br>" +
//	    	                "Amount: $" + df.format(resultSet.getDouble(4)) + "<br>" +
//	    	                "Description: " + resultSet.getString(5) + "<br>" +
//	    	                "Time Submitted: " + resultSet.getTimestamp(6) + "<br>" +
//	    	                "Status: " + resultSet.getString(7) + "<br>"
//	    	                + "</section>";
//	    		}
//	    		break;		                
//	    	}
//	
//	    }
    }
    
    public boolean manage(int id) throws SQLException {
    	String sql = "select * from employee where EmpID = " + id;
    	Statement statement = ConnectionFactory.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getBoolean(7);
    }
    
    public void changeTicket(Tickets ticket) throws SQLException {
    	
    	SessionFactory factory = ConnectionFactory.configHibernate().buildSessionFactory();
    	Session session = factory.openSession();
    	Transaction transaction = session.beginTransaction();
    	String table = "update Tickets set Status= :status where TicketID= :ticketId";
    	Query query = session.createQuery(table);
    	query.setParameter("status", ticket.getStat());
    	query.setParameter("ticketId", ticket.getTicketId());	
    	query.executeUpdate();
    	transaction.commit();
    	session.close();    	
    	
//        String sql = "update ticket set Status = ? where TicketID = " + id;
//        PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql);
//        preparedStatement.setString(1, "Approved");
//        preparedStatement.executeUpdate();
    }
    
//    public void rejectTicket(int id) throws SQLException {
//    	
//        String sql = "update ticket set Status = ? where TicketID = " + id;
//        PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql);
//        preparedStatement.setString(1, "Rejected");
//        preparedStatement.executeUpdate();
//    }
}