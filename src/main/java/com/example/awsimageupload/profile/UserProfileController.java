package com.example.awsimageupload.profile;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-profile")
@AllArgsConstructor
@CrossOrigin("*") // accept request from different hosts
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping
    public List<UserProfile> getAlUserProfiles(){
        return userProfileService.getUserProfiles();
    }

    @PostMapping(
            path = "{userProfileID}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE, // accept a specific data sent from the client
            produces = MediaType.APPLICATION_JSON_VALUE // specific the media type sent back to the client
    )
    public void uploadUserProfileImage(@PathVariable("userProfileID") UUID userProfileID,
                                       @RequestParam("file")MultipartFile file)
    {
        userProfileService.uploadUserProfileImage(userProfileID,file);
    }

    @GetMapping("{userProfileID}/image/download")
    public byte[] downloadUserProfileImage(@PathVariable("userProfileID") UUID userProfileID){
        return userProfileService.downloadUserProfileImage(userProfileID);
    }
}
