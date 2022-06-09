package com.ua.library.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private Integer year;
    private String genre;
    private Integer numberOfPage;

    private boolean isBusy = false;

    private boolean isExcluded = false;
    private String reasonExclude;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private List<LibraryLogEntity> logs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(Integer numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public boolean isExcluded() {
        return isExcluded;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public void setExcluded(boolean excluded) {
        isExcluded = excluded;
    }

    public String getReasonExclude() {
        return reasonExclude;
    }

    public void setReasonExclude(String reasonExclude) {
        this.reasonExclude = reasonExclude;
    }

    public List<LibraryLogEntity> getLogs() {
        return logs;
    }

    public void setLogs(List<LibraryLogEntity> logs) {
        this.logs = logs;
    }
}
