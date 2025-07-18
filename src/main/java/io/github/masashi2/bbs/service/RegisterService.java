package io.github.masashi2.bbs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.masashi2.bbs.model.User;
import io.github.masashi2.bbs.repository.UserRepository;


@Service
public class RegisterService {

    private final UserRepository userRepository;

    @Autowired
    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //ユーザー情報の保存
    public boolean saveUser(String userid, String password) {
        if (userRepository.existsById(userid)) {
            return false; // 重複ユーザー
        }
        User user = new User(userid, password);
        userRepository.save(user); 
        return true;
    }

    //ユーザー情報の取得
    public Optional<User> getUserById(String userid) {
        return userRepository.findById(userid);
    }

    //ユーザー情報の全取得
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}