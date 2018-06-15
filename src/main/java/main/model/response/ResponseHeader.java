package main.model.response;

/**
 * Created by Sherif on 6/15/2018.
 */
public class ResponseHeader {

    private Integer senderId;

    public Integer getSenderId() {
        return senderId;
    }

    public ResponseHeader setSenderId(Integer senderId) {
        this.senderId = senderId;
        return this;
    }
}
