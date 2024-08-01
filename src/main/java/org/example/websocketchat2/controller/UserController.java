package org.example.websocketchat2.controller;

import lombok.RequiredArgsConstructor;
import org.example.websocketchat2.entity.UserEntity;
import org.example.websocketchat2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/loginform")
    public String loginForm() {
        return "users/loginform";
    }

    @GetMapping("/loginerror")
    public String loginError() {
        return "users/loginerror";
    }

    @GetMapping("/userregform")
    public String userRegForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "users/userregform";
    }

    @PostMapping("/userreg")
    public String userReg(@ModelAttribute("user") UserEntity user, BindingResult result) {
        if (result.hasErrors()) {
            return "users/userregform";
        }

        if (userService.nameExists(user.getName())) {
            result.rejectValue("name", null, "이미 사용 중인 아이디입니다.");
            return "users/userregform";
        }

        userService.registerNewUser(user);
        return "redirect:/welcome";
    }
}