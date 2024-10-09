<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>Coffee _ Home</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="stylesheet" type="text/css" href="/css/comm.css">

</head>
<body>

<!-- 헤더 위치 -->
<%@include file="/WEB-INF/views/v2/comm/header.jsp"%>

<div id="main" style="font-size:large; alignment: center; padding: 10px 50px ">
  <table>
    <tr>
      <td style="width:450px">
      <p>따라하며 배우는 Spring boot with IT 늦공 김부장<p>
        우리 같이 Spring boot 항해를 같이해요. <p>
        제가 이 배의 선장이 되어 볼게요.
      </p>
      <p>
        <hr>
      <ul style="list-style-type:square;">
        <li>개발툴 : 인텔리J</li>
        <li>Java 버전 : 1.8</li>
        <li>View  : 타임리프</li>
        <li>DBMS : MaridDB</li>
        <li>Mapper : 마이바티스</li>
      </ul>
      </p>
      </td>
      <td style="width:auto">
        <img src="/img/Ship.PNG" style="width: 700px">
      </td>
    </tr>
  </table>
</div>

<!--푸터위치-->
<%@include file="/WEB-INF/views/v2/comm/footer.jsp"%>

</body>
</html>


