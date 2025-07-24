//URLへのアクセス受付とレスポンス
package io.github.masashi2.bbs.controller;
import io.github.masashi2.bbs.service.PostService;
import io.github.masashi2.bbs.service.RegisterService;
import io.github.masashi2.bbs.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import io.github.masashi2.bbs.model.Post;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class BbsController {

    private final PostService postService;
    private final LoginService loginService;
    private final RegisterService registerService;

    @Autowired
    public BbsController(PostService postService, LoginService loginService, RegisterService registerService) {
        this.postService = postService;
        this.loginService = loginService;
        this.registerService = registerService;
    }
    
    @GetMapping("/")
    public String index() {
        return "index";  
    }

    @GetMapping("/form")
    public String showPost() {
        return "form";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login")
    public String doLogin(
        @RequestParam("id") String id,
        @RequestParam("pw") String pw,
        Model model,
        HttpSession session
    ) {
        if (loginService.authenticate(id, pw)) {
            session.setAttribute("userId", id);//セッションにuserid保持
            return "redirect:/";
        } else {
            model.addAttribute("error", "Fail, try to login again");
            return "login";
        }
    }
    @PostMapping("/logout")
    public String logout(
        HttpSession session
    ) {
        session.invalidate();
        return "redirect:/";
    }
    

    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @PostMapping("/register")
    public String doRegister(
        @RequestParam("id") String id,
        @RequestParam("pw") String pw,
        Model model,
        RedirectAttributes redirectAttributes
    ) {
        boolean success = registerService.saveUser(id, pw);

        if (success) {
            redirectAttributes.addFlashAttribute("message", "Register success");//メッセージの一時保持
            return "redirect:/login";
        } else {
            model.addAttribute("error", "The user ID is already used");
            return "register";
        }
    }

    @GetMapping("/posts")
    public String listPosts(Model model) {
        Iterable<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "posts"; 
    }


    @PostMapping("/post")
    public String handlePost(
        @RequestParam("title") String title,
        @RequestParam("content") String content,
        Model model
    ){  
        Post post = postService.savePost(title, content);//DB保存
        model.addAttribute("post", post);

        return "result";
    }

    @PostMapping("/reply")
    public String handleReply(@RequestParam("postId") long postId,
                              @RequestParam("title") String title,
                              @RequestParam("content") String content,
                              Model model) {
        Post reply = postService.saveReply(postId, title, content);
        return "redirect:/posts";
    }
    
    

    //JSONを返す
    // @GetMapping()
    // @ResponseBody
    
}
