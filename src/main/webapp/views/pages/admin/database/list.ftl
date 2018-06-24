<#include "/views/common/header.ftl">

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Main content -->
    <section class="content">
        <!-- Small boxes (Stat box) -->
        <div class="row">
            <div class="col-xs-12">
                <a class="btn btn-primary" href="${ctx}/admin/database/create">新增</a>
            </div>
            <div class="col-xs-12">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th class="col-xs-1">操作</th>
                        <th class="col-xs-1">数据库名</th>
                        <th class="col-xs-1">中文名称</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list dbs.list as item>
                    <tr>
                        <td>
                            <a href="${ctx}/admin/database/edit/${item.id}">编辑</a>
                            <a href="javascript:void(0)">删除</a>
                        </td>
                        <td>${item.name!}</td>
                        <td>${item.nickname!}</td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
            <!-- ./col -->
        </div>
        <!-- /.row -->

    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<#include "/views/common/footer.ftl">