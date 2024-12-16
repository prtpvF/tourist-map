package bu.eugene.map.service;

import bu.eugene.map.dto.ImageDto;
import bu.eugene.map.exception.FileExtensionException;
import bu.eugene.map.exception.FileUploadException;
import bu.eugene.map.jwt.JwtUtil;
import bu.eugene.map.model.ImageEntity;
import bu.eugene.map.repository.ImageRepository;
import bu.eugene.map.util.Dto2EntityConverter;
import bu.eugene.map.util.Entity2DtoConverter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {

        private final ImageRepository imageRepository;
        private final Dto2EntityConverter dto2EntityConverter;
        private final Entity2DtoConverter entity2DtoConverter;
        private final PlaceService placeService;
        private final JwtUtil jwtUtil;
        private final PersonService personService;
        private final String  UPLOAD_DIR = "/Users/mihail/Downloads/map-2/uploads";

        private final String[] IMAGE_EXTENSIONS = new String[]{"jpg", "jpeg", "png"};

        public ImageDto saveImage(ImageDto imageDto, String placeId, String token) {
                ImageEntity image = dto2EntityConverter.convertImageDto2ImageEntity(imageDto);
                image.setPerson(personService.findByUsername(jwtUtil.validateTokenAndRetrieveClaim(token.substring(7))));
                image.setPathToFile(saveImagesLocal(imageDto.getImage()));
                image.setPlace(placeService.findById(Integer.valueOf(placeId.replace("[object Object],", ""))));
                return entity2DtoConverter.convertImageEntity2Dto(imageRepository.save(image));
        }

        public ResponseEntity<?> deletePhoto(String token, Integer imageId) {
                ImageEntity image = imageRepository.findById(imageId).orElseThrow(
                        () -> new EntityNotFoundException("Image not found")
                );

                if (image.getPerson().getUsername().equals(
                        jwtUtil.validateTokenAndRetrieveClaim(token.substring(7)))) {
                        imageRepository.delete(image);
                } else {
                        throw new AccessDeniedException("You can delete only your images");
                }
                return ResponseEntity.ok().build();
        }

        public String saveImagesLocal(MultipartFile file) {

                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) {
                        if (uploadDir.mkdirs()) {
                                log.info("Директория для загрузки создана: {}", UPLOAD_DIR);
                        } else {
                                log.error("Не удалось создать директорию: {}", UPLOAD_DIR);
                                throw new FileUploadException("Ошибка при создании директории для загрузки");
                        }
                }

                        log.info("start saving image");
                        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
                        String baseName = FilenameUtils.getBaseName(file.getOriginalFilename()).replaceAll(" ", "_");

                        if (!Arrays.stream(IMAGE_EXTENSIONS).anyMatch(extension::equalsIgnoreCase)) {
                                throw new FileExtensionException("неверный формат файла, должен быть только: jpg, jpeg или png");
                        }

                        String uniqueFilename = createUniqueFilename(baseName, extension);
                        File destinationFile = new File(UPLOAD_DIR, uniqueFilename);

                        try {
                                file.transferTo(destinationFile);
                        } catch (IOException e) {
                                log.error("smth went wrong while file saving");
                                throw new FileUploadException("ошибка загрузки файла");
                        }

                return "/uploads/" + uniqueFilename;
        }


        private  String createUniqueFilename(String baseName, String extension) {

                String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
                return baseName + "_" + timestamp + "." + extension;
        }

}