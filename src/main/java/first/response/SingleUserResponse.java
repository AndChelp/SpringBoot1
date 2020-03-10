package first.response;

import first.model.UserAPI;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SingleUserResponse extends ErrorResponse {
    private UserAPI userAPI;

    public SingleUserResponse(int statusCode, String statusMsg, UserAPI userAPI) {
        super(statusCode, statusMsg);
        this.userAPI = userAPI;
    }
}
