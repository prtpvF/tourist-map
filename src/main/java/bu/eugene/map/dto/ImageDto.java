package bu.eugene.map.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImageDto {

    private Integer id;

    private String authorUsername;

    private String pathToFile;

    private MultipartFile image;

    private PlaceDto place;

    private List<LikeDto> likes = new ArrayList<>();

    private List<CommentDto> comments = new ArrayList<>();
}
