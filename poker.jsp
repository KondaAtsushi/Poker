<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="poker.PokerModel"%>

<%
PokerModel model = (PokerModel) request.getAttribute("model");
String label = model.getButtonLabel();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Poker</title>
<link rel="stylesheet" href="poker.css">
<script src="poker.js"></script>
</head>
<body>
	<div id="title">
		<b><font color="green" size="7">Poker Game</font></b>
	</div>
	<hr>
	<div id="detail">
		<b>ゲーム回数:<%=model.getGames()%> / チップ:<%=model.getChips()%></b>
	</div>
	<hr>
		<b><font size="5px"><%=model.getMessage()%></font></b>
		<br>
		<div id="main">
		<form action="/Product/PokerServlet" method="POST">
			<table>
				<tr>
					<td><p class="img"><img src="cards/<%=model.getHandcardAt(0)%>.png" width="100" height="150"></p></td>
					<td><img class="img" src="cards/<%=model.getHandcardAt(1)%>.png" width="100" height="150"></td>
					<td><img class="img" src="cards/<%=model.getHandcardAt(2)%>.png" width="100" height="150"></td>
					<td><img class="img" src="cards/<%=model.getHandcardAt(3)%>.png" width="100" height="150"></td>
					<td><img class="img" src="cards/<%=model.getHandcardAt(4)%>.png" width="100" height="150"></td>
				</tr>
				<tr align="center">
					<td><input type="checkbox" name="change" value="0" class="big"></td>
					<td><input type="checkbox" name="change" value="1" class="big"></td>
					<td><input type="checkbox" name="change" value="2" class="big"></td>
					<td><input type="checkbox" name="change" value="3" class="big"></td>
					<td><input type="checkbox" name="change" value="4" class="big"></td>
				</tr>
			</table>
			<input type="submit" value="<%=label%>" class="width">
	</div>
	<hr>
	<b>レイズ ×2</b>
	<td><input type="checkbox" name="raise" value="0" class="big"></td>
	</form>
	<hr>
	<a href="/Product/PokerServlet">リセット</a>
	<hr>
</body>
</html>
