/**
 * Copyright (C), 2019, 1809B
 * FileName: ShiroConfig
 * Author:   zyf
 * Date:     2019/5/4 19:23
 * History:
 * zyf          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.shiro;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration//把当前类 作为spring配置文件使用
public class ShiroConfig {


    /**
     * shiro 的过滤器链
     *       shiro的核心总入口
     *
     * */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {

        System.out.println("ShiroConfiguration.shirFilter()");
        // shiro过滤器工厂
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager 如果不设置就无法完成认证和授权
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 过滤器链
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        // 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        // logout shiro定义好的过滤器名字 /logout访问路径
        // 浏览器访问的地址栏路径中以/logout结尾的路径 走logout过滤器
        // logout会清除session 退出登录
        //第一个参数是前台发送的路径
        filterChainDefinitionMap.put("/logout", "logout");
        // 所有的css文件走  anon过滤器 此过滤器代表放过拦截 不需要权限也能访问
        filterChainDefinitionMap.put("/css/**", "anon");
        // 放过登录页面拦截
        filterChainDefinitionMap.put("/toLogin2", "anon");
        filterChainDefinitionMap.put("/tree/findTree", "anon");
        filterChainDefinitionMap.put("/page/findgoodslist", "anon");
        filterChainDefinitionMap.put("/page/findDeng", "anon");
        filterChainDefinitionMap.put("/page/easYui1", "anon");
        filterChainDefinitionMap.put("/user/gainMessgerCode", "anon");
        filterChainDefinitionMap.put("/user/messagerlogin", "anon");
        filterChainDefinitionMap.put("/user/flieImgByID", "anon");
        filterChainDefinitionMap.put("/zhuCe", "anon");
        filterChainDefinitionMap.put("/send", "anon");
        filterChainDefinitionMap.put("/page/findZhuCe", "anon");
        filterChainDefinitionMap.put("/goods/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        /// **代表所有路径 除以上路径外都拦截 authc代表权限拦截过滤器
        filterChainDefinitionMap.put("/**", "authc");
        // perms权限过滤器 必须拥有某项权限才能访问对应路径
        // filterChainDefinitionMap.put("/add", "perms[user:query]");
        // 登录请求路径 登录页面提交form表单时 表单的action写此路径
        shiroFilterFactoryBean.setLoginUrl("/login2");
        // 登录成功跳转到登录成功页面
        shiroFilterFactoryBean.setSuccessUrl("/page/easYui");
        // 未授权界面;
        //shiroFilterFactoryBean.setUnauthorizedUrl("/warning");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        // 最终返回过滤器链
        return shiroFilterFactoryBean;

    }



    @Bean // 在xml文件中配置一个bean标签 相当于<bean class="
    // org.apache.shiro.mgt.SecurityManager"
    // name="securityManager"></bean>
    public SecurityManager securityManager() {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 设置realm. 域(数据源 用来连接数据库完成认证和授权)
        // 把自己创建的Realm 注入到securityManager中
        securityManager.setRealm(myShiroRealm());

        // 注入缓存管理器;
        //securityManager.setCacheManager(ehCacheManager());//
        // 这个如果执行多次，也是同样的一个对象;
        // securityManager.setRememberMeManager(rememberMeManager());

        return securityManager;

    }

    @Bean
    public Realm myShiroRealm(){
        return new MyRealm();
    }
}


