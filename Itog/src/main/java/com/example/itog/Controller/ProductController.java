package com.example.itog.Controller;

import com.example.itog.model.Category;
import com.example.itog.model.Ord;
import com.example.itog.model.Product;
import com.example.itog.repositories.CategoryRepository;
import com.example.itog.repositories.OrdRepository;
import com.example.itog.repositories.ProductRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OrdRepository ordRepository;


    @Autowired
    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository, OrdRepository ordRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.ordRepository = ordRepository;
    }

    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "product/list";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        List<Category> categories = categoryRepository.findAll();
        List<Ord> ords = ordRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("ords", ords);
        model.addAttribute("product", new Product());

        return "product/add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product/add";
        }

        productRepository.save(product);
        return "redirect:/product";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        List<Category> categories = categoryRepository.findAll();
        List<Ord> ords = ordRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("ords", ords);
        model.addAttribute("product", product);
        return "product/edit";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, @Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            product.setId(id);
            return "product/edit";
        }

        productRepository.save(product);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

        productRepository.delete(product);
        return "redirect:/product";
    }
}
