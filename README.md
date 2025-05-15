<div class="row">
 <div class="column1">
<table>
<tr>
<td><a href="index">Home</a><br> </td>
<tr><td><a href="viewAllCandidate">View All Candidate Info</a></td> </tr>
</table>
</div>
 <div class="column">
<table style="margin: 0px auto; margin-left: auto; margin-right:auto">
 
 
 <!-- fill the code -->
 <tr>
 <td><fmt:message key = "label.candidateId"/></td>
 <td><form:input path = "candidateId" id = "candidateId"/></td>
 </tr>
 
 <tr>
 <td><fmt:message key = "label.candidateId"/></td>
 <td><form:input path = "name" id = "name"/></td>
 </tr>
 <tr>
 <td><fmt:message key = "label.emailId"/></td>
 <td><form:input path = "emailId" id = "emailId"/></td>
 </tr>
 
 <tr> </tr>
 
 <tr>
 <td><fmt:message key = "label.candidateId"/></td>
 <td>
 <form:select path = "designation" id = "designation"/>
 <form:options items = "${designation}" itemValue = "id" itemLabel = "name"/>
 </form:select></td>
 </tr>
 <tr>
 <td><fmt:message key = "label.yearsOfExperience"/></td>
 <td><form:input path = "yearsOfExperience" id = "yearsOfExperience"/></td>
 </tr>
 <tr>
 <td><fmt:message key = "label.expectedSalary"/></td>
 <td><form:input path = "expectedSalary" id = "expectedSalary"/></td>
 </tr>
 <tr>
 <td colspan = "2" style = "text-align: center;">
 <form:button type = "submit">Register</form:button>
 </td>
 </tr>
 </table>
 </div>
 </div>
 <center>
 <div id="message"> </div></center>
</form:form>
</body>
</html>
