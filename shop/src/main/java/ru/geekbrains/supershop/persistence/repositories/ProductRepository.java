package ru.geekbrains.supershop.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import ru.geekbrains.supershop.persistence.entities.Product;
import ru.geekbrains.supershop.persistence.entities.enums.AvailableCategory;
import ru.geekbrains.supershop.persistence.entities.enums.ProductCategory;

import java.util.List;
import java.util.UUID;


@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAll();
    List<Product> findAllByCategory(ProductCategory category);
    List<Product> findAllByCategoryAndAvailable(ProductCategory category, AvailableCategory availableCategory);
}