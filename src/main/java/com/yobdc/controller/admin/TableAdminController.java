package com.yobdc.controller.admin;

import com.yobdc.controller.BaseController;

public class TableAdminController extends BaseController {
    public final static String CONTROLLER_KEY = "/admin/table";

    public void index() {
        Long databaseId = getParaToLong(0);
        renderFreeMarker("/views/pages/admin/table/list.ftl");
    }
}