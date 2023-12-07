package com.example.itog.model;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Имя продукта не может быть пустым")
    @Size(max = 255, message = "Имя продукта должно содержать не более 255 символов")
    private String name;

    @NotBlank(message = "Описание продукта не может быть пустым")
    @Size(max = 1000, message = "Описание продукта должно содержать не более 1000 символов")
    private String description;

    @NotNull(message = "Цена продукта не может быть пустой")
    @PositiveOrZero(message = "Цена продукта должна быть положительной или равной нулю")
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "ord_id", referencedColumnName = "id")
    private Ord ord;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;



    public Product() {

    }

    public Product(Long id, String name, String description, double price, Category category, Ord ord, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.ord = ord;
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Ord getOrd() {
        return ord;
    }

    public void setOrd(Ord ord) {
        this.ord = ord;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}

