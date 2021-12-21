package axiata.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import axiata.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);

    User findByEmail(String email);

    @Transactional
    void deleteByUsername(String username);

    User findTopByOrderByIdDesc();

    @Override
    Page<User> findAll(Pageable pageable);

    @Query(value = "select u from User u where u.employeeNumber = ?1 or u.email = ?1 ")
    User findByEmailOrEmpId(String key);

}
