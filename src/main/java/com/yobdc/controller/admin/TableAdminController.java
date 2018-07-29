package com.yobdc.controller.admin;

import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.POST;
import com.yobdc.controller.BaseController;
import com.yobdc.model.Table;

public class TableAdminController extends BaseController {
    public final static String CONTROLLER_KEY = "/admin/table";

    public void index() {
        Long databaseId = getParaToLong(0);
        renderFreeMarker("/views/pages/admin/table/list.ftl");
    }

    public void edit() {
        Long tableId = getParaToLong(0);
        Table table = Table.dao.findById(tableId);
        setAttr("table", table);
        renderFreeMarker("/views/pages/admin/table/edit.ftl");
    }

    @Before(POST.class)
    public void doSave() {
        Table model = getModel(Table.class, "table");
        if (model.get("id") == null) {
            model.save();
        } else {
            model.update();
        }
        redirect(getHeader("Referer"));
    }
}
