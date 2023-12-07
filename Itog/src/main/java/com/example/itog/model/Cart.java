package com.example.itog.model;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(value = 1, message = "Количество должно быть не меньше 1")
    @Max(value = 100, message = "Количество не может превышать 100")
    private int quantity;
    @NotBlank(message = "имя не может быть пустым")
    @Size(max = 50, message = "имя не должен превышать 50 символов")
    private String name;




    @OneToMany(mappedBy = "cart")
    private List<Ord> ords;



    public Cart() {

    }


    public Cart(Long id, int quantity, String name, List<Ord> ords) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.ords = ords;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Ord> getOrds() {
        return ords;
    }

    public void setOrds(List<Ord> ords) {
        this.ords = ords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
