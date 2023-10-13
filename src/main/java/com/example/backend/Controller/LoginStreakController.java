package com.example.backend.Controller;

import com.example.backend.Repository.LoginStreakRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LoginStreakController {
    @Autowired
    private LoginStreakRepo loginStreakRepo;
    @GetMapping("/GetLoginStreakforParticularUser")
    public int GetLoginStreakforParticularUser(@PathVariable Long userid)
    {
        return loginStreakRepo.getLoginStreak(userid);
    }

}
