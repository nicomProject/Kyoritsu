package com.enicom.board.kyoritsu.api.controller;

import com.enicom.board.kyoritsu.login.MemberDetail;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final SecurityUtil securityUtil;

    @GetMapping(path = {"/"})
    public String home(Model model) throws Exception {
        return "main/index";
    }

    @GetMapping("/{page}")
    public String main(Model model, HttpServletResponse response, @PathVariable String page) throws IOException {
        String view = page;
        return String.format("main/%s", view);
    }

    @GetMapping(path = { "/admin"})
    public String admin(Model model) throws Exception {
        return login(model);
    }

    @GetMapping(path = "/admin/login")
    public String login(Model model) throws Exception {
        MemberDetail member = getCurrentUser(model);
        if (member == null) {
            return "admin/login";
        }

        return "admin/index";
    }

    /**
     * Logout
     */
    @GetMapping("/admin/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        response.sendRedirect("/admin");
    }

    @GetMapping("/admin/{page}")
    public String admin(Model model, HttpServletResponse response, @PathVariable String page) throws IOException {
        MemberDetail member = getCurrentUser(model);
        if (member == null || page.equalsIgnoreCase("login")) {
            response.sendRedirect("/admin");
        }

        String view = page;
        if (page.equalsIgnoreCase("dashboard")) {
            view = "index";
        }

        return String.format("admin/%s", view);
    }

    @GetMapping("/admin/{page}/detail")
    public String adminDetail(Model model, HttpServletResponse response, @PathVariable String page) throws IOException {
        MemberDetail member = getCurrentUser(model);
        if (member == null || page.equalsIgnoreCase("login")) {
            response.sendRedirect("/admin");
        }

        return String.format("admin/detail/%s", page);
    }
    @GetMapping("/admin/{page}/detail/{key}")
    public String adminDetail(Model model, HttpServletResponse response, @PathVariable String page, @PathVariable String key) throws IOException {
        MemberDetail member = getCurrentUser(model);
        if (member == null || page.equalsIgnoreCase("login")) {
            response.sendRedirect("/admin");
        }

        model.addAttribute("key", key);
        return String.format("admin/detail/%s", page);
    }

    @RequestMapping(path = "/modal/{page}", method = {RequestMethod.GET})
    public String modal(@PathVariable String page, Model model, @RequestParam Map<String, Object> paramMap) {
        model.addAllAttributes(paramMap);
        return String.format("modal/%s", page);
    }

    @GetMapping(path = {"/favicon", "/favicon.ico"})
    public String favicon() {
        return "forward:/static/images/favicon/favicon.ico";
    }

    @GetMapping(path = {"/error"})
    public String error() throws IOException {
        return "error";
    }

    @GetMapping(path = {"/sessionExpired", "/sessionInvalid"})
    public void session(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        response.sendRedirect("/");
    }


    private MemberDetail getCurrentUser(Model model) {
        MemberDetail member = securityUtil.getCurrentUser();

        if (member != null) {
            model.addAttribute("member_id", member.getUsername());
            model.addAttribute("member_name", member.getName());
            model.addAttribute("member_authorities", member.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
            return member;
        } else {
            model.addAttribute("member_authorities", Collections.emptyList());
        }

        return null;
    }
}
