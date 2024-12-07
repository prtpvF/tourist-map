package bu.eugene.map.util;

import bu.eugene.map.dto.PersonDto;
import bu.eugene.map.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Dto2EntityConverter {

    private final ModelMapper modelMapper;

    public Person convertPersonDto2PersonEntity(PersonDto personDto) {
        Person person = new Person();
        modelMapper.map(personDto, person);
        return person;
    }
}
