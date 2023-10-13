package com.example.backend.Service;

import com.example.backend.Entity.Courseinfo;
import com.example.backend.dto.UserDetailsDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public String addUser(String username, String email,String role);
    public UserDetailsDTO getusercourse(Long userid);
    public void admininsert(String coursename, String coursedescription, String language, String duration, String difficultylevel, float rating, float price, String authorname, String link);
    public List<Courseinfo> getallcourse();
    public Long deletecourse(Long courseId);
    public String enrollcourse(Long courseId, Long userid);
    public String access(Long userid,Long courseId);

    String editCourse(Long courseId, String authorname, String coursedescription, String coursename, String difficultylevel, String duration, String link, float price, float rating);
    public Optional<Courseinfo> getCourseInfo(Long courseId);

}
