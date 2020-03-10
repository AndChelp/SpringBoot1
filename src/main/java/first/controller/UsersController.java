package first.controller;

import first.exception.UserNotFoundException;
import first.model.UserAPI;
import first.model.UserData;
import first.response.ErrorCode;
import first.response.ErrorResponse;
import first.response.MultipleUsersResponse;
import first.response.SingleUserResponse;
import first.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
public class UsersController {
    @Autowired
    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<ErrorResponse> create(@Valid @RequestBody UserData user) {
        usersService.addUser(user);
        return ResponseEntity.ok(new ErrorResponse(ErrorCode.CREATED, "User created!"));
    }

    @GetMapping
    public ResponseEntity<ErrorResponse> read() throws UserNotFoundException {
        List<UserData> userDataList = usersService.findAllUsers();
        List<UserAPI> userAPIList = new ArrayList<>();
        for (UserData userData : userDataList) {
            userAPIList.add(new UserAPI(userData));
        }
        return ResponseEntity.ok(new MultipleUsersResponse(ErrorCode.FOUND, "Found " + userAPIList.size() + " users!", userAPIList, userAPIList.size()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ErrorResponse> read(@PathVariable(name = "id") long id) throws UserNotFoundException {
        UserAPI userAPI = new UserAPI(usersService.findUserById(id));
        return ResponseEntity.ok(new SingleUserResponse(ErrorCode.FOUND, "User found!", userAPI));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ErrorResponse> delete(@PathVariable(name = "id") long id) throws UserNotFoundException {
        usersService.deleteUserById(id);
        return ResponseEntity.ok(new ErrorResponse(ErrorCode.DELETED, "User deleted!"));
    }
}

























