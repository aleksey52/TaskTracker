package com.practice.tracker.service;

import com.practice.tracker.entity.User;
import com.practice.tracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
  private UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User findUser(String userName) {
    return userRepository.findByUserName(userName);
  }

  @Override
  public Optional<User> findById(long id) {
    return Optional.ofNullable(userRepository.findById(id));
  }

  @Override
  public User save(User user) {
    user.setUserName(user.getUserName().trim());
    if(!user.getUserName().equals("")) {
      return userRepository.save(user);
    }
    return user;
  }

  @Override
  public void deleteById(long id) {
    userRepository.deleteById(id);
  }

}
