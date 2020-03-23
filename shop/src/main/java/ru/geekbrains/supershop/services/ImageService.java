package ru.geekbrains.supershop.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import ru.geekbrains.supershop.persistence.entities.Image;
import ru.geekbrains.supershop.persistence.repositories.ImageRepository;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.charset.MalformedInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class ImageService {

    private ImageRepository imageRepository;

    @Autowired
    public void setImageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    private List<String> getImagesForSpecificProduct(UUID id) {
        return imageRepository.obtainImageNameByProductId(id);
    }

    public List<BufferedImage> loadFilesAsResource(String id) throws IOException {
        List<BufferedImage> bufferedImages = new ArrayList<>();
        try {
            List<String> imageNames = getImagesForSpecificProduct(UUID.fromString(id));

            for (String imageName : imageNames) {
                Resource resource = new ClassPathResource("/static/images/" + imageName);
                if (resource.exists()) {
                    bufferedImages.add(ImageIO.read(resource.getFile()));
                } else {
                    throw new FileNotFoundException("File " + imageName + " not found!");
                }
            }
        } catch (MalformedInputException | FileNotFoundException ex) {
            return null;
        }
        return bufferedImages;
    }

    public List<Image> getImagesById (String id) {
        return imageRepository.findAllById(UUID.fromString(id));
    }

}