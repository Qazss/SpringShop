package ru.geekbrains.supershop.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.geekbrains.supershop.controllers.utils.UUIDchecker;
import ru.geekbrains.supershop.exceptions.ProductNotFoundException;
import ru.geekbrains.supershop.services.ImageService;
import ru.geekbrains.supershop.services.ProductService;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.List;
import java.util.UUID;



@Controller
@RequestMapping("/products")
public class ProductController {
    private final Logger logger = LogManager.getLogger(ProductController.class);

    private ImageService imageService;
    private ProductService productService;

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getOneProduct(Model model, @PathVariable String id) throws ProductNotFoundException {
        try {
            UUIDchecker.check(id);
        } catch (IllegalArgumentException e) {
            logger.error(String.format("UUID [%s] имеет некорректное значение", id));
        }
        model.addAttribute("product", productService.findOneById(UUID.fromString(id)));
        return "product";
    }

    @GetMapping(value = "/images/{id}")
    public String getImage(@PathVariable String id, Model model) {

        // TODO ДЗ - сделать поддержку множества картинок

        try {
            List<BufferedImage> images = imageService.loadFilesAsResource(id);

            for (BufferedImage image : images) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(imageService.loadFilesAsResource(id).get(0), "png", byteArrayOutputStream);
                model.addAttribute("images", image);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return "index";
    }

}