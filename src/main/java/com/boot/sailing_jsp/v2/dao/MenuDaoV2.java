package com.boot.sailing_jsp.v2.dao;

import com.boot.sailing_jsp.v2.vo.Coffee_menu;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface MenuDaoV2 {
    List<Coffee_menu> doList();

    int doInsert(Coffee_menu coffeeMenu);

    int doDelete(String strNo);

    // One row 조회
    Map<String, Object> doListOne(String strNo);

    // Update
    int doUpdate(Coffee_menu coffeeMenu);

    /* 검색 */
    List<Coffee_menu> doSerch(String strStartDate, String strEndDate, String strCoffee, String strKind);

    /* 가격 변경 */
    int doUpdatePrice(String strNo, String strPrice);

    /* 가격 로그입력 */
    int doInsertLog(String strNo, String strPrice);

    /* 로그 원 쿼리 */
    int doInsertLogOne(List<String> chkList, String strPrice);

    /* Update 원 쿼리 */
    int doUpdatePriceOne(List<String> chkList, String strPrice);

    int doBootLog(String strClass);
}
