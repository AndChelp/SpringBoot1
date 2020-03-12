package first.controller;

import first.exception.UserNotFoundException;
import first.log.Level;
import first.log.annotation.Log;
import first.model.UserAPI;
import first.model.UserData;
import first.response.Response;
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
@RequestMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {
    @Autowired
    private final UsersService usersService;

    @Log(level = Level.INFO)
    @PostMapping
    public ResponseEntity<Response> create(@Valid @RequestBody UserData user) {
        usersService.addUser(user);
        return ResponseEntity.ok(Response.builder()
                .statusMsg("User created!")
                .build()
        );
    }

    @Log(level = Level.DEBUG)
    @GetMapping
    public ResponseEntity<Response> read() throws UserNotFoundException {
        List<UserData> userDataList = usersService.findAllUsers();
        List<UserAPI> userAPIList = new ArrayList<>();
        for (UserData userData : userDataList) {
            userAPIList.add(new UserAPI(userData));
        }
        return ResponseEntity.ok(Response.builder()
                .statusMsg("Found " + userAPIList.size() + " users!")
                .userAPIList(userAPIList)
                .userCount(userAPIList.size())
                .build()
        );
    }

    @Log(level = Level.DEBUG)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Response> read(@PathVariable(name = "id") long id) throws UserNotFoundException {
        UserAPI userAPI = new UserAPI(usersService.findUserById(id));
        return ResponseEntity.ok(Response.builder()
                .statusMsg("User found!")
                .userAPI(userAPI)
                .build()
        );
    }

    @Log(level = Level.INFO)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> delete(@PathVariable(name = "id") long id) throws UserNotFoundException {
        usersService.deleteUserById(id);
        return ResponseEntity.ok(Response.builder()
                .statusMsg("User deleted!")
                .build()
        );
    }
}

























