package com.prasant.service;

import com.prasant.modal.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface UserService {

    @GetMapping("/api/user/profile")
    UserDto getUserProfile(@RequestHeader("Authorization") String jwt);
}
