
<!DOCTYPE html>
<html lang="ko"  xmlns:th="http://www.thymeleaf.org">
<head>
  <title>Coffee Menu</title>
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
    <h3>[ Coffee menu Management <span style="font-size:30px;">&#9749;</span> ] <th:block th:text="${hello}"></th:block>  </h3>

    <form name="fm_menu" autocomplete="on" action="/v2/menu_search" method="post">
      <fieldset>

        <legend> [검색조건] </legend>
        <label>등록기간</label><input type="date" id="start_date" name="start_date" min="2024-01-01" max="2024-12-31">
        - <input type="date" id="end_date" name="end_date" min="2024-01-01" max="2024-12-31">
        &nbsp;&nbsp;
        <label>메뉴명</label> <input type="text" id="coffee" name="coffee">
        &nbsp;&nbsp;
        <label>종류</label> <select id="kind" name="kind">
        <option value="ALL">전체</option>
        <option value="커피">커피</option>
        <option value="논커피">논커피</option>
        <option value="에이드">에이드</option>
      </select>

        &nbsp;&nbsp;<input type="submit" value="조회" style="width: 80px;height: 30px;font-weight: bold; font-size: medium">
        &nbsp;&nbsp;<button style="width: 80px;height: 30px;font-weight: bold; font-size: medium"><a href="/v2/menu_ins">등록</a></button>
        &nbsp;&nbsp;<button type="button" id="IdUpdateAll" style="width: 80px;height: 30px;font-weight: bold; font-size: medium" onclick="onModify()">가격수정</button>
        <!-- <a href="javascript:loadDocArray()">test</a> -->
        <!--        <label>CheckBox : </label><span id="idCheckBox"></span>-->
      </fieldset>

    </form>


  </div>

  <form name="formTable" id="IdFormTable" method="post" action="/v2/menu_updatePrice">
  <table class="table">
    <thead>
      <tr class="tr_td">
        <th>Chk</th>
        <th>커피No</th>
        <th>메뉴명</th>
        <th>종류</th>
        <th>가격</th>
        <th>등록일</th>
        <th>수정일</th>
        <th>수정</th>
        <th>삭제</th>
      </tr>
    </thead>


    <tbody id="t_body">
    <!--- 데이타 출력 부분 -->


    <tr th:each="prod : ${list}">
      <td><input type="checkbox" name="chkCoffeeNo" th:value="${prod.getNo()}"></td>
      <td th:text="${prod.getNo()}">커피No</td>
      <td th:text="${prod.getCoffee()}">메뉴명</td>
      <td th:text="${prod.getKind()}">종류</td>
      <td th:text="${numbers.formatInteger(prod.getPrice(),0,'COMMA')}">가격</td>
      <td th:text="${prod.getReg_day()}">등록일</td>
      <td th:text="${prod.getMod_day()}">수정일</td>
      <td><a th:href="@{/v2/menu_up(no=${prod.getNo()})}">수정</a></td>
      <td><a th:href="@{/v2/menu_del(no=${prod.getNo()})}">삭제</a></td>
    </tr>




    </tbody>
  </table>
    <input type="hidden" name="hidden_price">
  </form>/
</div>
<!--푸터위치-->
<th:block th:replace="/v2/comm/footer :: footerFragment"></th:block>

<script>
  /* 현재 시간 날짜에 적용시키기 */
  const now = new Date();	// 현재 날짜 및 시간
  const time7 = new Date(now.setDate(now.getDate() - 100));	// 기간 설정
  document.getElementById("start_date").value= time7.toISOString().slice(0,10);
  document.getElementById("end_date").value= new Date().toISOString().slice(0,10);

  function onModify(){
    let _price = prompt("가격을 입력하세요");

    if(_price == undefined){
      return;
    }else if(_price ==""){
      alert("가격을 입력하세요");
      onModify();
    }else if(_price != ""){
       let _frm = document.formTable;
       _frm.hidden_price.value = _price;
       _frm.submit();
    }
  }

</script>

</body>
</html>


