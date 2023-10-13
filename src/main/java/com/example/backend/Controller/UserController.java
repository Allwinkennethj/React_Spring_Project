package com.example.backend.Controller;

import com.example.backend.Entity.Courseinfo;
import com.example.backend.Service.UserService;
import com.example.backend.dto.AdminCoursePostDTO;
import com.example.backend.dto.UserDetailsDTO;
import com.example.backend.dto.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class UserController {
    @Autowired
    UserService userService;

   @PostMapping("/Adduser")
    public String addUser(@RequestBody UserInfoDTO userinfo) {
       return userService.addUser(userinfo.getUserName(),
                userinfo.getEmail(),
                null);
    }
    @GetMapping("/courses/{userid}")
    public UserDetailsDTO getUserCourses(@PathVariable long userid) {
        return userService.getusercourse(userid);
    }
@PostMapping("/Adminposting")
    public void admininsert(@RequestBody AdminCoursePostDTO courseinfo)
{
    userService.admininsert(courseinfo.getCoursename(), courseinfo.getCoursedescription(), courseinfo.getLanguage(), courseinfo.getDuration(), courseinfo.getDifficultylevel(), courseinfo.getRating(),courseinfo.getPrice(),courseinfo.getAuthorname(),courseinfo.getLink());
}
@GetMapping("/get")
    public List<Courseinfo> getallcourses()
{
    return userService.getallcourse();
}
@DeleteMapping("/DeleteCourse/{courseId}")
    public Long admindeletecourse(@PathVariable long courseId)
{
    return userService.deletecourse(courseId);
}
@PostMapping("/enroll")
public String enrollment(@RequestParam(value="courseId") Long courseId,@RequestParam(value="userid") Long userid )
{
    return userService.enrollcourse(courseId,userid);
}
@GetMapping("/Access")
        public String accesscontrol(@RequestParam (value="userid") Long userid,@RequestParam(value = "courseId") Long courseId)
{
    return userService.access(userid,courseId);
}
@PutMapping("/EditCourse/{courseId}")
    public String EditCourse(@RequestBody Courseinfo courseinfo,@PathVariable Long courseId)
{

    return userService.editCourse(courseId, courseinfo.getAuthorname(), courseinfo.getCoursedescription(), courseinfo.getCoursename(), courseinfo.getDifficultylevel(), courseinfo.getDuration(), courseinfo.getLink(), courseinfo.getPrice(), courseinfo.getRating());
}
@GetMapping("/Getcourse/{courseId}")
        public Optional<Courseinfo> getcourseinfo(@RequestBody Courseinfo courseinfo,@PathVariable Long courseId)
{
    return userService.getCourseInfo(courseId);
}



}
