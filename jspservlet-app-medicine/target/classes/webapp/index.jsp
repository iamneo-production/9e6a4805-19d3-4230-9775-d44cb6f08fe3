<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<!DOCTYPE html>
	<html lang="en">
	<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Medicine App</title>
	
	<!-- -----------------------All The CSS Links Goes Here----------------------- -->

	<!-- Icon Set -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
	<style>
		*{
    padding:0;
    margin:0;
    font-weight: bold;
}
/* Add a black background color to the top navigation bar */
.topnav {
    overflow: hidden;
    background-color: green;
  }
  
  /* Style the links inside the navigation bar */
  .topnav a {
    float: left;
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
    font-size: 1.4rem;
  }
  /* Style the search box inside the navigation bar */
  .topnav form{
    height: 100%;
    float: right;
    border: none;
    margin-right: 5vw;
  }
  .topnav input{
    height: 6vh;
    width: 20vw;
    text-align: center;
    font-weight: bold;
    font-size: 1rem;
  }
  .topnav #searchButton{
    height: 6vh;
    width:10vw;
    color: white;
    background-color: black;
    font-weight: bold;
    font-size: 1rem;
  }

  #main{
    max-width: 60vw;
    float: left;
    min-height: 100vh;
  }
  .grid-container {
    display: grid;
    grid-template-columns: 50% auto;
  }
  .grid{
    width: 88%;
    float: left;
    margin: 2rem;
    background: grey;
    border-radius: 2px;
    padding: 2rem;
  }
  .grid-item p{
    margin: 1rem 0;
    font-size: 1.2rem;
  }
  .grid-item .icons{
    float: right;
  }
  i{
    margin: 1rem;
    font-size: 1.5rem;
  }
  .grid-item a{
    color: black;
  }

  .addbutton{
    width: 100%;
  }
  .addbutton a{
    float: right;
    margin: 1rem;
    background-color: gray;
    color: black ;
    padding: 0.5rem;
    text-decoration: none;
    font-size: 1.2rem;
  }
  .addbutton i{
    margin: 0;
  }
  #addform{
    width: 35vw;
    float: right;
    text-align: center;
    padding-top: 4rem;
  }
  #addform form{
    background: gray;
    margin-right:1rem;
    padding: 1rem;
    border-radius: 30px;
  }
  #addform input{
    width: 70%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
    border-radius: 15px;
    text-align: center;
    font-weight: bold;
    color:black;
    font-size: 1rem;
  }
  
  #addform input[type=submit] {
    width: 30%;
    background-color: black;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 30px;
    cursor: pointer;
  }
	</style>
	</head>
	<body>
	<!-- -----------------------All the Individual Sections Goes Here----------------------- -->
	
	<!-- Navbar with Search Bar -->
	<section class="navbar">
		<div class="topnav">
			<a class="active" href="<%=request.getContextPath()%>/list">Medicine</a>
			<form action="search" method="post">
				<input type="text" name="medicineName" placeholder="type here to search">
				<button type="submit" id="searchButton">Search</button>
			</form>
			
		</div>
	</section>
	
	<!-- Main Section -->
	<section id="main">
		<div class="addbutton">
			<a href="<%=request.getContextPath()%>/new"><i class="bi bi-plus-circle"></i>&nbsp;&nbsp;&nbsp;Add</a>
		</div>
		<%! int i=0; %>  
		<c:forEach var="medicine" items="${listMedicine}">
			<div id=<%= "grid"+(++i) %> class="grid">
				<div class="grid-container">
					<div class="grid-item">
						<p>Medicine Name : <c:out value="${medicine.medicineName}" /> </p>
						<p>Expiry date : <c:out value="${medicine.expiryDate}" /> </p>
						<p>Date of Manufacturing : <c:out value="${medicine.manufacturingDate}" /></p>
						
					</div>
					<div class="grid-item">
						<p>Medicine price : <c:out value="${medicine.medicinePrice}" /> </p>
						<p>Medicine quality : <c:out value="${medicine.medicineQuality}" /> </p>
						<p>
							<div class="icons">
								<a id=<%= "editMedicine"+(++i) %> href="edit?medicineId=<c:out value='${medicine.medicineId }' />"><i class="bi bi-pencil-square"></i></a>
								<a id=<%= "deleteMedicine"+(++i) %> href="delete?medicineId=<c:out value='${medicine.medicineId }' />"><i class="bi bi-trash3-fill" ></i></a>
								
							</div>
						</p>
		
					</div> 
				</div>
			</div>
		</c:forEach>
	</section>
  <div id="addform">
    <c:if test="${medicine != null}">
      <form action="update" method="post">
    </c:if>
    <c:if test="${medicine == null}">
      <form action="insert" method="post">
    </c:if>

    <caption>
      <h2>
      <c:if test="${medicine != null}">
        Edit Medicine
      </c:if>
      <c:if test="${medicine == null}">
        Add New Medicine
      </c:if>
      </h2>
    </caption>
    <c:if test="${medicine != null}">
      <input type="hidden" id="medicineId" name="medicineId" value="<c:out value='${medicine.medicineId}' />" />
    </c:if>
    <br><br>
    <p><label for="enterMedicineName">Medicine Name</label></p>
    <input type="text" id="enterMedicineName" name="medicineName" value="<c:out value='${medicine.medicineName}' />" placeholder="Enter the medicine Name">

    <p><label for="enterExpiryDate">Expiry Date</label></p>
    <input type="date" id="enterExpiryDate" name="expiryDate" value="<c:out value='${medicine.expiryDate}' />" placeholder="Enter the expiry date">

    <p><label for="enterManufacturingDate">Manufacturing Date</label></p>
    <input type="date" id="enterManufacturingDate" name="manufacturingDate" value="<c:out value='${medicine.manufacturingDate}' />" placeholder="Enter the date of manufacturing">
    
    <p><label for="enterMedicinePrice">Medicine Price</label></p>
    <input type="number" id="enterMedicinePrice" name="medicinePrice" value="<c:out value='${medicine.medicinePrice}' />" placeholder="Enter medicine price">
    
    <p><label for="enterMedicineQuality">Medicine Quality</label></p>
    <input type="text" id="enterMedicineQuantity" name="medicineQuality" value="<c:out value='${medicine.medicineQuality}' />" placeholder="Enter medicine quality">     
    <input id="updateMedicine" type="submit" value="Update">
  </form>
</div>
	<!-- -----------------------All The JS Links Goes Here----------------------- -->
	
	</body>
	</html>