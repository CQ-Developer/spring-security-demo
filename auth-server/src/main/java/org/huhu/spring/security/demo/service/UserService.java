package org.huhu.spring.security.demo.service;

import org.huhu.spring.security.demo.entity.otp;
import org.huhu.spring.security.demo.entity.User;
import org.huhu.spring.security.demo.repository.OptJpaRepository;
import org.huhu.spring.security.demo.repository.UserJpaRepository;
import org.huhu.spring.security.demo.utils.GenerateCodeUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserJpaRepository userJpaRepository;

    private final OptJpaRepository otpJpaRepository;

    public UserService(PasswordEncoder passwordEncoder, UserJpaRepository userJpaRepository, OptJpaRepository otpJpaRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userJpaRepository = userJpaRepository;
        this.otpJpaRepository = otpJpaRepository;
    }

    public void addUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userJpaRepository.save(user);
    }

    public void auth(User user) {
        Optional<User> optionalUser = userJpaRepository.findById(user.getUsername());
        if (optionalUser.isEmpty()) {
            throw new BadCredentialsException("Bad credentials");
        }
        User recordedUser = optionalUser.get();
        if (!passwordEncoder.matches(user.getPassword(), recordedUser.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }
        renewOpt(recordedUser);
    }

    public boolean check(otp otp) {
        Optional<otp> optionalOtp = otpJpaRepository.findById(otp.getUsername());
        if (optionalOtp.isEmpty()) {
            return false;
        }
        return otp.getCode().equals(optionalOtp.get().getCode());
    }

    private void renewOpt(User user) {
        String code = GenerateCodeUtil.generateCode();
        Optional<otp> optionalOtp = otpJpaRepository.findById(user.getUsername());
        if (optionalOtp.isPresent()) {
            otp otp = optionalOtp.get();
            otp.setCode(code);
        } else {
            otp otp = new otp();
            otp.setUsername(user.getUsername());
            otp.setCode(code);
            otpJpaRepository.save(otp);
        }
    }

}
