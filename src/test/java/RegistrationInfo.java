import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationInfo {
    String login;
    String password;
    String status;
}
