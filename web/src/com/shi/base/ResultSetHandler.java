package com.shi.base;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * 处理结果集接口
 */
public interface ResultSetHandler {
	public Object doHandler(ResultSet rs) throws SQLException;
}
