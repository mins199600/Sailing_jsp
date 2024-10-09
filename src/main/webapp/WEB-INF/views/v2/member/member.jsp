
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Member List</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" type="text/css" href="/css/comm.css">
    <link rel="stylesheet" type="text/css" href="/css/coffee.css">

</head>
<body>

<!-- 헤더 위치 -->
<th:block th:replace="/v2/comm/header :: headerFragment"></th:block>


<div id="main" style="font-size:large; text-align: center; ">

    <div id="search" style="height: 150px;padding: 15px; font-size: small; width: 90%; margin-left: auto;  margin-right: auto;">
        <h3>[ Member Info <span style="font-size:30px;">&#128699;</span> ] <th:block th:text="${hello}"></th:block></h3>

        <form name="fm_member" autocomplete="on" action="/v2/member_search" method="post">
            <fieldset>
                <legend> [검색조건] </legend>
                <label>등록기간</label><input type="date" id="start_date" name="start_date" min="2020-01-01" max="2023-12-31">
                - <input type="date" id="end_date" name="end_date" min="2020-01-01" max="2023-12-31">
                &nbsp;&nbsp;
                <label>고객명</label> <input type="text" id="name" name="name">


                &nbsp;&nbsp;<input type="submit" value="조회" style="width: 80px;height: 30px;font-weight: bold; font-size: medium">

            </fieldset>

        </form>


    </div>


    <table class="table">
        <thead>
        <tr class="tr_td">
            <th>Chk</th>
            <th>고객ID</th>
            <th>고객명</th>
            <th>이메일</th>
            <th>권한</th>
            <th>등록일</th>
        </tr>
        </thead>


        <tbody id="t_body">


        <tr class="tr_td" th:each="prod : ${list}">
            <td><input type="checkbox" name="chkMemberNo" th:value="${prod.getNo()}"></td>
            <th th:text="${prod.getCust_id()}">고객ID</th>
            <td th:text="${prod.getName()}">고객명</td>
            <td th:text="${prod.getEmail()}">이메일</td>
            <td th:text="${prod.getRole()}">권한</td>
            <td th:text="${prod.getReg_day()}">등록일</td>
        </tr>
        </tbody>
    </table>
</div>

<!--푸터위치-->
<th:block th:replace="/v2/comm/footer :: footerFragment"></th:block>

<script>
    /* 현재 시간 날짜에 적용시키기 */
    const now = new Date();	// 현재 날짜 및 시간
    const time7 = new Date(now.setDate(now.getDate() - 100));	// 기간 설정
    document.getElementById("start_date").value= time7.toISOString().slice(0,10);
    document.getElementById("end_date").value= new Date().toISOString().slice(0,10);
</script>

</body>
</html>


