package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @Autowired
    CredentialRepository credentialRepository;

    @GetMapping("/")
    public String getLandingPage() {
        return "landingpage";
    }

    @GetMapping("/save")
    public String saveCredential() {
        Credential cd = new Credential();
        cd.setPassword("harsh");
        cd.setPassword("2025");
        credentialRepository.save(cd);
        return "New saved";


    }

    @PostMapping("/signup")
    public String signup(@RequestParam("username") String username, @RequestParam("password") String password)
    { Credential credential = new Credential();
        credential.setUsername("username");
        credential.setPassword("password");
        credentialRepository.save(credential);
        return "dashboard";}

    @PostMapping("/login")
    public String signin(@RequestParam("username") String username, @RequestParam("password") String password) {
        Credential credential = credentialRepository.findByUsername(username);
        if (credential != null && credential.getPassword().equals(password)) {
            return "dashboard";
        } else {
            return "redirect:/login?error";
        }
    }
}