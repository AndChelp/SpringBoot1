package first.response;

import first.model.UserAPI;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class MultipleUsersResponse extends ErrorResponse {
    private List<UserAPI> userAPIList;
    private int count;

    public MultipleUsersResponse(int statusCode, String statusMsg, List<UserAPI> userAPIList, int count) {
        super(statusCode, statusMsg);
        this.userAPIList = userAPIList;
        this.count = count;
    }
}
