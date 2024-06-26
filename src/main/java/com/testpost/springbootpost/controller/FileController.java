package com.testpost.springbootpost.controller;

import com.testpost.springbootpost.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        String url=fileService.upload(file);
        return url;
    }

    @GetMapping(value="{fileName}",produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public org.springframework.core.io.Resource getImage(@PathVariable("fileName") String fileName ){
        Resource resource=fileService.getImage(fileName);
        return resource;
    }
}
