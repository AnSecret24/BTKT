package com.shopvn.controller;

import com.shopvn.entity.User;
import com.shopvn.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult result,
                           @RequestParam("confirmPassword") String confirmPassword,
                           Model model) {

        // check confirm password
        if (!user.getPassword().equals(confirmPassword)) {
            result.rejectValue("password", "error.user", "Mật khẩu không trùng khớp");
        }

        // check username tồn tại
        if (userService.existsByUsername(user.getUsername())) {
            result.rejectValue("username", "error.user", "Username đã tồn tại");
        }

        // check email tồn tại
        if (userService.existsByEmail(user.getEmail())) {
            result.rejectValue("email", "error.user", "Email đã tồn tại");
        }

        if (result.hasErrors()) {
            return "register";
        }

        userService.save(user);

        model.addAttribute("success", "Đăng ký thành công!");
        model.addAttribute("user", new User());
        return "register";
    }
}