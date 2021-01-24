package in.tatsam.priority.model.request;

import java.util.List;

public class UserReviewReq {
    List<ReviewReq> reviewReqList;

    public List<ReviewReq> getReviewReqList() {
        return reviewReqList;
    }

    public void setReviewReqList(List<ReviewReq> reviewReqList) {
        this.reviewReqList = reviewReqList;
    }
}
