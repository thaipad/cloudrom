/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom.common.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pro.thaipad.cloudrom.AuthorizedUser;
import pro.thaipad.cloudrom.common.exceptions.NotFoundException;
import pro.thaipad.cloudrom.instances.entity.Os;
import pro.thaipad.cloudrom.instances.service.InstanceService;
import pro.thaipad.cloudrom.users.entity.Role;
import pro.thaipad.cloudrom.users.entity.User;
import pro.thaipad.cloudrom.users.service.UserService;

import java.util.Arrays;

@Controller
public class RootController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private InstanceService instance;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model) {

        AuthorizedUser user = AuthorizedUser.safeGet();
        model.addAttribute("user", user.getUser().getName());
        model.addAttribute("email", user.getUser().getEmail());

        return "index";
    }

    @GetMapping("/instances")
    public String instances(Model model) {

        AuthorizedUser user = AuthorizedUser.safeGet();
        model.addAttribute("user", user.getUser().getName());
        model.addAttribute("email", user.getUser().getEmail());
        model.addAttribute("os", Arrays.asList(Os.values()));

        return "instances";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/reloginonerror")
    public String reloginOnError(ModelMap model) {
        model.put("errorReg", "login.note.user_exist");
        return "login";
    }

    @GetMapping("/reloginonsuccess")
    public String reloginOnSuccess(ModelMap model) {
        model.put("successReg", "login.note.success_registration");
        return "login";
    }

    @PostMapping(value = "/registration")
    public String registration(@RequestParam("name") String name,
                               @RequestParam("username") String email,
                               @RequestParam("password") String password,
                               @RequestParam("repeat") String repeat) {

        try {
            userService.getUserByEmail(email);
            return "redirect:/reloginonerror";
        } catch (NotFoundException e) {
            User user = new User(null, name, email, password, Role.ROLE_USER);
            user.setEnabled(true);
            userService.createUser(user);
            return "redirect:/reloginonsuccess";
        }
    }

}
