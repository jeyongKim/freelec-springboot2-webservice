package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    /*@GetMapping("/")
    public String index(){
        return "index";
    }*/

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    /*
    * Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
    * 여기서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달.
    * */
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }


}
