package com.pelime.ebanck.server.api;

import com.pelime.ebanck.server.domain.User;
import com.pelime.ebanck.server.service.AuthenticationService;
import com.pelime.ebanck.server.service.UserService;
import com.pelime.ebanck.server.shiro.MyToken;
import com.pelime.ebanck.server.support.EbanckCode;
import com.pelime.ebanck.server.support.EbanckHttpEntity;
import com.pelime.ebanck.server.support.StaticCommon;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserService userService;

    /**
     *
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        MyToken token=new MyToken(username, password);
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return new EbanckHttpEntity(EbanckCode.BAD_CERTIFIED,"未知账户");
        } catch (IncorrectCredentialsException ice) {
            return new EbanckHttpEntity(EbanckCode.BAD_CERTIFIED,"密码不正确");
        } catch (LockedAccountException lae) {
            return new EbanckHttpEntity(EbanckCode.BAD_CERTIFIED,"账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            return new EbanckHttpEntity(EbanckCode.BAD_CERTIFIED,"用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            return new EbanckHttpEntity(EbanckCode.BAD_CERTIFIED,"用户名或密码不正确！");
        }
        if (subject.isAuthenticated()) {
            return new EbanckHttpEntity(EbanckCode.SUCCESS,"null");
        } else {
            token.clear();
            return new EbanckHttpEntity(EbanckCode.BAD_CERTIFIED,"登录失败");
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Object regiest(@RequestParam("principal") String principals,@RequestParam("password") String password){
        User user =authenticationService.register(principals,password);
        if(user==null){
            return new EbanckHttpEntity(EbanckCode.USER_EXISTS,"用户已存在");
        }
        userService.register(user);
        return new EbanckHttpEntity(EbanckCode.USER_EXISTS,"注册成功");
    }



    public static String simpleEncrypt(String data){
        AesCipherService aesCipherService=new AesCipherService();
        aesCipherService.setKeySize(128);
        aesCipherService.generateNewKey();
        String var1= aesCipherService.encrypt("123456".getBytes(), StaticCommon.WEB_SECURITY_SECRECT.getBytes()).toHex();
        System.out.println(var1);
        String var2=new String(aesCipherService.decrypt(Hex.decode(var1),StaticCommon.WEB_SECURITY_SECRECT.getBytes()).getBytes());
        System.out.println(var2);
        System.out.println(new String(new AesCipherService().decrypt(Hex.decode(var1),StaticCommon.WEB_SECURITY_SECRECT.getBytes()).getBytes()));
        return "";
    }
}
