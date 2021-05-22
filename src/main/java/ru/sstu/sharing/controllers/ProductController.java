package ru.sstu.sharing.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sstu.sharing.domain.entities.Product;
import ru.sstu.sharing.services.ImageProductService;
import ru.sstu.sharing.exceptions.ProductDoesNotExist;
import ru.sstu.sharing.services.impl.ProductServiceImpl;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ImageProductService imageProductService;

    @GetMapping("/{productId}")
    public String getInfoAboutProduct(@PathVariable String  productId, Model model) {
        try {
            long id = Long.parseLong(productId);
            Optional<Product> product = this.productService.getInfoAboutProductForBigPageById(id);
            if (product.isPresent()) {
                model.addAttribute("product", product.get());
                model.addAttribute("numberImages", this.imageProductService.getNumberImages(id));
                return "catalog/product";
            } else {
                throw new ProductDoesNotExist();
            }
        } catch (NumberFormatException | ProductDoesNotExist e) {
            return "redirect:/";
        }
    }
}
