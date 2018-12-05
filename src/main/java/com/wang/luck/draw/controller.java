package com.wang.luck.draw;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.luck.draw.entity.User;
import com.wang.luck.draw.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class controller {

    @Autowired
    private UserMapper mapper;

    private List<String> configList = new ArrayList<String>(){
        {
            add("沈阳康泰电子科技股份有限公司");
            add("辽宁金誉科技工程有限公司");
            add("易迅科技股份有限公司");
            add("沈阳昂立信息技术有限公司");
        }
    };

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String page () {
        return "index";
    }

    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public String config () {
        return "config";
    }

    @ResponseBody
    @GetMapping(value = "/list")
    public HttpResult<List<User>> list(){
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        return new HttpResult<>(mapper.selectList(wrapper));
    }

    @ResponseBody
    @GetMapping(value = "/yesList")
    public HttpResult<List<User>> yesList(){
        return new HttpResult<>(this.userYesList());
    }

    @ResponseBody
    @GetMapping(value = "/delete")
    public HttpResult<Boolean> delete( String name ){
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.setEntity(new User().setName(name));
        return new HttpResult<>( mapper.delete(wrapper) > 0 );
    }

    @ResponseBody
    @GetMapping(value = "/deleteAll")
    public HttpResult<Boolean> deleteAll(){
        return new HttpResult<>(mapper.deleteAll() > 0);
    }

    @ResponseBody
    @GetMapping(value = "/save")
    public HttpResult<Boolean> save( String name ){
        User user = new User().setName(name);
        if(configList.contains(name)) user.setState("Yes");
        else user.setState("No");
        return new HttpResult<>(mapper.insert(user) > 0);
    }

    @ResponseBody
    @PostMapping(value = "/configHandle")
    public HttpResult<List<User>> configHandle(@RequestParam(value = "names[]", required=false)List<String> names){
        mapper.updateForNo();
        if( names!=null )names.forEach( name -> mapper.updateForYes(name) );
        return new HttpResult<>(this.userYesList());
    }

    @ResponseBody
    @GetMapping(value = "/handle")
    public HttpResult<List<String>> handle(Integer num){
        List<String> result = new ArrayList<>();

        List<User> userList = this.userYesList();
        int yesNum = userList == null ? 0 : userList.size();

        if( yesNum == num ) userList.forEach( user -> result.add(user.getName()));
        else if( yesNum > num ) result.addAll(this.gt(userList, num));
        else if( yesNum < num ) result.addAll(this.lt(userList, num));

        return new HttpResult<>(result);
    }

    /**
     * 当设置参与抽奖的用户小于选定数时的算法
     * @param userList 参与抽奖的用户
     * @param num 选定数
     * @return
     */
    private List<String> lt( List<User> userList, Integer num ){
        List<String> result = new ArrayList<>();

        //获取必然中奖的用户
        userList.forEach( user -> result.add(user.getName()));

        //随机选取非必然中奖的用户
        int yesNum = userList == null ? 0 : userList.size();
        result.addAll(this.gt(this.userNoList(), num - yesNum));

        return result;
    }

    /**
     * 当设置参与抽奖的用户大于选定数时的算法
     * @param userList 参与抽奖的用户
     * @param num 选定数
     * @return
     */
    private List<String> gt(List<User> userList, Integer num){
        List<String> result = new ArrayList<>();

        List<Integer> iList = new ArrayList<>();
        Random ra =new Random();
        for (int i=0; i<num; i++){
            int r;
            do { r = ra.nextInt(userList.size()); }
            while ( iList.contains(r) );    //排除以选中的用户

            iList.add(r);
        }

        iList.forEach( i -> result.add( userList.get(i).getName() ) );
        return result;
    }

    /**
     * 获取参与抽奖的用户
     * @return
     */
    private List<User> userYesList(){
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.setEntity(new User().setState("Yes"));
        return mapper.selectList(wrapper);
    }

    /**
     * 获取不参与抽奖的用户
     * @return
     */
    private List<User> userNoList(){
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.setEntity(new User().setState("No"));
        return mapper.selectList(wrapper);
    }
}
