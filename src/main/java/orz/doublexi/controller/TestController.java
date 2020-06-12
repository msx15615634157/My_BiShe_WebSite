package orz.doublexi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import orz.doublexi.mapper.TestMapper;
import orz.doublexi.utils.ServletUtils;

import javax.sql.DataSource;

@Controller
public class TestController {
    public TestController(){
        System.out.println("generate");
    }
    @Autowired
    DataSource dataSource;
    @Autowired
    TestMapper testMapper;
@RequestMapping("test.do")
@ResponseBody
    public String test01(){
    return "孟双喜";
    }
    @RequestMapping("red.do")
    public String redirect(Model model){
        model.addAttribute("name", "doublexi");
        ServletUtils.getSession().setAttribute("username", "doublexi");
        return "test";
    }
}
class Bean{
    String name="双喜";
    String age="22";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
