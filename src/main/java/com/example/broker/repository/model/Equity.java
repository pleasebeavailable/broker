package com.example.broker.repository.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "equities")
public class Equity {

    public Equity() {
    }

    public Equity(String sticker, Number price) {
        this.sticker = sticker;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "sticker")
    private String sticker;

    @Column(name = "price")
    private Number price;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSticker() {
        return sticker;
    }

    public void setSticker(String sticker) {
        this.sticker = sticker;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

}
