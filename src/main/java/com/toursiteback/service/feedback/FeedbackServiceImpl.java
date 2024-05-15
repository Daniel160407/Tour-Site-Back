package com.toursiteback.service.feedback;

import com.toursiteback.dto.FeedbackDto;
import com.toursiteback.repository.FeedbacksRepository;
import com.toursiteback.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbacksRepository feedbacksRepository;
    private final ModelConverter modelConverter;

    @Autowired
    public FeedbackServiceImpl(FeedbacksRepository feedbacksRepository, ModelConverter modelConverter) {
        this.feedbacksRepository = feedbacksRepository;
        this.modelConverter = modelConverter;
    }

    @Override
    public List<FeedbackDto> getAllFeedbacks() {
        return modelConverter.convertFeedbacksToDtoList(feedbacksRepository.getAllBy());
    }

    @Override
    public List<FeedbackDto> addFeedback(FeedbackDto feedbackDto) {
        feedbacksRepository.save(modelConverter.convert(feedbackDto));
        return modelConverter.convertFeedbacksToDtoList(feedbacksRepository.getAllBy());
    }

    @Override
    @Transactional
    public List<FeedbackDto> deleteFeedback(String comment) {
        feedbacksRepository.deleteByComment(comment);
        return modelConverter.convertFeedbacksToDtoList(feedbacksRepository.getAllBy());
    }
}
