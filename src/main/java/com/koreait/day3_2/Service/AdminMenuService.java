package com.koreait.day3_2.Service;

import com.koreait.day3_2.model.front.AdminMenu;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminMenuService {
    public List<AdminMenu> getAdminMenu(){
        return Arrays.asList(
                AdminMenu.builder().title("고객관리").url("/pages/user").img("grade").code("user").build(),
                AdminMenu.builder().title("관리자관리").url("/pages/adminuser").code("adminuser").img("perm_identity").build(),
                AdminMenu.builder().title("카테고리관리").url("/pages/category").code("category").img("shopping_cart").build(),
                AdminMenu.builder().title("아이템관리").url("/pages/item").code("item").img("shopping_bag").build(),
                AdminMenu.builder().title("구매정보관리").url("/pages/ordergroup").code("ordergroup").img("receipt").build(),
                AdminMenu.builder().title("업체관리").url("/pages/partner").code("partner").img("article").build()
        );
    }




}
