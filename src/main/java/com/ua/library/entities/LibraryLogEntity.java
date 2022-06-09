package com.ua.library.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "logs")
public class LibraryLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp dateTaking;
    private Timestamp dateShouldReturn;
    private Timestamp dateReturning;

    @ManyToOne
    private BookEntity book;
    @ManyToOne
    private CustomerEntity customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDateTaking() {
        return dateTaking;
    }

    public void setDateTaking(Timestamp dateTaking) {
        this.dateTaking = dateTaking;
    }

    public Timestamp getDateShouldReturn() {
        return dateShouldReturn;
    }

    public void setDateShouldReturn(Timestamp dateShouldReturn) {
        this.dateShouldReturn = dateShouldReturn;
    }

    public Timestamp getDateReturning() {
        return dateReturning;
    }

    public void setDateReturning(Timestamp dateReturning) {
        this.dateReturning = dateReturning;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
