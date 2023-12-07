package com.example.itog.Controller;

import com.example.itog.model.Address;
import com.example.itog.model.Cart;
import com.example.itog.model.Ord;

import com.example.itog.repositories.AddressRepository;
import com.example.itog.repositories.CartRepository;
import com.example.itog.repositories.OrdRepository;
import com.example.itog.repositories.PaymentRepository;
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
@RequestMapping("/ord")
public class OrdController {

    private final OrdRepository ordRepository;
    private final AddressRepository addressRepository;
    private final CartRepository cartRepository;


    @Autowired
    public OrdController(OrdRepository ordRepository, AddressRepository addressRepository, CartRepository cartRepository) {
        this.ordRepository = ordRepository;
        this.addressRepository = addressRepository;
        this.cartRepository = cartRepository;
    }

    @GetMapping
    public String listOrders(Model model) {
        List<Ord> orders = ordRepository.findAll();
        model.addAttribute("orders", orders);
        return "ord/list";
    }

    @GetMapping("/add")
    public String showAddOrderForm(Model model) {
        List<Address> addresses = addressRepository.findAll();
        List<Cart> carts = cartRepository.findAll();
        model.addAttribute("addresses", addresses);
        model.addAttribute("carts", carts);
        model.addAttribute("order", new Ord());
        return "ord/add";
    }


    @PostMapping("/add")
    public String addOrder(@Valid @ModelAttribute("order") Ord order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ord/add";
        }

        ordRepository.save(order);
        return "redirect:/ord";
    }

    @GetMapping("/edit/{id}")
    public String showEditOrderForm(@PathVariable("id") Long id, Model model) {
        Ord order = ordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        List<Address> address = addressRepository.findAll();
        List<Cart> cart = cartRepository.findAll();
        model.addAttribute("address", address);
        model.addAttribute("cart", cart);
        model.addAttribute("order", order);
        return "ord/edit";
    }

    @PostMapping("/edit/{id}")
    public String editOrder(@PathVariable("id") Long id, @Valid @ModelAttribute("order") Ord order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            order.setId(id);
            return "ord/edit";
        }

        ordRepository.save(order);
        return "redirect:/ord";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") Long id) {
        Ord order = ordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));

        ordRepository.delete(order);
        return "redirect:/ord";
    }
}