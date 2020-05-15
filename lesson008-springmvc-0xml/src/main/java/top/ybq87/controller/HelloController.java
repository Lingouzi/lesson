package top.ybq87.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ybq87.service.UserService;

/**
 * springmvc 中 controller 的定义方式有 2 种类型，3 种方式
 * 2 类：BeanName 类型、@Controller 注解类型【体现在 HandlerMapping】
 * 3 种：实现 HttpRequestHandler 接口、实现 Controller 接口、使用 @Controller 注解【体现在 HandlerAdapter】
 *
 * 参考：https://www.bilibili.com/video/BV1f7411K7UE?p=3
 * 4 分 47 秒
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/18
 */
@Controller
public class HelloController {
    
    @Autowired
    private UserService userService;
    
    @ResponseBody
    @RequestMapping("/hello")
    public Object hello(String name) {
        return userService.say(name);
    }
    
}
