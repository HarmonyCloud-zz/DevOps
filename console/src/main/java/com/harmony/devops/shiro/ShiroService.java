package com.harmony.devops.shiro;

import com.harmony.devops.repository.resource.ResourcesRepository;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/8/31
 * @描述
 */
@Service
public class ShiroService {
    @Resource
    private ShiroFilterFactoryBean shiroFilterFactoryBean;
    @Autowired
    ResourcesRepository resourcesRepository;

    /**
     * 初始化权限
     */
    private Map<String, String> loadFilterChainDefinitions() {
        // 权限控制map.从数据库获取
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/logout", "logout");
        //swagger跳过权限认证
        filterChainDefinitionMap.put("/swagger-ui.html/**", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");

        resourcesRepository.findAll().forEach(
                resource -> filterChainDefinitionMap.put(resource.getResourcesUrl(), "perms[" + resource.getResourcesCode() + "]"));

        filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;
    }

    /**
     * 重新加载权限
     */
    public void updatePermission() {
        synchronized (shiroFilterFactoryBean) {
            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean
                        .getObject();
            } catch (Exception e) {
                throw new RuntimeException(
                        "get ShiroFilter from shiroFilterFactoryBean error!");
            }
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
                    .getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
                    .getFilterChainManager();

            // 清空老的权限控制
            manager.getFilterChains().clear();

            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean
                    .setFilterChainDefinitionMap(loadFilterChainDefinitions());
            // 重新构建生成
            shiroFilterFactoryBean.getFilterChainDefinitionMap().entrySet().forEach(
                    entry->manager.createChain(entry.getKey(),entry.getValue().trim().replace(" ","")));

            System.out.println("更新权限成功！！");
        }
    }


}
