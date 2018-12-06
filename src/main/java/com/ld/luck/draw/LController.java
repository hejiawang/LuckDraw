package com.ld.luck.draw;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description:
 * @Auther: HeJiawang
 * @Date: 2018/12/6
 */
@Controller
public class LController {

    @RequestMapping(value = "/indexL", method = RequestMethod.GET)
    public String page () {
        return "indexL";
    }
}
