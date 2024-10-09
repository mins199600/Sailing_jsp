package com.boot.sailing_jsp.v2.controller;

import com.boot.sailing_jsp.v2.service.MenuSvcV2;
import com.boot.sailing_jsp.v2.vo.Coffee_menu;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/v2")
@Log4j2
public class MenuConV2 {

    @Autowired
    MenuSvcV2 menuSvc;

    @RequestMapping("/menu")
    public String doMenu(Model model){

        // Data 만들기 , List , Map
        List<Coffee_menu> list = menuSvc.doList();

        log.info("============================");
        log.info(list);
        // Data 송부
        model.addAttribute("list", list);
        model.addAttribute("hello", " ========== MenuCon V2 ======== ");

        return "/v2/menu/menu";
    }

    @GetMapping("/menu_ins")
    public String doInsert(){
        return "/v2/menu/menu_ins";
    }

    @PostMapping("/menu_ins")
    public String doInsertPost(@ModelAttribute Coffee_menu coffeeMenu
    ){
        log.info("==========================================================");
        log.info(coffeeMenu);
        int i = menuSvc.doInsert(coffeeMenu);

        return "redirect:/v2/menu";
    }

    @GetMapping("/menu_del")
    public String doDelete(@RequestParam("no") String strNo){

        log.info("strNO : "+strNo);
        int i = menuSvc.doDelete(strNo);

        return "redirect:/v2/menu";
    }

    @GetMapping("/menu_up")
    public String doUpdate(Model model,
                           @RequestParam("no") String strNo){

        Map<String , Object> map = menuSvc.doListOne(strNo);
        model.addAttribute("map", map);

        return "/v2/menu/menu_up";
    }

    @PostMapping("/menu_up")
    public String doUpdatePost(Coffee_menu coffeeMenu
    ){
        int i = menuSvc.doUpdate(coffeeMenu);

        return "redirect:/v2/menu";
    }

    /* 조회하기 */
    @PostMapping("/menu_search")
    public String doSearch( @RequestParam("start_date") String strStartDate,
                            @RequestParam("end_date") String strEndDate,
                            @RequestParam(value = "coffee", defaultValue = "ALL") String strCoffee,
                            @RequestParam("kind") String strKind,
                            Model model

    ){

        log.info("strStartDate :"+strStartDate);

        List<Coffee_menu> list = menuSvc.doSerch(strStartDate,strEndDate, strCoffee,strKind );

        model.addAttribute("list", list);

        return "/v2/menu/menu";
    }

    /* 가격 수정 - 다중 체크 */
    @PostMapping("/menu_updatePrice")
    public String doUpdatePrice(@RequestParam("chkCoffeeNo") List<String> chkList,
                                @RequestParam("hidden_price") String strPrice,
                                Model model
    ){

        String strReturn = "redirect:/v2/menu";

        try {
            if (chkList != null) {
//            for(String strNo : chkList){
//                 int int1 = menuSvc.doInsertLog(strNo, strPrice);
//                 int int2 = menuSvc.doUpdatePrice(strNo, strPrice);
//            }

                int int1 = menuSvc.doUpdateInsert(chkList, strPrice);
                //int int1 = menuSvc.doInsertLogOne(chkList, strPrice);
                //int int2 = menuSvc.doUpdatePriceOne(chkList, strPrice);
            }
        }catch (Exception e){
            log.info("Error Error Error Error : "+e.getMessage());

            model.addAttribute("em", e.getMessage());
            strReturn = "/v2/comm/ErrorPage";
        }

        return strReturn;

    }

}
