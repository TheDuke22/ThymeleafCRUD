package com.example.thymeleafcrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView getUsers (Model model){
        List<User> users = userService.getUsers();
        model.addAttribute("users",users);
        model.addAttribute("user",new User());
        return new ModelAndView("users", (Map<String, ?>) model);
    }



    @RequestMapping(path = "/", method = RequestMethod.POST)
    public RedirectView createUser(RedirectAttributes redirectAttributes, @ModelAttribute User user) {
        userService.createUser(user);
        String message = "Created user <b>" + user.getNome() + " " + user.getCognome() + "</b> ✨." + "<div class=\"tenor-gif-embed\" data-postid=\"11766934\" data-share-method=\"host\" data-aspect-ratio=\"1.35326\" data-width=\"15%\">" +
                "                                                                        <a href=\"https://tenor.com/view/mission-complete-spongebob-done-gif-11766934\">Mission Complete Spongebob GIF</a>from " +
                "                                                                        <a href=\"https://tenor.com/search/mission+complete-gifs\">Mission Complete GIFs</a>" +
                "                                                                        </div> <script type=\"text/javascript\" async src=\"https://tenor.com/embed.js\"></script>";
        RedirectView redirectView = new RedirectView("/", true);
        redirectAttributes.addFlashAttribute("userMessage", message);
        return redirectView;
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String getUser(Model model,@PathVariable("id") Long id) throws ChangeSetPersister.NotFoundException {
        User user=userService.getUser(id);
        model.addAttribute("user",user);
        return"edit";}

    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public RedirectView updateUser(RedirectAttributes redirectAttributes,@PathVariable("id") Long id,@ModelAttribute User user) throws ChangeSetPersister.NotFoundException {
        userService.updateUser(id, user);
        String message = (user.isActive() ? "Updated " : "Deleted ") + " user <b>" + user.getNome() + " " + user.getCognome() + "</b> ✨." + "<div class=\"tenor-gif-embed\" data-postid=\"24764953\" data-share-method=\"host\"" +
                "                                                                                                                data-aspect-ratio=\"1\" data-width=\"15%\"><a href=\"https://tenor.com/view/sakshi-okay-gif-24764953\"" +
                "                                                                                                               >Sakshi Okay GIF</a>from <a href=\"https://tenor.com/search/sakshi-gifs\">Sakshi GIFs</a></div> " +
                "                                                                                                                <script type=\"text/javascript\" async src=\"https://tenor.com/embed.js\"></script>";
        RedirectView redirectView = new RedirectView("/", true);
        redirectAttributes.addFlashAttribute("userMessage", message);
        return redirectView;
    }

}
