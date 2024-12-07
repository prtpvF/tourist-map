package bu.eugene.map.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PersonDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String role;
    private String username;
    private String password;
    private String pathToProfileImage;
    private MultipartFile profileImage;
    private List<String> pathToFiles = new ArrayList<>();
}
