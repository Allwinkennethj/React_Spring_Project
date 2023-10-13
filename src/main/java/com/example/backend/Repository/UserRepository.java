package com.example.backend.Repository;

import com.example.backend.Entity.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<Userinfo,Long> {
    List<Userinfo> findByEmail(String email);
}
