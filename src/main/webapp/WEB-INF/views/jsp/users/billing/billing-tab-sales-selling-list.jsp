<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<tr>
		<td><c:out value="${param.index+1}"></c:out></td>
		<td><input id="salesStockList[${param.index}]stockEntryDate" name="salesStockList[${param.index}].stockEntryDate" value="${param.stockEntryDate}" class="form-control form-control-sm " type="date" readonly/></td>
		<td><input id="salesStockList[${param.index}]barcode" name="salesStockList[${param.index}].barcode" value="${param.barcode}" class="form-control form-control-sm" type="text" readonly/></td>
		<td><input id="salesStockList[${param.index}]quantity" name="salesStockList[${param.index}].quantity" value="${param.quantity}" class="form-control form-control-sm" type="number" readonly/></td>
		<td><input id="salesStockList[${param.index}]stockPrice" name="salesStockList[${param.index}].stockPrice" value="${param.stockPrice}" class="form-control form-control-sm" type="number" readonly /></td>
		<td><input id="salesStockList[${param.index}]sellingPrice" name="salesStockList[${param.index}].sellingPrice" value="${param.sellingPrice}" class="form-control form-control-sm" type="number" required autofocus/></td>
		<td><button type='button' onclick='deleteDatafromSellingList(this,"${param.barcode}");' class='btn btn-default'>
			        <img src="https://img.icons8.com/ios-glyphs/20/000000/trash--v3.png"/></button></td>	
	</tr>


