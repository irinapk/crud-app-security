package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("")
public class UserController {

    private final UserDao userDao;
    private final RoleDao roleDao;

    @Autowired
    public UserController(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String viewAdminPage(Model model) {
        model.addAttribute("users", userDao.getAllUsers());
        return "users/admin";
    }

    @GetMapping("user/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDao.show(id));
        return "users/show";
    }

    @GetMapping("user/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        Set<Role> roles = roleDao.showAllRoles();
        model.addAttribute("allRoles", roles);
        return "users/new";
    }

    @PostMapping("user/new")
    public String create(@ModelAttribute("user") @Valid User user,
                         @RequestParam(value="roleID") int[] roleID,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/new";
        }
        Set<Role> roleSet = new HashSet<>();
        for (int i = 0; i < roleID.length; i++) {
           roleSet.add(roleDao.findRoleById(roleID[i]));
        }
        user.setRoles(roleSet);
        userDao.saveUser(user);
        return "redirect:/admin";
    }

    // EDITING EXISTING USERS
    @GetMapping("user/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDao.show(id));
        Set<Role> roles = roleDao.showAllRoles();
        model.addAttribute("allRoles", roles);
        return "users/edit";
    }

    @PatchMapping("user/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         @RequestParam(value="roleID") int[] roleID,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        Set<Role> roleSet = new HashSet<>();
        for (int i = 0; i < roleID.length; i++) {
            roleSet.add(roleDao.findRoleById(roleID[i]));
        }
        user.setRoles(roleSet);
        userDao.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("user/{id}")
    public String delete(@PathVariable("id") int id) {
        userDao.removeUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String viewUserInfo(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User user = userDao.getUserByName(username);
        model.addAttribute("user", user);
        return "users/user";
    }
}
