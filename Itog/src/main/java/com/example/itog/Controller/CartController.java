package com.example.itog.Controller;

import com.example.itog.model.Cart;
import com.example.itog.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
@RequestMapping("/cart")
public class CartController {

    private final CartRepository cartRepository;

    @Autowired
    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @GetMapping
    public String listCarts(Model model) {
        List<Cart> carts = cartRepository.findAll();
        model.addAttribute("carts", carts);
        return "cart/list";
    }

    @GetMapping("/add")
    public String showAddCartForm(Model model) {
        model.addAttribute("cart", new Cart());
        return "cart/add";
    }

    @PostMapping("/add")
    public String addCart(@Valid @ModelAttribute("cart") Cart cart, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "cart/add";
        }

        cartRepository.save(cart);
        return "redirect:/cart";
    }

    @GetMapping("/edit/{id}")
    public String showEditCartForm(@PathVariable("id") Long id, Model model) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid cart Id:" + id));

        model.addAttribute("cart", cart);
        return "cart/edit";
    }

    @PostMapping("/edit/{id}")
    public String editCart(@PathVariable("id") Long id, @Valid @ModelAttribute("cart") Cart cart, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            cart.setId(id);
            return "cart/edit";
        }

        cartRepository.save(cart);
        return "redirect:/cart";
    }

    @GetMapping("/delete/{id}")
    public String deleteCart(@PathVariable("id") Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid cart Id:" + id));

        cartRepository.delete(cart);
        return "redirect:/cart";
    }
}
