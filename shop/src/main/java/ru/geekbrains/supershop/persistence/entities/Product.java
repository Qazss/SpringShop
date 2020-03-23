package ru.geekbrains.supershop.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import ru.geekbrains.supershop.persistence.entities.enums.AvailableCategory;
import ru.geekbrains.supershop.persistence.entities.enums.ProductCategory;
import ru.geekbrains.supershop.persistence.entities.utils.PersistableEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends PersistableEntity {

    private String title;

    private String description;

    private Date added;

    private Double price;

    @Enumerated(EnumType.ORDINAL)
    private AvailableCategory available;

    @Enumerated(EnumType.ORDINAL)
    private ProductCategory category;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

}