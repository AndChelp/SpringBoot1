package first.service;

import first.exception.UserNotFoundException;
import first.model.UserData;
import first.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public void addUser(UserData userData) {
        usersRepository.save(userData);
    }

    public UserData findUserById(long id) throws UserNotFoundException {
        UserData userData = usersRepository.findById(id).orElse(null);
        if (userData == null)
            throw new UserNotFoundException("User with this ID not found!");
        return userData;
    }

    public List<UserData> findAllUsers() throws UserNotFoundException {
        List<UserData> userDataList = usersRepository.findAll();
        if (userDataList.isEmpty()) {
            throw new UserNotFoundException("Users not found!");
        }
        return userDataList;
    }

    public void deleteUserById(long id) throws UserNotFoundException {
        if (!usersRepository.existsById(id))
            throw new UserNotFoundException("Cannot delete user with ID " + id + " because it does not exists!");
        usersRepository.deleteById(id);
    }
}
