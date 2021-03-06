package com.djoly.hello.RESTJWT.repositories;

import com.djoly.hello.RESTJWT.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


   List<User> findUserByUsername(@Param("username") String username);

}
