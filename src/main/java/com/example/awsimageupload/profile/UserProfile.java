package com.example.awsimageupload.profile;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

// use lombok 
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserProfile {

    private UUID id;
    private String username;
    private String userProfileImageLink; // S3 key

}
