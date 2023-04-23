package toik.foodApp.user.domain;

import toik.foodApp.user.dto.UserDto;

class UserCreator {

  User from(UserDto userDto) {

    return User.builder()
        .userId(userDto.getUserId())
        .name(userDto.getName())
        .surname(userDto.getSurname())
        .username(userDto.getUsername())
        .password(userDto.getPassword())
        .email(userDto.getEmail())
        .phoneNumber(userDto.getPhoneNumber())
        .build();
  }
}
