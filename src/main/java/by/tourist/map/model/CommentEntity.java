package by.tourist.map.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@DynamicUpdate
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Length(min = 5, max = 255, message = "коменатрий должен быть длинее 4 символов и короче 256")
        private String text;

        @ManyToOne
        @JoinColumn(name = "author_id")
        private Person author;

        @ManyToOne
        @JoinColumn(name = "image_id")
        private ImageEntity image;

        @CreationTimestamp
        private LocalDateTime createdAt;
}