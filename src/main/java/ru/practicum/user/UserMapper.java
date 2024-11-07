package ru.practicum.user;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class UserMapper {
    public static User toUser(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .state(userDto.getState())
                .build();
    }

    public static UserDto toUserDto(User user) {
        String regDate = DateTimeFormatter
                .ofPattern("yyyy.MM.dd hh:mm:ss")
                .withZone(ZoneOffset.UTC)
                .format(user.getRegistrationDate());

        return UserDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .registrationDate(regDate)
                .state(user.getState())
                .build();
    }

    public static List<UserDto> toUserDto(Iterable<User> users) {
        List<UserDto> result = new ArrayList<>();
        for (User user : users) {
            result.add(toUserDto(user));
        }

        return result;
    }
}
