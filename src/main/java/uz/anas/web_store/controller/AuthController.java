package uz.anas.web_store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.anas.web_store.model.request.UserRequestDto;
import uz.anas.web_store.service.UserService;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error,@RequestParam(required = false) String username, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("username", username);
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute UserRequestDto userRequestDto, Model model) {
        model.addAttribute("user", userRequestDto);
        if (userService.existUser(userRequestDto.getEmail())) {
            model.addAttribute("emailInUse", true);
            return "sign-up";
        }
        if (userService.notEqualPassword(userRequestDto)) {
            model.addAttribute("confirmPasswordWrong", true);
            return "sign-up";
        }
        userService.saveUser(userRequestDto);
       return "/login";
    }

}
