import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ticket")
public class Tickets {
	
	@Id
	@Column(name="TicketID")
	private int ticketId;
	
	@Column(name="EmpID")
	private int empId;
	
	@Column(name="Type")
	private String expType;
	
	@Column(name="Amount")
	private double amount;
	
	@Column(name="Description")
	private String desc;
	
	@Column(name="Time")
	private Date date;
	
	@Column(name="Status")
	private String stat; 
	
	public Tickets () {}
	
	public Tickets(int ticketId, int empId, String expType, double amount, String desc, Date date, String stat) {
		this.ticketId = ticketId;
		this.empId = empId;
		this.expType = expType;
		this.amount = amount;
		this.desc = desc;
		this.date = date;
		this.stat = stat;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getExpType() {
		return expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	@Override
	public String toString() {
		return "Tickets [ticketId=" + ticketId + ", empId=" + empId + ", expType=" + expType + ", amount=" + amount
				+ ", desc=" + desc + ", date=" + date + ", stat=" + stat + "]";
	}
}