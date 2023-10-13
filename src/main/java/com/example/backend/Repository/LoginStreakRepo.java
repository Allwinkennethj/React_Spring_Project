package com.example.backend.Repository;

import com.example.backend.Entity.LoginStreak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginStreakRepo extends JpaRepository<LoginStreak,Long> {
    int getLoginStreak(Long userid);
}
