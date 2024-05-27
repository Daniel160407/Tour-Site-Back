package com.toursiteback.service.states;

import com.toursiteback.dto.StateDto;
import com.toursiteback.model.State;
import com.toursiteback.repository.StatesRepository;
import com.toursiteback.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StatesServiceImpl implements StatesService {
    private final StatesRepository statesRepository;
    private final ModelConverter modelConverter;

    @Autowired
    public StatesServiceImpl(StatesRepository statesRepository, ModelConverter modelConverter) {
        this.statesRepository = statesRepository;
        this.modelConverter = modelConverter;
    }

    @Override
    public void increaseTime(String time) {
        State state = statesRepository.findById(1).orElse(null);
        if (state != null) {
            state.setTime(Double.toString(Double.parseDouble(state.getTime()) + Double.parseDouble(time)));
            statesRepository.save(state);
        }
    }

    @Override
    public void increaseClicks(Integer clicks) {
        State state = statesRepository.findById(1).orElse(null);
        if (state != null) {
            state.setClicks(state.getClicks() + clicks);
            statesRepository.save(state);
        }
    }

    @Override
    public void increaseMessages() {
        State state = statesRepository.findById(1).orElse(null);
        if (state != null) {
            state.setMessages(state.getMessages() + 1);
            statesRepository.save(state);
        }
    }

    @Override
    public void increaseFeedbacks() {
        State state = statesRepository.findById(1).orElse(null);
        if (state != null) {
            state.setFeedbacks(state.getFeedbacks() + 1);
            statesRepository.save(state);
        }
    }

    @Override
    public StateDto getStates() {
        return modelConverter.convert(Objects.requireNonNull(statesRepository.findById(1).orElse(null)));
    }
}
