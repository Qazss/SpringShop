package ru.geekbrains.supershop.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import ru.geekbrains.supershop.persistence.entities.Image;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
    @Query(value = "SELECT i.name FROM image i INNER JOIN product p ON i.product_id = :id", nativeQuery = true)
    List<String> obtainImageNameByProductId(@Param("id") UUID id);

    List<Image> findAllById(UUID id);
}