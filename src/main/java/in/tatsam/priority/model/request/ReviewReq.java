package in.tatsam.priority.model.request;

import in.tatsam.priority.model.Priority;

import javax.persistence.Column;

public class ReviewReq {
    private Long priorityId;
    private Integer rating;
    private String description;

    public Long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Long priorityId) {
        this.priorityId = priorityId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
