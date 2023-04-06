package com.bilgeadam.repository;

import com.bilgeadam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmailAndPassword(String email, String password);

    List<User> findAllByOrderByName();

    List<User> findAllByNameContainsIgnoreCase(String value);

    boolean existsByNameIgnoreCase(String value);

    List<User> findByEmailIgnoreCase(String value);

    Optional<User> findByEmailEqualsIgnoreCase(String email);

    //NativeSQL
    @Query(value = "select * from users as u where length(u.password)>:x", nativeQuery = true)
    List<User> passwordLongerThan(@Param("x") int value);
    //JPQL ile
    @Query("select u from User u where length(u.password)>?1")
    List<User> passwordLongerThan2(int num);

    List<User> findByEmailEndsWithIgnoreCase(String email);


}
