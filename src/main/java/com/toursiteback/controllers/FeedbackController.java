package com.toursiteback.controllers;

import com.toursiteback.dto.FeedbackDto;
import com.toursiteback.service.feedback.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tours/feedback")
@CrossOrigin(origins = "*")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    @ResponseBody
    public List<FeedbackDto> getFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @PostMapping
    @ResponseBody
    public List<FeedbackDto> addFeedback(@RequestBody FeedbackDto feedbackDto) {
        return feedbackService.addFeedback(feedbackDto);
    }

    @DeleteMapping
    @ResponseBody
    public List<FeedbackDto> deleteFeedback(@RequestParam String comment){
        return feedbackService.deleteFeedback(comment);
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS).build();
    }
}
