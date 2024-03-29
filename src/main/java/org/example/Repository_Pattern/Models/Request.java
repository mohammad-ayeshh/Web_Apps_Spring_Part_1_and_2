package Repository_Pattern.Models;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 1L;

    private String requestType;
    private AuthenticationRequest requestData;

    public Request(String requestType, AuthenticationRequest requestData) {
        this.requestType = requestType;
        this.requestData = requestData;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestType='" + requestType + '\'' +
                ", requestData=" + requestData +
                '}';
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Object getRequestData() {
        return requestData;
    }

    public void setRequestData(AuthenticationRequest requestData) {
        this.requestData = requestData;
    }


}
