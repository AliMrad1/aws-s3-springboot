package com.example.awsimageupload.profile;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/user-profile")
@AllArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping
    public List<UserProfile> getAlUserProfiles(){
        return userProfileService.getUserProfiles();
    }
}
