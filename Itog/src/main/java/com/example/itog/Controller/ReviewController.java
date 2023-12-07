package com.example.itog.Controller;

import com.example.itog.model.Product;
import com.example.itog.model.Review;
import com.example.itog.repositories.ProductRepository;
import com.example.itog.repositories.ReviewRepository;
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
@RequestMapping("/review")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;


    @Autowired
    public ReviewController(ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listReviews(Model model) {
        List<Review> reviews = reviewRepository.findAll();
        model.addAttribute("reviews", reviews);
        return "review/list";
    }

    @GetMapping("/add")
    public String showAddReviewForm(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("review", new Review());
        return "review/add";
    }

    @PostMapping("/add")
    public String addReview(@Valid @ModelAttribute("review") Review review, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "review/add";
        }

        reviewRepository.save(review);
        return "redirect:/review";
    }

    @GetMapping("/edit/{id}")
    public String showEditReviewForm(@PathVariable("id") Long id, Model model) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid review Id:" + id));
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("review", review);
        return "review/edit";
    }

    @PostMapping("/edit/{id}")
    public String editReview(@PathVariable("id") Long id, @Valid @ModelAttribute("review") Review review, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            review.setId(id);
            return "review/edit";
        }

        reviewRepository.save(review);
        return "redirect:/review";
    }

    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable("id") Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid review Id:" + id));

        reviewRepository.delete(review);
        return "redirect:/review";
    }
}