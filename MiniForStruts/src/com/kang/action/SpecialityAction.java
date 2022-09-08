
package com.kang.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import com.kang.POJO.Speciality;
import com.kang.db.DBConn;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SpecialityAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	public String specialityname;
	public int specialityid;
	public String action;
	
	@Override
	public String execute() throws Exception {
		Connection conn=DBConn.createDBConn();
		//----�����Ҫ����һ��רҵ---
		if("add".equals(action)){
			String sql="select * from Speciality where SpecialityName=?";
			//רҵ���Ƿ����
			PreparedStatement preSQLSelect=conn.prepareStatement(sql);
			preSQLSelect.setString(1,specialityname);
			ResultSet rs=preSQLSelect.executeQuery();
			if(!rs.next()){//û�����רҵ����ע����רҵ
				sql="insert into Speciality(SpecialityName) values(?)";
				PreparedStatement preSQLInsert=conn.prepareStatement(sql);
				preSQLInsert.setString(1,specialityname);
				preSQLInsert.executeUpdate();//�������ݿ�
			}
		}
		if("del".equals(action)){
			String sql="delete from Speciality where SpecialityId=?";
			PreparedStatement preSQLDel=conn.prepareStatement(sql);
			preSQLDel.setInt(1,specialityid);
			preSQLDel.executeUpdate();
		}
		//----��ѯ�����е�רҵ����----
		String sql="select * from Speciality";
		Statement state=conn.createStatement();
		ResultSet rs=state.executeQuery(sql);
		ArrayList<Speciality> specialityArray=new ArrayList<Speciality>();
		while(rs.next()){//�Ѳ�ѯ�������	ArrayList��
			Speciality spec=new Speciality();//Speciality��һ��javaBean
			spec.setSpecialityid(rs.getInt("specialityid"));//ͨ����ѯ�����ȡidֵ��������javaBean��
			spec.setSpecialityname(rs.getString("specialityname"));//��ȡ���֣�����javaBean
			specialityArray.add(spec);//����specialityArray��
		}
		Map<String,ArrayList<Speciality>> request = (Map<String,ArrayList<Speciality>>)ActionContext.getContext().get("request");
		//���������ȡ��request����ֻ������һ��map����keyΪString�ͣ�valueΪArrayList<Speciality>��
		request.put("specialityArray", specialityArray);
		//��request�����һ�����ݣ���specialityadmin.jsp��ͨ��
		//#request.specialityArray���Եõ��������
		DBConn.closeConn(conn);
		return SUCCESS;
	}

	public String getSpecialityname() {
		return specialityname;
	}

	public void setSpecialityname(String specialityname) {
		this.specialityname = specialityname;
	}

	public int getSpecialityid() {
		return specialityid;
	}

	public void setSpecialityid(int specialityid) {
		this.specialityid = specialityid;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	

}
