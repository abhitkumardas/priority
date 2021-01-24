package in.tatsam.priority.service;

import in.tatsam.priority.model.Priority;
import in.tatsam.priority.model.Review;
import in.tatsam.priority.model.User;
import in.tatsam.priority.model.request.ReviewReq;
import in.tatsam.priority.model.request.UserReviewReq;

import java.util.List;

public interface ReviewService {
    public List<Review> findAll();
    public Review findById(Long id);
    public List<Review> findByUser(User user);
    public List<Review> findByPriority(Priority priority);

    public void createReview(UserReviewReq userReviewReq, Long userId);
    public void postReview(List<ReviewReq> reviewReqs, Long userId);

    public List<Review> findByUserId(Long userId);

    public List<Review> getAdminSpecific(Long userId);
}
