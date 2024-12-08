package bu.eugene.map.util;

import bu.eugene.map.dto.ImageDto;
import bu.eugene.map.dto.PlaceDto;
import bu.eugene.map.model.ImageEntity;
import bu.eugene.map.model.Place;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
@RequiredArgsConstructor
public class Entity2DtoConverter {

    private final ModelMapper modelMapper;

    public PlaceDto convertPlaceEntity2Dto(Place place) {
        PlaceDto placeDto = modelMapper.map(place, PlaceDto.class);
        return placeDto;
    }

    public ImageDto convertImageEntity2Dto(ImageEntity image) {
       return modelMapper.map(image, ImageDto.class);
    }
}
