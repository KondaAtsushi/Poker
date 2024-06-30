<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="poker.PokerModel" %>

<%
PokerModel model = (PokerModel)request.getAttribute("model");
String label = model.getButtonLabel();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Poker</title>
</head>
<body>

<b><font color="green" size="7">ポーカーゲーム</font></big></b>
<hr>
ゲーム回数:<%= model.getGames() %>
<br>
チップ:<%=model.getChips() %>
<hr>
<%= model.getMessage() %>
<form action="/s2232056/PokerServlet" method="POST">
<table>
<tr>
<td><img src="cards/<%= model.getHandcardAt(0) %>.png" width="100" height="150"></td>
<td><img src="cards/<%= model.getHandcardAt(1) %>.png" width="100" height="150"></td>
<td><img src="cards/<%= model.getHandcardAt(2) %>.png" width="100" height="150"></td>
<td><img src="cards/<%= model.getHandcardAt(3) %>.png" width="100" height="150"></td>
<td><img src="cards/<%= model.getHandcardAt(4) %>.png" width="100" height="150"></td>
</tr>
<tr align="center">
<td><input type="checkbox" name="change" value="0" class="big"></td>
<td><input type="checkbox" name="change" value="1" class="big"></td>
<td><input type="checkbox" name="change" value="2" class="big"></td>
<td><input type="checkbox" name="change" value="3" class="big"></td>
<td><input type="checkbox" name="change" value="4" class="big"></td>
</tr>
</table>
<input type ="submit" value="<%=label %>" class="width">
<hr>
レイズ  ×２ <td><input type="checkbox" name="raise" value="0" class ="big"></td>
<style>
input.big {
	transform: scale(2);
}
input.width {
  padding-left: 20em;
  padding-right: 20em;
  height: 3em;
}
</style>
</form>
<hr>
<a href="/s2232056/PokerServlet">リセット</a>
<hr>
</body>
</html>