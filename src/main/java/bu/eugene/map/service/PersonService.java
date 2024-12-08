package bu.eugene.map.service;

import bu.eugene.map.model.Person;
import bu.eugene.map.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {

        private final PersonRepository personRepository;

        public Person findByUsername(String username) {
            return personRepository.findByUsername(username)
                    .orElseThrow(
                            () -> new EntityNotFoundException("пользвоатель не найден")
                    );
        }
}
