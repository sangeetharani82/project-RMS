package org.launchcode.projectRMS.Controllers;

import org.launchcode.projectRMS.models.User;
import org.launchcode.projectRMS.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("users", userDao.findAll());
        model.addAttribute("title", "List of Users");
        return "user/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("title", "Sign-up here");
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User newUser, Errors errors) {

        if (errors.hasErrors()){
            model.addAttribute("title", "Sign-up here");
            model.addAttribute(newUser);
            return "user/add";
        }
        if (newUser.getPassword() != null || newUser.getVerify() != null) {
            if (newUser.getPassword().equals(newUser.getVerify()) && newUser.getPassword().length() >= 6){
                userDao.save(newUser);
            } else {
                model.addAttribute("errors", "Password don't match");
                newUser.setPassword(null);
                return "user/add";
            }
        } else {
            model.addAttribute("errors", "One or more fields invalid");
            newUser.setPassword(null);
            return "user/add";
        }
        return "redirect:view/"+newUser.getId();
    }

    @RequestMapping(value="view/{id}", method = RequestMethod.GET)
    public String viewUser(@PathVariable int id, Model model){
        User user = userDao.findOne(id);
        model.addAttribute("title", user.getFirstName() + ' ' + user.getLastName());
        model.addAttribute("user", user);
        return "user/view";
    }

    @RequestMapping(value="login", method = RequestMethod.GET)
    public String displayLoginForm(Model model){
        model.addAttribute("title", "Login");
        return "user/login";
    }

}
