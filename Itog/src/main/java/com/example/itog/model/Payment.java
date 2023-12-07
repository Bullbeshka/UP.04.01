package com.example.itog.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Сумма платежа не может быть пустой")
    @Positive(message = "Сумма платежа должна быть положительной")
    private double amount;

    @NotNull(message = "Дата платежа не может быть пустой")
    private java.sql.Date paymentDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ord_id", referencedColumnName = "id")
    private Ord ord;




    public Payment() {

    }

    public Payment(Long id, double amount, java.sql.Date paymentDate, Ord ord) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.ord = ord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(java.sql.Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Ord getOrd() {
        return ord;
    }

    public void setOrd(Ord ord) {
        this.ord = ord;
    }
}

