package first.repository;

import first.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UserData, Long> {
}
