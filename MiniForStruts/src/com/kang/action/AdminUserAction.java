package com.kang.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import com.kang.POJO.AdminUser;
import com.kang.db.DBConn;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminUserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	public String action;
	public String adminusername;
	public String adminuserpassword;
	public String adminuserrole;
	
	@Override
	public String execute() throws Exception {
		Connection conn=DBConn.createDBConn();
		//----�����Ҫ����һ���û�---
		if("add".equals(action)){
			String sql="select * from adminuser where adminusername=?";
			PreparedStatement preSQLSelect=conn.prepareStatement(sql);
			preSQLSelect.setString(1,adminusername);
			ResultSet rs=preSQLSelect.executeQuery();
			if(!rs.next()){//û������û�
				sql="insert into adminuser(adminuserName,adminuserpassword,adminuserrole) values(?,?,?)";
				PreparedStatement preSQLInsert=conn.prepareStatement(sql);
				preSQLInsert.setString(1,adminusername);
				preSQLInsert.setString(2,adminuserpassword);
				preSQLInsert.setString(3,adminuserrole);
				preSQLInsert.executeUpdate();
			}
		}
		//----�����ɾ��һ���û�----
		if("del".equals(action)){
			String sql="delete from adminuser where adminusername=?";
			PreparedStatement preSQLDel=conn.prepareStatement(sql);
			preSQLDel.setString(1,adminusername);
			preSQLDel.executeUpdate();
		}
		//----��ѯ�����еİ༶����----
		String sql="select * from adminuser";
		Statement state=conn.createStatement();
		ResultSet rs=state.executeQuery(sql);
		ArrayList<AdminUser> userArray=new ArrayList<AdminUser>();
		while(rs.next()){
			AdminUser user=new AdminUser();
			user.setAdminusername(rs.getString("adminusername"));
			user.setAdminuserrole(rs.getInt("adminuserrole"));
			user.setAdminuserpassword(rs.getString("adminusername"));
			userArray.add(user);
		}
		Map<String,ArrayList<AdminUser>> request = (Map<String,ArrayList<AdminUser>>)ActionContext.getContext().get("request");
		request.put("userArray", userArray);
		DBConn.closeConn(conn);
		return SUCCESS;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAdminusername() {
		return adminusername;
	}

	public void setAdminusername(String adminusername) {
		this.adminusername = adminusername;
	}

	public String getAdminuserpassword() {
		return adminuserpassword;
	}

	public void setAdminuserpassword(String adminuserpassword) {
		this.adminuserpassword = adminuserpassword;
	}

	public String getAdminuserrole() {
		return adminuserrole;
	}

	public void setAdminuserrole(String adminuserrole) {
		this.adminuserrole = adminuserrole;
	}
	
	

}
