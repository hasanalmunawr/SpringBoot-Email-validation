package hasanalmunawr.Dev.Emailvalidation.Entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Token {

    @Id
    @GeneratedValue
    private Integer id;
    private String token;
    private LocalDateTime createdDate;
    private LocalDateTime expiryDate;
    private LocalDateTime validatedDate;


    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;
}
