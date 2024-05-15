package com.toursiteback.service.feedback;

import com.toursiteback.dto.FeedbackDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeedbackService {
    List<FeedbackDto> getAllFeedbacks();
    List<FeedbackDto> addFeedback(FeedbackDto feedbackDto);
}
