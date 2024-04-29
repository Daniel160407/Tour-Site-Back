package com.toursiteback.util;

import com.toursiteback.dto.TourCollectionDto;
import com.toursiteback.dto.TourDto;
import com.toursiteback.messenger.dto.MessageDto;
import com.toursiteback.messenger.dto.UserDto;
import com.toursiteback.messenger.model.Message;
import com.toursiteback.messenger.model.User;
import com.toursiteback.model.Tour;
import com.toursiteback.model.TourWithImg;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelConverter {
    public TourCollectionDto convertTours(List<Tour> tours) {
        List<TourDto> tourDtos = tours.stream()
                .map(this::convert)
                .toList();

        return TourCollectionDto.builder()
                .tours(tourDtos)
                .build();
    }

    public TourDto convert(Tour tour) {
        return TourDto.builder()
                .name(tour.getName())
                .description(tour.getDescription())
                .direction(tour.getDirection())
                .history(tour.getHistory())
                .price(tour.getPrice())
                .imgUrl(tour.getImgUrl())
                .build();
    }

    public List<TourDto> convert(List<Tour> tours) {
        List<TourDto> tourDtos = new ArrayList<>();

        for (Tour tour : tours) {
            tourDtos.add(convert(tour));
        }
        return tourDtos;
    }

    public Tour convert(TourDto tourDto) {
        return Tour.builder()
                .name(tourDto.getName())
                .description(tourDto.getDescription())
                .direction(tourDto.getDirection())
                .history(tourDto.getHistory())
                .price(tourDto.getPrice())
                .imgUrl(tourDto.getImgUrl())
                .build();
    }

    public Tour convert(TourWithImg tour, String imgUrl) {
        return Tour.builder()
                .name(tour.getName())
                .description(tour.getDescription())
                .direction(tour.getDirection())
                .history(tour.getHistory())
                .price(tour.getPrice())
                .imgUrl(imgUrl)
                .build();
    }

    public TourDto convert(TourWithImg tour) {
        return TourDto.builder()
                .name(tour.getName())
                .description(tour.getDescription())
                .direction(tour.getDirection())
                .history(tour.getHistory())
                .price(tour.getPrice())
                .build();
    }

    public static UserDto convert(User user) {
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .sid(user.getSid())
                .build();
    }

    public User convert(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .sid(userDto.getSid())
                .build();
    }

    public static MessageDto convert(Message message) {
        return MessageDto.builder()
                .senderEmail(message.getSenderEmail())
                .receiverEmail(message.getReceiverEmail())
                .sender(message.getSender())
                .receiver(message.getReceiver())
                .message(message.getMessage())
                .build();
    }

    public List<UserDto> convertUsersToDtoList(List<User> userList) {
        return userList.stream()
                .map(ModelConverter::convert)
                .collect(Collectors.toList());
    }

    public List<MessageDto> convertMessagesToDtoList(List<Message> messageList) {
        return messageList.stream()
                .map(ModelConverter::convert)
                .collect(Collectors.toList());
    }
}
