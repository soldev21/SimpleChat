package main.model.request;

/**
 * Created by Sherif on 6/15/2018.
 */
public class RequestHeader {

    private Integer receiverId;

    public Integer getReceiverId() {
        return receiverId;
    }

    public RequestHeader setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
        return this;
    }
}
