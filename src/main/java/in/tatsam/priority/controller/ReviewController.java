package in.tatsam.priority.controller;

import in.tatsam.priority.model.request.ReviewReq;
import in.tatsam.priority.model.request.UserReviewReq;
import in.tatsam.priority.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity createReview(@RequestBody UserReviewReq userReviewReq, @RequestHeader Long userId){
        reviewService.createReview(userReviewReq, userId);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/v1")
    public ResponseEntity postReview(@RequestBody List<ReviewReq> reviewReqs, @RequestHeader Long userId){
        reviewService.postReview(reviewReqs, userId);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/{id}")
    public  ResponseEntity getById(@PathVariable Long id){
        return ResponseEntity.ok(reviewService.findById(id));
    }

    @GetMapping("/all")
    public  ResponseEntity getAll(@RequestHeader Long userId){
        return ResponseEntity.ok(reviewService.getAdminSpecific(userId));
    }


}
