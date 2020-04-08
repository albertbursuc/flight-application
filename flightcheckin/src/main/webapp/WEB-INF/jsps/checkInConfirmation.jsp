<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Check In Confirmation</title>
</head>
<body>

<h2>Check In completed successfully!</h2><br/>

<h2>Flight details:</h2>

Airlines: ${reservation.flight.operatingAirlines}<br/>
Flight No: ${reservation.flight.flightNumber}<br/>
Departure city: ${reservation.flight.departureCity}<br/>
Arrival city: ${reservation.flight.arrivalCity}<br/>
Date of departure: ${reservation.flight.dateOfDeparture}<br/>
Estimated departure time: ${reservation.flight.estimatedDepartureTime}<br/>

<h2>Passenger details:</h2><br/>

First name: ${reservation.passenger.firstName}<br/>
Last name: ${reservation.passenger.lastName}<br/>
Email: ${reservation.passenger.email}<br/>
Phone: ${reservation.passenger.phone}<br/>
Number of bags: ${reservation.numberOfBags}

</body>
</html>