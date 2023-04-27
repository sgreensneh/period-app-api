package africa.semicolon.periodapp.dto.response;

import africa.semicolon.periodapp.data.models.Flow;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;

    private String fullName;

    private String username;

    private Flow flow;

    private LocalDateTime createdAt;
}
