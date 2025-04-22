package com.url.shortner.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class UrlMapping {
    private Long id;
    private LocalDateTime createdDate;
    private String originalUrl;
    private String shortUrl;
    private int clickCount =0;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany
    private List<ClickEvent> clickEvents;


}
