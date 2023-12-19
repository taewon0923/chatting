package test.chat.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import test.chat.domain.User;
import test.chat.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public String join(String userName, String password){
        userRepository.findByUserName(userName)
                .ifPresent(user -> {
                    throw new RuntimeException(userName+"는 이미 있습니다");
                });

        User user = User.builder()
                .userName(userName)
                .password(encoder.encode(password))
                .build();
        userRepository.save(user);
        return "success";
    }
}
