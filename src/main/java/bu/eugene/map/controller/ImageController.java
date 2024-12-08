package bu.eugene.map.controller;

import bu.eugene.map.dto.ImageDto;
import bu.eugene.map.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/")
    public ImageDto save(@ModelAttribute ImageDto imageDto, @RequestParam("placeId") Integer placeId) {
        return imageService.saveImage(imageDto, placeId);
    }
}