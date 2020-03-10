package first.model;

import lombok.Getter;

@Getter
public class UserAPI {

    private long id;
    private String firstName;
    private short age;
    private boolean hasPremium;

    public UserAPI(UserData userData) {
        this.id = userData.getId();
        this.firstName = userData.getFirstName();
        this.age = userData.getAge();
        this.hasPremium = userData.isHasPremium();
    }
}
