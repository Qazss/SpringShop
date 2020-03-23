package ru.geekbrains.supershop.persistence.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import ru.geekbrains.supershop.persistence.entities.utils.PersistableEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Image extends PersistableEntity implements Serializable {

    private static final long SUID = 1L;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

}