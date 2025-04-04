package systementor.securelogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import systementor.securelogin.model.UserModel;
import systementor.securelogin.service.UserService;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loadForm(Model model) {
        model.addAttribute("user", new UserModel());
        return "login";
    }

    @GetMapping("/register")
    public String loadRegisterForm(Model model) {
        model.addAttribute("user", new UserModel());
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@RequestParam String username,
                                 @RequestParam String password,
                                 Model model) {
        /*TODO
         * Register a user
         * Check if user already exists in our DB(Be a nice developer and tell the user that username is already in use)
         * */
        return "redirect:/login";
    }


    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              Model model) {
        return userService.findByUsername(username)
                .filter(hashed -> new BCryptPasswordEncoder().matches(password, hashed))
                .map(found -> {
                    model.addAttribute("username", username);
                    return "welcome";
                })
                .orElseGet(() -> {
                    UserModel failedUser = new UserModel();
                    failedUser.setUsername(username);
                    model.addAttribute("user", failedUser);
                    model.addAttribute("error", "Fel användarnamn eller lösenord.");
                    return "login";
                });
    }

}
