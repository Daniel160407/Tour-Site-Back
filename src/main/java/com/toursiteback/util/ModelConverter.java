package com.toursiteback.util;

import com.toursiteback.dto.CountryDto;
import com.toursiteback.dto.FeedbackDto;
import com.toursiteback.dto.StatisticDto;
import com.toursiteback.dto.TourDto;
import com.toursiteback.messenger.dto.MessageDto;
import com.toursiteback.messenger.dto.UserDto;
import com.toursiteback.messenger.model.Message;
import com.toursiteback.messenger.model.User;
import com.toursiteback.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelConverter {

    public TourDto convert(Tour tour) {
        return TourDto.builder()
                .name(tour.getName())
                .description(tour.getDescription())
                .direction(tour.getDirection())
                .duration(tour.getDuration())
                .history(tour.getHistory())
                .requirements(tour.getRequirements())
                .price(tour.getPrice())
                .language(tour.getLanguage())
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

    public Tour convert(TourWithImg tour, String imgUrl) {
        return Tour.builder()
                .name(tour.getName())
                .description(tour.getDescription())
                .direction(tour.getDirection())
                .duration(tour.getDuration())
                .history(tour.getHistory())
                .requirements(tour.getRequirements())
                .price(tour.getPrice())
                .language(tour.getLanguage())
                .imgUrl(imgUrl)
                .build();
    }

    public TourDto convert(TourWithImg tour) {
        return TourDto.builder()
                .name(tour.getName())
                .description(tour.getDescription())
                .direction(tour.getDirection())
                .duration(tour.getDuration())
                .history(tour.getHistory())
                .requirements(tour.getRequirements())
                .price(tour.getPrice())
                .language(tour.getLanguage())
                .build();
    }

    public static UserDto convert(User user) {
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .sid(user.getSid())
                .position(user.getPosition())
                .build();
    }

    public User convert(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .sid(userDto.getSid())
                .position(userDto.getPosition())
                .build();
    }

    public static MessageDto convert(Message message) {
        return MessageDto.builder()
                .senderEmail(message.getSenderEmail())
                .receiverEmail(message.getReceiverEmail())
                .sender(message.getSender())
                .receiver(message.getReceiver())
                .message(message.getMessage())
                .time(message.getTime())
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

    public List<FeedbackDto> convertFeedbacksToDtoList(List<Feedback> feedbacks) {
        List<FeedbackDto> feedbackDtos = new ArrayList<>();
        feedbacks.forEach(feedback ->
                feedbackDtos.add(new FeedbackDto(feedback.getName(), feedback.getTime(), feedback.getComment()))
        );
        return feedbackDtos;
    }

    public Feedback convert(FeedbackDto feedbackDto) {
        return Feedback.builder()
                .name(feedbackDto.getName())
                .time(feedbackDto.getTime())
                .comment(feedbackDto.getComment())
                .build();
    }

    public StatisticDto convert(Statistic statistic) {
        return StatisticDto.builder()
                .time(statistic.getTime())
                .clicks(statistic.getClicks())
                .users(statistic.getUsers())
                .messages(statistic.getMessages())
                .feedbacks(statistic.getFeedbacks())
                .build();
    }

    public Country convert(CountryDto countryDto) {
        return Country.builder()
                .ip(countryDto.getIp())
                .country(countryDto.getCountry())
                .build();
    }

    public List<CountryDto> covertCountriesListToDto(List<Country> countries) {
        List<CountryDto> countryDtos = new ArrayList<>();
        countries.forEach(country -> countryDtos.add(new CountryDto(country.getIp(), country.getCountry())));
        return countryDtos;
    }
}
