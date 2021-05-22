package ru.sstu.sharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sstu.sharing.domain.entities.Order;
import ru.sstu.sharing.exceptions.ProductDoesNotExist;
import ru.sstu.sharing.exceptions.UserDoesNotExist;
import ru.sstu.sharing.services.OrderService;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("basket")
    public String getBasket(Model model, Authentication authentication) {
        try {
            model.addAttribute("currentSection", "basket");
            model.addAttribute("order", this.orderService.getBasket(authentication));
            return "order/basket";
        } catch (UserDoesNotExist e) {
            return "redirect:/error";
        }
    }

    @RequestMapping(value = "basket/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String addInBasket(String productId, Authentication authentication) {
        try {
            this.orderService.addInBasket(Long.parseLong(productId), authentication);
            Order order = this.orderService.createOrder(authentication);
            return "confirm";
        } catch (UserDoesNotExist | ProductDoesNotExist | NumberFormatException e) {
            System.out.println(e);
            return "redirect:/error";
        }
    }

    @RequestMapping(value = "/basket/changeQuantity", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Boolean changeQuantity(String quantity, String productId, Authentication authentication) {
        try {
            this.orderService.changeQuantity(Integer.parseInt(quantity), Long.parseLong(productId), authentication);
            return true;
        } catch (UserDoesNotExist | ProductDoesNotExist | NumberFormatException e) {
            return false;
        }
    }

    @GetMapping("/basket/{productId}")
    public String deleteProductBasket(@PathVariable String productId, @RequestParam String delete, Model model, Authentication authentication) {
        try {
            Long id = Long.parseLong(productId);
            this.orderService.deleteProduct(id, authentication);
        } catch (UserDoesNotExist e) {
            return "redirect:/error";
        } catch (ProductDoesNotExist | NumberFormatException e) {
        }
        return "redirect:/basket";
    }

    @GetMapping("/basket/order")
    public String createOrder(Model model, Authentication authentication) {
/*        try {
            model.addAttribute("currentSection", "order");
            if (order != null) {
                model.addAttribute("message", "Заказ подтвержден!");
            } else {
                model.addAttribute("message", "Заказ не подтвержден! Проверьте товары их их колличество");
            }
            return "order/order";
        } catch (UserDoesNotExist e) {*/
            return "redirect:/error";
/*
        }*/
    }
}
