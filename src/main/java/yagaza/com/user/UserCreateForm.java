package yagaza.com.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
public class UserCreateForm {
    @Size(min = 3, max = 15)
    @NotEmpty(message = "사용자ID는 필수항목입니다.")
    private String id;

    @NotEmpty(message = "이름은 필수항목입니다.")
    private String name;

    @Size(min = 3, max = 10)
    @NotEmpty(message = "별명은 필수항목입니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String password2;

    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email
    private String email;

    @NotEmpty(message = "전화번호는 필수항목입니다.")
    private String phoneNumber;

}
