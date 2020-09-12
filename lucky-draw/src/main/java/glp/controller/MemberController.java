package glp.controller;

import glp.model.Member;
import glp.model.User;
import glp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/add")
    public Object add(@RequestBody Member member,HttpSession session){
        User user = (User)session.getAttribute("user");
        member.setUserId(user.getId());
        memberService.add(member);
        return null;
    }

    @PostMapping("/update")
    public Object update(@RequestBody Member member){
        memberService.update(member);
        return null;
    }

    @GetMapping("/delete/{id}")
    public Object delete(@PathVariable Integer id){
        memberService.delete(id);
        return null;
    }
}
