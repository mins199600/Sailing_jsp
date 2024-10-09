<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="com.boot.sailing_jsp.v2.vo.Order_list"%>
<%
    String hello = (String) request.getAttribute("hello");
    List<Order_list> list = (List<Order_list>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Coffee Order List</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" type="text/css" href="/css/comm.css">
    <link rel="stylesheet" type="text/css" href="/css/coffee.css">

</head>
<body>

<!-- 헤더 위치 -->
<%@include file="/WEB-INF/views/v2/comm/header.jsp"%>

<div id="main" style="font-size:large; text-align: center; ">

    <div id="search" style="height: 150px;padding: 15px; font-size: small; width: 90%; margin-left: auto;  margin-right: auto;">
        <h3>[ Coffee Order List <span style="font-size:30px;">&#128722;</span> ] <th:block th:text="${hello}"></th:block> </h3>

        <form name="fm_order" autocomplete="on" action="/v2/order_search" method="post">
            <fieldset>
                <legend> [검색조건] </legend>
                <label>등록기간</label><input type="date" id="start_date" name="start_date" min="2024-01-01" max="2024-12-31">
                - <input type="date" id="end_date" name="end_date" min="2024-01-01" max="2024-12-31">
                &nbsp;&nbsp;
                <label>메뉴명</label> <input type="text" id="coffee" name="coffee">
                &nbsp;&nbsp;
                <label>고객명</label> <input type="text" id="name" name="name">

                &nbsp;&nbsp;<input type="submit" value="조회" style="width: 80px;height: 30px;font-weight: bold; font-size: medium">

                <!-- <a href="javascript:loadDocArray()">test</a> -->
                <!--        <label>CheckBox : </label><span id="idCheckBox"></span>-->
            </fieldset>

        </form>


    </div>


    <table class="table">
        <thead>
        <tr class="tr_td">
            <th>Chk</th>
            <th>주문번호</th>
            <th>커피No</th>
            <th>메뉴명</th>
            <th>가격</th>
            <th>고객ID</th>
            <th>고객명</th>
            <th>주문일자</th>
        </tr>
        </thead>


        <tbody id="t_body">
        <% for(Order_list orderList : list){ %>
         <tr class="tr_td">
            <td><input type="checkbox" name="chkOrderNo" value="<%=orderList.getNo()%>>"></td>
            <td><%=orderList.getNo()%></td>
            <td><%=orderList.getCoffee_no()%></td>
            <td><%=orderList.getCoffee()%></td>
            <td><%=orderList.getPrice()%></td>
            <td><%=orderList.getCust_id()%></td>
            <td><%=orderList.getName()%></td>
            <td><%=orderList.getReg_day()%></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<!--푸터위치-->
<%@include file="/WEB-INF/views/v2/comm/footer.jsp"%>

<script>
    /* 현재 시간 날짜에 적용시키기 */
    const now = new Date();	// 현재 날짜 및 시간
    const time7 = new Date(now.setDate(now.getDate() - 100));	// 기간 설정
    document.getElementById("start_date").value= time7.toISOString().slice(0,10);
    document.getElementById("end_date").value= new Date().toISOString().slice(0,10);
</script>

</body>
</html>


