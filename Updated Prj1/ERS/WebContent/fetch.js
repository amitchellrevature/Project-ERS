/**
 * 
 */
 function fetchAllTickets(){
	fetch('History')
	.then(res => { 
		res.json()
		.then(data => {
			const filter = document.getElementById("filter").value;
			let tickets = "";
			for(let i = 0; i <= data.length-1; i++){
				if(filter === "All"){
					tickets += "<div class='container text-warning w-50 p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success'>" + 
					"Ticket: " + data[i].ticketId + "<br>" +
					"Type: " + data[i].expType + "<br>" +
					"Amount: $" + data[i].amount + "<br>" +
					"Description: " + data[i].desc + "<br>" +
					"Time Submitted: " + data[i].date + "<br>" +
					"Status: " + data[i].stat + "<br>";
					if(data[i].stat === "Pending") {
						tickets += "Action: <div class='text-center my-2'>" 
						+ "<form action='Reimburse' method='post'>"
						+ "<select name='action' class='form-control'>"
						+ "<option value='Approved'>Approve</option>"
						+ "<option value='Rejected'>Reject</option>"
						+ "</select>"
						+ "<input type='hidden' name='details' value='" + data[i].ticketId 
						+"," + data[i].empId + "," + data[i].expType + "," + data[i].amount 
						+ "," + data[i].desc + "," + data[i].date + "," + data[i].stat + "'>"
						+ "<input type='submit' class='btn btn-success my-2' value='Continue'>"
						+ "</form></div><br>";
					}
					tickets += "</div>";
				} else if(filter === "Pending"){
					if(data[i].stat === "Pending") {
						tickets += "<div class='container text-warning w-50 p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success'>" + 
						"Ticket: " + data[i].ticketId + "<br>" +
						"Type: " + data[i].expType + "<br>" +
						"Amount: $" + data[i].amount + "<br>" +
						"Description: " + data[i].desc + "<br>" +
						"Time Submitted: " + data[i].date + "<br>" +
						"Status: " + data[i].stat + "<br>" + 
						"Action: <div class='text-center my-2'>" 
						+ "<form action='Reimburse' method='post'>"
						+ "<select name='action' class='form-control'>"
						+ "<option value='Approved'>Approve</option>"
						+ "<option value='Rejected'>Reject</option>"
						+ "</select>"
						+ "<input type='hidden' name='details' value='" + data[i].ticketId 
						+"," + data[i].empId + "," + data[i].expType + "," + data[i].amount 
						+ "," + data[i].desc + "," + data[i].date + "," + data[i].stat + "'>"
						+ "<input type='submit' class='btn btn-success my-2' value='Continue'>"
						+ "</form></div><br>"
						+ "</div>";
					}
				} else if(filter === "Approved"){
					if(data[i].stat === "Approved") {
						tickets += "<div class='container text-warning w-50 p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success'>" + 
						"Ticket: " + data[i].ticketId + "<br>" +
						"Type: " + data[i].expType + "<br>" +
						"Amount: $" + data[i].amount + "<br>" +
						"Description: " + data[i].desc + "<br>" +
						"Time Submitted: " + data[i].date + "<br>" +
						"Status: " + data[i].stat + "<br>"
						+ "</div>";
					}
				} else if(filter === "Rejected"){
					if(data[i].stat === "Rejected") {
						tickets += "<div class='container text-warning w-50 p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success'>" + 
						"Ticket: " + data[i].ticketId + "<br>" +
						"Type: " + data[i].expType + "<br>" +
						"Amount: $" + data[i].amount + "<br>" +
						"Description: " + data[i].desc + "<br>" +
						"Time Submitted: " + data[i].date + "<br>" +
						"Status: " + data[i].stat + "<br>"
						+ "</div>";
					}
				}
				document.getElementById("content").innerHTML = tickets;
			}
		}) 
	})
	.catch(err => { console.log(err) })
}

function fetchEmployeeTickets(){
	fetch('EmployeeHistoryTickets')
	.then(res => { 
		res.json()
		.then(data => {
			let tickets = "";
			tickets += "<h5 class='text-center text-warning'>Employee Id: " + data[0].empId + "</h5>";
				for(let i = 0; i <= data.length-1; i++) {
					tickets += "<div class='text-warning w-50 container p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success'>" +
					"Ticket: " + data[i].ticketId + "<br>" +
		            "Type: " + data[i].expType + "<br>" +
		            "Amount: $" + data[i].amount + "<br>" +
		            "Description: " + data[i].desc + "<br>" +
		            "Time Submitted: " + data[i].date + "<br>" +
		            "Status: " + data[i].stat + "<br>" +
		            "</div>";
				}
				document.getElementById("content").innerHTML = tickets;				
		}) 
	})
	.catch(err => { console.log(err) })
}