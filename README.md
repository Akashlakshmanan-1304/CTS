@PostMapping("/addFund")
 public String addFundDetails(@ModelAttribute("donate") Sponsor sponsor, BindingResul
 // Fill the code
 service.addFundDetails(sponsor);
 model.addAttribute("message","Thank you "+sponsor.getSponsorName()+",Happy for y
 return "showPage";
 }
 // invoke the service class - viewFundList method.
 @GetMapping("/viewList")
 public String viewFundList(ModelMap model) throws NoFundsRaisedException {
 // Fill the code
 model.addAttribute("fundList",service.viewFundList());
 return "viewList";
 }
 @ModelAttribute("donationFor")
 public List<String> populationDonationList(){
 return Arrays.asList("Education","Food","Medicine","Cloth","MedicalExpense");
 }
 // Use appropriate annotation
 @ExceptionHandler(NoFundsRaisedException.class)
 public ModelAndView exceptionHandler(NoFundsRaisedException e) {
 // Fill the code
 ModelAndView mv=new ModelAndView("exceptionPage");
 mv.addObject("errMsg");
 return mv;
 }

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/htm
<html>
<head>
<style>
* {
 box-sizing: border-box;
}
.row {
 margin-left:-5px;
 margin-right:-5px;
}
 
.column {
 float: left;
 width: 60%;
 padding: 3px;
}
.column1 {
 float: left;
 width: 25%;}
/* Clearfix (clear floats) */
.row::after {
 content: "";
 clear: both;
 display: table;
}
table {
 border-collapse: collapse;
 border-spacing: 0;
 width: 100%;
}
th, td {
 text-align: left;
 padding: 5px;
}
#message{
color:red;
}
</style>
</head>
<body style="background-color:lavender"><!-- Do not change the below line -->
<a href="/showPage?language=en">English</a>|<a href="/showPage?language=de">German</a>|<a
<!-- Design the page as per the requirements-->
<h1><center>Charity Trust</center></h1>
<form:form >
<div class="row">
 <div class="column1">
<table>
<tr>
<td><a href="index">Home</a><br> </td>
<tr><td><a href="viewList">View Fund Details</a></td> </tr>
</table>
</div>
 <div class="column">
<table style="margin: 0px auto; margin-left: auto; margin-right:auto">
 <tr>
 <td><spring:message code="label.sponsorName"/></td>
 <td><form:input path="sponsorName" id="sponsorName"/></td>
 <td><form:errors path="sponsorName" /></td>
 </tr>
 
 <tr>
 <td><spring:message code="label.phoneNumber"/></td>
 <td><form:input path="phoneNumber" id="phoneNumber"/></td>
 <td><form:errors path="phoneNumber" /></td>
 </tr><tr>
 <td><spring:message code="label.donationFor"/></td>
 <td><form:select path="donationFor" id="donationFor">
 <form:options items="${donationFor}"/>
 </form:select>
 <td><form:errors path="donationFor"/>
 </td>
 </tr>
 
 <tr>
 <td><spring:message code="label.date"/></td>
 <td><form:input path="date" id="date"/></td>
 <td><form:errors path="date" /></td>
 </tr><tr>
 <td><spring:message code="label.amount"/></td>
 <td><form:input path="amount" id="amount"/></td>
 <td><form:errors path="amount" /></td>
 </tr>
 
 <tr>
 <td colspan="3" align="center">
 <input type="submit" id="submit" value="Add Fund"/>
 </td>
 </tr>
 </table>
 </div>
 </div>
 <div id="message"> <c:out value="${message}"/> </div></center>
</form:form>
 
 <tr>
