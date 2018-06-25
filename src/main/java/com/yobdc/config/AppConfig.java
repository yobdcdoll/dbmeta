package com.yobdc.config;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.RenderManager;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.yobdc.kit.error.ErrorKit;
import com.yobdc.model.*;
import com.yobdc.plugin.shiro.ShiroPlugin;
import com.yobdc.plugin.shiro.ShiroInterceptor;

/**
 * Created by lex on 2017/11/22.
 */
public class AppConfig extends JFinalConfig {
    private static Routes routes = new RouteConfig();

    public void configConstant(Constants me) {
        PropKit.use("config.properties");
        me.setDevMode(PropKit.getBoolean("devMode"));
        me.setViewType(ViewType.FREE_MARKER);

        /**
         * 自定义错误页面
         */
        ErrorKit.configErrorViews(me);
    }

    public void configRoute(Routes me) {
        me.add(routes);
    }

    public void configEngine(Engine me) {
    }

    public void configPlugin(Plugins me) {
        DruidPlugin dsMysql = new DruidPlugin(
                PropKit.get("jdbcUrl")
                , PropKit.get("user")
                , PropKit.get("password")
        );
        me.add(dsMysql);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dsMysql);
        arp.addMapping("sys_user", User.class);
        arp.addMapping("sys_role", Role.class);
        arp.addMapping("sys_permission", Permission.class);
        arp.addMapping("db_table", Table.class);
        arp.addMapping("db_column", Column.class);
        arp.addMapping("db_database", Database.class);
        me.add(arp);

        me.add(new ShiroPlugin(routes));
    }

    public void configInterceptor(Interceptors me) {
        me.add(new ShiroInterceptor());
        me.add(new SessionInViewInterceptor());
    }

    public void configHandler(Handlers me) {
        me.add(new ContextPathHandler("ctx"));
    }

    public static void main(String[] args) {
        JFinal.start("src/main/webapp", 8080, "/");
    }
}
