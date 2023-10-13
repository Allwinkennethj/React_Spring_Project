package com.example.backend.Service;

import com.example.backend.Entity.LoginStreak;
import com.example.backend.Entity.Userinfo;
import com.example.backend.Repository.LoginStreakRepo;
import com.example.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class LoginStreakImplementation implements LoginStreakService{
    // @Autowired
    // private LoginStreakService loginStreakService;
    // @Autowired
    // private LoginStreakRepo loginStreakRepo;

    // @Autowired
    // private UserRepository userRepository;

    // @Override
    // public int getLoginStreak(Long userid) {
    //     Optional<LoginStreak> streak= loginStreakRepo.findById(userid);
    //     if(streak.isPresent())
    //     {
    //      LoginStreak loginStreak=streak.get();
    //      LocalDate lastLoginDate=loginStreak.getLastLoginDate();
    //      int currentStreak=loginStreak.getStreakCount();
    //      LocalDate currentDate=LocalDate.now();
    //      if(lastLoginDate==null)
    //      {
    //          loginStreak.setStreakCount(1);
    //          loginStreak.setLastLoginDate(currentDate);
    //      }
    //      else if(lastLoginDate.isEqual(currentDate.minusDays(1))){
    //          loginStreak.setLastLoginDate(currentDate);
    //          loginStreak.setStreakCount(currentStreak+1);

    //      }else {
    //          loginStreak.setStreakCount(1);
    //          loginStreak.setLastLoginDate(currentDate);
    //      }
    //        loginStreakRepo.save(loginStreak);
    //      return loginStreak.getStreakCount();
    //     }
    //     else {
    //         LoginStreak newlogin=new LoginStreak();
    //         newlogin.setLastLoginDate(LocalDate.now());
    //         newlogin.setStreakCount(1);
    //         Optional<Userinfo> userinfo = userRepository.findById(userid);
    //         if (userinfo.isEmpty()) {
    //             throw new RuntimeException("user not found");
    //         }
    //         newlogin.setUser(userinfo.get());
    //         loginStreakRepo.save(newlogin);
    //         return newlogin.getStreakCount();
    //     }

    // }
}
