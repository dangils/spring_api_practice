package com.koreait.day3_2.controller.page;

import com.koreait.day3_2.Service.AdminMenuService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages") // http://127.0.0.1:8080/pages  // 페이지 url 호출
public class PageController {
    @Autowired   // 페이지 저장할 것에 대한 주입
    private AdminMenuService adminMenuService;  //get, set으로 페이지 주입

    @RequestMapping(path={""})
    public ModelAndView index(){    // 화면 송출하는 메서드 (index) 임의로 정한 메서드명, index 메서드 자동 호출
        return new ModelAndView("/pages/index") //페이지를 띄우며 아래 자료등을 넘겨줌(리스트,특정 변수)
                .addObject("menuList",adminMenuService.getAdminMenu())
                .addObject("code","main");
    }


    @RequestMapping(path = {"/user"})
    public ModelAndView user() {
        return new ModelAndView("/pages/user")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "user");
    }

    @RequestMapping("/adminuser")
    public ModelAndView admin() {
        return new ModelAndView("/pages/adminuser")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "adminuser");
    }

    @RequestMapping("/category")
    public ModelAndView category() {
        return new ModelAndView("/pages/category")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "category");
    }
    @RequestMapping("/item")
    public ModelAndView item() {
        return new ModelAndView("/pages/item")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "item");
    }
    @RequestMapping("/ordergroup")
    public ModelAndView ordergroup() {
        return new ModelAndView("/pages/ordergroup")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "ordergroup");
    }

    @RequestMapping(path = {"/partner"})
    public ModelAndView partner() {
        return new ModelAndView("/pages/partner")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "partner");
    }

}

