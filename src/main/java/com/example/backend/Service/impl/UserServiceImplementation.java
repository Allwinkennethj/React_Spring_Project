package com.example.backend.Service.impl;

import com.example.backend.Entity.Courseinfo;
import com.example.backend.Entity.CoursesEnrolled;
import com.example.backend.Entity.Userinfo;
import com.example.backend.Repository.CourseinfoRepository;
import com.example.backend.Repository.CoursesEnrolledRepository;
import com.example.backend.Repository.UserRepository;
import com.example.backend.Service.UserService;
import com.example.backend.dto.CourseEnrolledDTO;
import com.example.backend.dto.UserDetailsDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseinfoRepository courseinfoRepository;
    @Autowired
    private CoursesEnrolledRepository coursesEnrolledRepository;
    @Autowired
    private JavaMailSender javaMailSender;



    @Override
    public String addUser(String username, String email,String role) {
        Userinfo user=new Userinfo();
        List<Userinfo> check=userRepository.findByEmail(email);
        if(check.isEmpty()) {
            user.setEmail(email);
            user.setUserName(username);
            user.setRole(role);
            userRepository.save(user);
            sendWelcomeEmail(user);
            return "Added";
        }else {
            return "Existing user";
        }

    }

    @Override
    public UserDetailsDTO getusercourse(Long userid) {
        ModelMapper modelMapper = new ModelMapper();
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        Optional<Userinfo> user = userRepository.findById(userid);
        if(user.isEmpty())
        {
            throw new RuntimeException("user not found");
        }
        userDetailsDTO.setUserinfo(user.get());
        List<CoursesEnrolled> allByUserInfoUserId = coursesEnrolledRepository.findAllByUserInfoUserId(userid);
        List<CourseEnrolledDTO> courseEnrolledDTOS = new ArrayList<>();
        allByUserInfoUserId.forEach(coursesEnrolled -> {
            CourseEnrolledDTO map = modelMapper.map(coursesEnrolled, CourseEnrolledDTO.class);

            courseEnrolledDTOS.add(map);
        });
        userDetailsDTO.setCourseEnrolled(courseEnrolledDTOS);
        return userDetailsDTO;
    }
    @Override
    public void admininsert(String coursename, String coursedescription, String language, String duration, String difficultylevel, float rating, float price, String authorname, String link) {
        Courseinfo course = new Courseinfo();
        course.setCoursename(coursename);
        course.setCoursedescription(coursedescription);
        course.setLanguage(language);
        course.setDuration(duration);
        course.setRating(rating);
        course.setPrice(price);
        course.setAuthorname(authorname);
        course.setLink(link);
        course.setDifficultyLevel(difficultylevel);
        courseinfoRepository.save(course);
    }
    @Override
    public List<Courseinfo> getallcourse()
    {
        return courseinfoRepository.findAll();
    }
    @Override
    public Long deletecourse(Long courseId)
    {
        courseinfoRepository.deleteById(courseId);
        return courseId;
    }

    @Override
    public String enrollcourse(Long courseId,Long userid) {
        CoursesEnrolled coursesEnrolled1=new CoursesEnrolled();
        coursesEnrolled1.setPaid(true);
        coursesEnrolled1.setEnrolledDate(new Date());
        coursesEnrolled1.setAccessedDate(new Date());
        Optional<Userinfo> userinfo = userRepository.findById(userid);
        if (userinfo.isEmpty()) {
            throw new RuntimeException("user not found");
        }
        coursesEnrolled1.setUserInfo(userinfo.get());

        Optional<Courseinfo> courseinfo = courseinfoRepository.findById(courseId);
        if (courseinfo.isEmpty()) {
            throw new RuntimeException("course not found");
        }
        coursesEnrolled1.setCourseInfo(courseinfo.get());
        coursesEnrolledRepository.save(coursesEnrolled1);
        return "done";

    }

    @Override
    public String access(Long userid, Long courseId) {
        Optional<CoursesEnrolled> enrollment = coursesEnrolledRepository.findByUserInfoUserIdAndCourseInfoCourseId(userid, courseId);
        Optional<Userinfo> userinfo=userRepository.findById(userid);
        if(userinfo.isEmpty())
        {
            return "User not Found";
        }
        Optional<Courseinfo> courseinfo=courseinfoRepository.findById(courseId);
        if(courseinfo.isEmpty())
        {
            return "Course not found";
        }
        if(enrollment.isPresent())
        {
            return "Access granted";
        }
        else {
            return "Access denied";
        }
    }

    @Override
    public String editCourse( Long courseId, String authorname, String coursedescription, String coursename, String difficultylevel, String duration, String link, float price, float rating) {
        Optional<Courseinfo> edit = courseinfoRepository.findById(courseId);

        if (edit.isPresent()) {
            Courseinfo course = edit.get();
            course.setAuthorname(authorname);
            course.setCoursedescription(coursedescription);
            course.setCoursename(coursename);
            course.setDifficultyLevel(difficultylevel);
            course.setDuration(duration);
            course.setLink(link);
            course.setPrice(price);
            course.setRating(rating);

            courseinfoRepository.save(course);

            return "Course updated successfully";
        } else {
            return "Course not found";
        }
    }

    

    public void sendWelcomeEmail(Userinfo user)
    {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("sabarimanoj960@gmail.com");
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Welcome to Learning Platform");
        simpleMailMessage.setText("Hello " + user.getUserName() + ",\n\n"
                + "You are now registered on the Learning Platform. Start your learning journey today!\n\n"
                + "Best regards,\nLearning Platform Team");
        javaMailSender.send(simpleMailMessage);
    }
    @Override
    public Optional<Courseinfo> getCourseInfo(Long courseId) {
         Optional<Courseinfo> course=courseinfoRepository.findById(courseId); 
         return course;
         
    }
}
