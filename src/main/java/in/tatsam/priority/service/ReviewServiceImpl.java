package in.tatsam.priority.service;

import in.tatsam.priority.model.Priority;
import in.tatsam.priority.model.Review;
import in.tatsam.priority.model.User;
import in.tatsam.priority.model.request.ReviewReq;
import in.tatsam.priority.model.request.UserReviewReq;
import in.tatsam.priority.repository.ReviewRepository;
import in.tatsam.priority.utils.PriorityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PriorityService priorityService;

    @Autowired
    private PriorityUtils priorityUtils;

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id).get();
    }

    @Override
    public List<Review> findByUser(User user) {
        return reviewRepository.findByUser(user);
    }

    @Override
    public List<Review> findByPriority(Priority priority) {
        return reviewRepository.findByPriority(priority);
    }

    public void createReview(UserReviewReq userReviewReq, Long userId){
        postReview(userReviewReq.getReviewReqList(), userId);
    }

    @Override
    public void postReview(List<ReviewReq> reviewReqs, Long userId) {
        List<Review> reviewList = new ArrayList<>();

        User user = userService.findById(userId);
        reviewReqs.forEach( x->
                {
                    Long priorityId = x.getPriorityId();
                    Integer rating = x.getRating();
                    String desc = x.getDescription();

                    Priority priority = priorityService.findById(priorityId);
                    if (priority!=null){
                        Review review = new Review();
                        review.setActive(true);
                        review.setGuid(priorityUtils.guidGenerator(userId+priorityId));
                        review.setRating(priorityUtils.formatRatings(rating));
                        review.setDescription(desc);
                        review.setPriority(priority);
                        review.setUser(user);

                        save(review);
                        reviewList.add(review);
                    }
                }
        );
    }

    @Override
    public List<Review> findByUserId(Long userId) {
        User user = userService.findById(userId);
        return findByUser(user);
    }

    public Review save(Review review){
        review.setActive(review.getActive()==null ? true:review.getActive());
        return reviewRepository.save(review);
    }
    
    public Review disableReview(Review review,User user){
        boolean isAdmin = userService.isAdminUser(user);
        if (!isAdmin){
            throw new RuntimeException("Only Admin Can disable Reviews");
        }

        review.setActive(false);
        return save(review);
    }

    @Override
    public List<Review> getAdminSpecific(Long userId){
        boolean isAdmin = userService.isAdminUser(userId);
        return isAdmin ? findAll(): findByUserId(userId);
    }
}
