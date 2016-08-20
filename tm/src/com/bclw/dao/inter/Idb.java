package com.bclw.dao.inter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public interface Idb {

	public void close(Connection conn, Statement stmt, ResultSet rs);
}
