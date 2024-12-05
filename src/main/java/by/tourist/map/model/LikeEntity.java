package by.tourist.map.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "like")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikeEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @CreationTimestamp
        private LocalDateTime createdAt;

        @ManyToOne
        @JoinColumn(name = "person_id")
        private Person author;

        @ManyToOne
        @JoinColumn(name = "image_id")
        private ImageEntity image;

        private int rate;
}
