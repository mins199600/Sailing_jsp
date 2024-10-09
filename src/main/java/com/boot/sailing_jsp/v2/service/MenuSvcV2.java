package com.boot.sailing_jsp.v2.service;

import com.boot.sailing_jsp.comm.Bootlog;
import com.boot.sailing_jsp.comm.MyExceptionRuntime;
import com.boot.sailing_jsp.v2.dao.MenuDaoV2;
import com.boot.sailing_jsp.v2.vo.Coffee_menu;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class MenuSvcV2 {

    @Autowired
    MenuDaoV2 menuDao;

    @Autowired
    Bootlog bootlog;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    TransactionDefinition definition;

    @Autowired
    TransactionTemplate transactionTemplate;



    public MenuSvcV2() {
        log.info("================ MenuSvc , 생성자 ===================");
    }

    public List<Coffee_menu> doList(){

        // Data 만들기 , List , Map
        List<Coffee_menu> list = menuDao.doList();

        log.info(list);

        return list;

    }
    public List<Map<String, Object>> doListOld(){

        // Data 만들기 , List , Map
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        map.put("No", "1");
        map.put("name", "아이스아메");
        map.put("kind", "커피");
        map.put("price", "5,000");
        map.put("reg_day", "2020.10.29");
        map.put("mod_day", "2021.10.29");
        list.add(map);

        Map<String, Object> map2 = new HashMap<>();

        map2.put("No", "2");
        map2.put("name", "카푸치노");
        map2.put("kind", "커피");
        map2.put("price", "6,000");
        map2.put("reg_day", "2020.10.29");
        map2.put("mod_day", "2021.10.29");
        list.add(map2);

        Map<String, Object> map3 = new HashMap<>();

        map3.put("No", "3");
        map3.put("name", "카페라떼");
        map3.put("kind", "커피");
        map3.put("price", "6,500");
        map3.put("reg_day", "2020.10.29");
        map3.put("mod_day", "2021.10.29");
        list.add(map3);

        log.info(list);

        return list;
    }


    public int doInsert(Coffee_menu coffeeMenu) {
        int i = menuDao.doInsert(coffeeMenu);
        return i;

    }

    public int doDelete(String strNo) {
        int i = menuDao.doDelete(strNo);
        return i;
    }

    // One row 조회
    public Map<String, Object> doListOne(String strNo) {

        Map<String , Object> map = menuDao.doListOne(strNo);

        return map;
    }

    // Update
    public int doUpdate(Coffee_menu coffeeMenu) {
        int i = menuDao.doUpdate(coffeeMenu);
        return i;
    }

    public List<Coffee_menu> doSerch(String strStartDate, String strEndDate, String strCoffee, String strKind) {
        List<Coffee_menu> list = menuDao.doSerch(strStartDate,strEndDate, strCoffee,strKind );
        return list;
    }


    /* 가격변경 */
    public int doUpdatePrice(String strNo, String strPrice) {
        int int2 = menuDao.doUpdatePrice(strNo, strPrice);
        return int2;
    }

    /* 가격 로그입력 */
    public int doInsertLog(String strNo, String strPrice) {
        int int1 = menuDao.doInsertLog(strNo, strPrice);
        return int1;
    }

    /* 로그 원 쿼리 */
    public int doInsertLogOne(List<String> chkList, String strPrice) {
        int int1 = menuDao.doInsertLogOne(chkList, strPrice);
        return int1;
    }

    /* Update 원 쿼리 */
    public int doUpdatePriceOne(List<String> chkList, String strPrice) {
        int int2 = menuDao.doUpdatePriceOne(chkList, strPrice);
        return int2;
    }

    public int doUpdateInsert(List<String> chkList, String strPrice) throws RuntimeException  {

        int rI=0;
        try {

            log.info("================== return  ===========================");
            rI = transactionTemplate.execute(status -> {
                        int int2 = menuDao.doUpdatePriceOne(chkList, strPrice);
                        //status.setRollbackOnly();
                        return int2;
                  });

            log.info("================== no return  ===========================");
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    int int1 = menuDao.doInsertLogOne(chkList, strPrice);
                    //status.setRollbackOnly();
                }
            });

        }catch (Exception e){
            throw new MyExceptionRuntime(e.getMessage(), getClass().getName());
        }finally {
            log.info("===================== Finally =================");
            TransactionStatus status3 = transactionManager.getTransaction(definition);
            bootlog.doBootLog(getClass().getName());
            transactionManager.commit(status3);
        }

        return rI;

    }
    /*
    //@Transactional(rollbackFor = Exception.class)
    public int doUpdateInsert(List<String> chkList, String strPrice) throws RuntimeException  {

        int int1=0;
        try {
            TransactionStatus status = transactionManager.getTransaction(definition);
            int int2 = menuDao.doUpdatePriceOne(chkList, strPrice);
            transactionManager.rollback(status);

            TransactionStatus status2 = transactionManager.getTransaction(definition);
            int1 = menuDao.doInsertLogOne(chkList, strPrice);
            transactionManager.rollback(status2);

        }catch (Exception e){
            throw new MyExceptionRuntime(e.getMessage(), getClass().getName());
        }finally {
            log.info("===================== Finally =================");
            TransactionStatus status3 = transactionManager.getTransaction(definition);
            bootlog.doBootLog(getClass().getName());
            transactionManager.rollback(status3);
        }

        return int1;

    }
    */

    /*
    //@Transactional(rollbackFor = Exception.class)
    @Transactional(propagation = Propagation.REQUIRED)
    public int doUpdateInsert(List<String> chkList, String strPrice) throws RuntimeException  {

        int int1=0;
        try {

            int int2 = menuDao.doUpdatePriceOne(chkList, strPrice);

            int1 = menuDao.doInsertLogOne(chkList, strPrice);

            int numerator = 1;
            int denominator = 0;
            int result = numerator / denominator;

        }catch (Exception e){
            throw new MyExceptionRuntime(e.getMessage(), getClass().getName());
        }finally {
            log.info("===================== Finally =================");
            bootlog.doBootLog(getClass().getName());
        }

        return int1;

           // Checked Exception
                File file = new File("not_existing_file.txt");
                   FileInputStream stream
                         = new FileInputStream(file);

           /// Unchecked Exception , -> ArithmeticException
            int numerator = 1;
            int denominator = 0;
            int result = numerator / denominator;

    }
    */
}
