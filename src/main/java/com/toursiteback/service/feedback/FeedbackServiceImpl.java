package com.toursiteback.service.feedback;

import com.toursiteback.dto.FeedbackDto;
import com.toursiteback.repository.FeedbacksRepository;
import com.toursiteback.service.states.StatesService;
import com.toursiteback.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbacksRepository feedbacksRepository;
    private final StatesService statesService;
    private final ModelConverter modelConverter;

    @Autowired
    public FeedbackServiceImpl(FeedbacksRepository feedbacksRepository, StatesService statesService, ModelConverter modelConverter) {
        this.feedbacksRepository = feedbacksRepository;
        this.statesService = statesService;
        this.modelConverter = modelConverter;
    }

    @Override
    public List<FeedbackDto> getAllFeedbacks() {
        return modelConverter.convertFeedbacksToDtoList(feedbacksRepository.getAllBy());
    }

    @Override
    public List<FeedbackDto> addFeedback(FeedbackDto feedbackDto) {
        feedbacksRepository.save(modelConverter.convert(feedbackDto));
        statesService.increaseFeedbacks();
        return modelConverter.convertFeedbacksToDtoList(feedbacksRepository.getAllBy());
    }

    @Override
    @Transactional
    public List<FeedbackDto> deleteFeedback(String comment) {
        feedbacksRepository.deleteByComment(comment);
        return modelConverter.convertFeedbacksToDtoList(feedbacksRepository.getAllBy());
    }
}
