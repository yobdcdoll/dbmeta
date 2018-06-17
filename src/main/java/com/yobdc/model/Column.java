package com.yobdc.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * Created by lex on 2017/11/22.
 */
public class Column extends Model<Column> {
    public static final Column dao = new Column().dao();


    public List<Column> searchColumnWithTable(String keyword) {
        String keywordLike = "%" + keyword + "%";
        return Column.dao.find("select \n" +
                "db_column.id column_id\n" +
                ",db_column.name column_name\n" +
                ",db_column.remark column_remark\n" +
                ",db_column.table_id table_id\n" +
                ",db_table.name table_name\n" +
                ",db_table.remark table_remark\n" +
                "from db_column\n" +
                "left join db_table on db_column.table_id = db_table.id\n" +
                "where db_table.name like ?\n" +
                "or db_table.remark like ?\n" +
                "or db_column.name like ?\n" +
                "or db_column.remark like ?", keywordLike, keywordLike, keywordLike, keywordLike);
    }
}
