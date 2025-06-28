package com.example.advanced_java_project.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HomeController {

/*    @GetMapping("/")
    public String home(Principal principal) {
        return "hello " + principal.getName();
    }

    @GetMapping("/set-session")
    @ResponseBody
    public String setSession(HttpSession session) {
        session.setAttribute("username", "UtilisateurDemo");
        return "Session créée avec username = UtilisateurDemo";
    }

    @GetMapping("/get-session")
    @ResponseBody
    public String getSession(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return username != null ? "Username en session : " + username : "Aucun username en session";
    }*/
}
