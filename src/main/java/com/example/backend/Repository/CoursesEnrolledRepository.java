package com.example.backend.Repository;

import com.example.backend.Entity.CoursesEnrolled;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoursesEnrolledRepository extends JpaRepository<CoursesEnrolled,Integer> {


    List<CoursesEnrolled> findAllByUserInfoUserId(Long userid);

    Optional<CoursesEnrolled> findByUserInfoUserIdAndCourseInfoCourseId(Long userid, Long courseId);
}
