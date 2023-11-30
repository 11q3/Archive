package Archive.web.dto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/login")
    public String login() {
        return "login_page";
    }

    @GetMapping("/account")
    public String account() {
        return "account_page";
    }

    @GetMapping("/docks")
    public String docks() {
        return "docks_page";
    }
}
