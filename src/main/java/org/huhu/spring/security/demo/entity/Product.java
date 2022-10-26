package org.huhu.spring.security.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.huhu.spring.security.demo.constant.Currency;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity(name = "t_product")
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String name;

    private String price;

    @Enumerated(value = STRING)
    private Currency currency;

}
