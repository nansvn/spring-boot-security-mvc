package com.springproject.securitymvc.controller;

import com.springproject.securitymvc.entity.User;
import com.springproject.securitymvc.service.UserService;
import com.springproject.securitymvc.user.RegisterUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final Logger logger = Logger.getLogger(getClass().getName());
    private final UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/registration-form")
    public String showMyLoginPage(Model theModel) {
        theModel.addAttribute("registerUser", new RegisterUser());
        return "register/registration-form";
    }

    @PostMapping("/process-registration-form")
    public String processRegistrationForm(
            @Valid
            @ModelAttribute("registerUser")
            RegisterUser registerUser,
            BindingResult theBindingResult,
            HttpSession session, Model theModel) {

        String userName = registerUser.getUserName();
        logger.info("Processing registration form for: " + userName);

        // form validation
        if (theBindingResult.hasErrors()) {
            return "register/registration-form";
        }

        // check the database if user already exists
        User user = userService.findByUserName(userName);
        if (user != null) {
            theModel.addAttribute("registerUser", new RegisterUser());
            theModel.addAttribute("registrationError", "User name already exists");

            logger.warning("User name already exists");
            return "register/registration-form";
        }

        // create user account and store in the database
        userService.save(registerUser);

        logger.info("Successfully created user: " + userName);

        // place user in the web http session for later use
        session.setAttribute("user", registerUser);

        return "register/registration-confirmation";
    }
}
