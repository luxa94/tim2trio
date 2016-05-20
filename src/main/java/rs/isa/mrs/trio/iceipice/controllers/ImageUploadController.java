package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rs.isa.mrs.trio.iceipice.model.Image;
import rs.isa.mrs.trio.iceipice.repository.ImageRepository;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Sandra on 17.4.2016.
 */
@RestController
@RequestMapping("/api/images")
public class ImageUploadController {
    @Autowired
    ServletContext servletContext;

    @Autowired
    ImageRepository imageRepository;

    @RequestMapping(value = "/new/{type}", method = RequestMethod.POST)
    public ResponseEntity addNew(@PathVariable String type, @RequestParam MultipartFile file) {
        Image i = new Image();
        i.setUrl("temp");
        i = imageRepository.save(i);

        try {
            final String baseUrl = servletContext.getRealPath("/images");
            final String newUrl = String.format("%s/%s/%d.jpg", baseUrl, type, i.getId());
            final String imageUrl = String.format("/images/%s/%d.jpg", type, i.getId());

            i.setUrl(imageUrl);
            imageRepository.save(i);

            final File newImage = new File(newUrl);
            if (!newImage.exists()) {
                newImage.createNewFile();
            }
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newImage));
            stream.write(bytes);
            stream.close();
            return new ResponseEntity<>(imageUrl, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
}
