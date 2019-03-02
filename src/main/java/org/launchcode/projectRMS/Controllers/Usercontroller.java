package org.launchcode.projectRMS.Controllers;

import org.launchcode.projectRMS.models.LoginUser;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class Usercontroller{

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("users", userDao.findAll());
        model.addAttribute("title", "List of Users");
        return "user/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displaySignupForm(Model model){
        model.addAttribute("title", "Sign-up here");
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processSignupForm(@ModelAttribute @Valid User newUser, Errors errors,
                                    HttpServletRequest request, Model model) {
        User alreadyExists = userDao.findByEmail(newUser.getEmail());

        if(alreadyExists != null){
            model.addAttribute("error", "Email already exists. Try another");
            return "user/add";
        }

        if (errors.hasErrors()){
            model.addAttribute("title", "Sign-up here");
            model.addAttribute("error", "One or more fields invalid");
            return "user/add";
        }
        if (newUser.getPassword() != null || newUser.getVerify() != null) {
            if ((newUser.getPassword().equals(newUser.getVerify())) && (newUser.getPassword().length()>=6)){
                userDao.save(newUser);
            } else {
                model.addAttribute("error", "Password don't match");
                newUser.setPassword(null);
                return "user/add";
            }
        } else {
            model.addAttribute("error", "One or more fields invalid");
            newUser.setPassword(null);
            return "user/add";
        }
        model.addAttribute("user", newUser.getFirstName() + ' ' + newUser.getLastName());
        model.addAttribute("message", "Sign-up successful");
        return "redirect:view/"+newUser.getId();

    }

//    @RequestMapping(value="view/{id}", method = RequestMethod.GET)
//    public String viewUser(@PathVariable int id, Model model){
//        User user = userDao.findOne(id);
//        model.addAttribute("title", user.getFirstName() + ' ' + user.getLastName());
//        model.addAttribute("user", user.getFirstName() + ' ' + user.getLastName());
//        return "user/view";
//    }

    @RequestMapping(value = "/delete/{userId}")
    public String delete(@PathVariable int userId, Model model){
        userDao.delete(userId);
        model.addAttribute("message", "User deleted successfully!");
        return "recipe/message";
    }


    @RequestMapping(value="login", method = RequestMethod.GET)
    public String displayLoginForm(Model model){
        model.addAttribute("title", "Login");
        model.addAttribute("LoginUser", new LoginUser());
        return "user/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processLoginForm(Model model, @ModelAttribute @Valid LoginUser loginUser, Errors errors,
                                   HttpServletRequest request){
        if (!errors.hasErrors()){
            User matchUser = userDao.findByEmail(loginUser.getEmail());
            if (matchUser == null){
                model.addAttribute("error", "No user found under this email");
                return "user/login";
            }
            if (matchUser != null && loginUser.getPassword().equals(matchUser.getPassword())){
                model.addAttribute("user", matchUser.getEmail());
                return "user/view";
            }
        }
        model.addAttribute("title", "Login");
        model.addAttribute("error", "Email/ Password don't match");
        return "user/login";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String processLogOutForm(HttpServletRequest request, Model model){
        request.getSession().invalidate();
        model.addAttribute("message", "Logged Out Successfully!");
        model.addAttribute("LoginUser", new LoginUser());
        return "user/logOutMsg";
    }

}
