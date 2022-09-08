package com.kang.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import com.kang.POJO.Speciality;
import com.kang.POJO.Student;
import com.kang.db.DBConn;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MatriAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	public String action;
	public int specialityid;
	public String matrino;//��ʾ¼ȡ֪ͨ����
	public String studentname;
	public int currentpage=1;
	public long studentid;
	
	@Override
	public String execute() throws Exception {
		if(studentname!=null&&studentname.length()!=0)
			studentname=studentname.trim();//Trim() �����Ĺ�����ȥ����β�ո�
		if(action!=null&&action.length()!=0)
			action=action.trim();
		if(matrino!=null&&matrino.length()!=0)
			matrino=matrino.trim();
		Connection conn=DBConn.createDBConn();
		//----�����Ҫ����һ��ѧ��---
		if("add".equals(action)){
			String sql="select * from Student where matrino=? and studentname=?"+
				" and specialityid=?";
			PreparedStatement preSQLSelect=conn.prepareStatement(sql);
			preSQLSelect.setString(1,matrino);
			preSQLSelect.setString(2,studentname);
			preSQLSelect.setInt(3,specialityid);
			ResultSet rs=preSQLSelect.executeQuery();
			if(!rs.next()){
				sql="insert into Student(matrino,studentname,specialityid) "+
					" values(?,?,?)";
				PreparedStatement preSQLInsert=conn.prepareStatement(sql);
				preSQLInsert.setString(1,matrino);
				preSQLInsert.setString(2,studentname);
				preSQLInsert.setInt(3,specialityid);
				preSQLInsert.executeUpdate();
			}
		}
		//----�����Ҫɾ��һ��ѧ��---
		if("del".equals(action)){
			String sql="delete from Student where studentid=?";
			PreparedStatement preSQLUpdate=conn.prepareStatement(sql);
			preSQLUpdate.setLong(1,studentid);
			preSQLUpdate.executeUpdate();
		}
		//----�����Ҫ��ѯ����----
		ResultSet rsselect=null;
		int pagesize=5;//ÿҳ��¼����
		int pagecount=0;//��ҳ��
		int recount=0;//�ܼ�¼����
		if("select".equals(action)){
			String sql=null;
			if((specialityid==0)){
				sql="select studentname,"+
					"studentid,matrino,specialityid "+
					"from Student where 1=1 limit  "+pagesize;
				String sqlcount="select count(*) as recount from Student ";
				Statement stateCount=conn.createStatement();
				ResultSet rscount=stateCount.executeQuery(sqlcount);
				rscount.next();
				recount=rscount.getInt("recount");
				if(recount%pagesize==0)
					pagecount=recount/pagesize;
				else
					pagecount=(int)(recount/pagesize)+1;
				//----���ɵõ���ǰҳ���ݲ�ѯ�ĸ�������----
				if(pagecount>1&&currentpage>1){
					String sqladd=" and studentid not in "+
						"(select studentid from Student limit "+(currentpage-1)*pagesize+")";
					sql=sql+sqladd;
				}
				Statement state=conn.createStatement();
				rsselect=state.executeQuery(sql);
			}else{  //��⵽ѧ���Ѿ�����רҵ
				//----�����ɲ�ѯ��where�Ӿ�----
				String sqlwhere=" where 1=1 ";
				if(specialityid!=0)
					{
					sqlwhere=sqlwhere+" and specialityid=? and studentname like BINARY ? ";
			}
				else
					sqlwhere=sqlwhere+" and studentname like BINARY ? ";
				sql="select studentname,studentid,matrino,specialityid "+
					"from Student "+sqlwhere +"limit "+pagesize;
				//----�õ��ܼ�¼����----
				String sqlcount="select count(*) as recount "+
					"from Student "+sqlwhere;
				PreparedStatement preSQLCount=conn.prepareStatement(sqlcount);
				if(specialityid!=0){
					preSQLCount.setInt(1,specialityid);
					preSQLCount.setString(2,"%"+studentname+"%");
				}else
					preSQLCount.setString(1,"%"+studentname+"%");
				ResultSet rscount=preSQLCount.executeQuery();
				rscount.next();
				recount=rscount.getInt("recount");//�õ��ܼ�¼����
				//----�õ���ҳ��----
				if(recount%pagesize==0)//������
					pagecount=recount/pagesize;
				else//��������
					pagecount=(int)(recount/pagesize)+1;
				//----���ɵõ���ǰҳ���ݲ�ѯ�ĸ�������----
				PreparedStatement preSQLSelect=null;
				//��ҳ������1�ҵ�ǰҳ�����1
				if(pagecount>1&&currentpage>1){
					String sqladd=" and studentid not in "+
						"(select studentid from Student "+sqlwhere+"limit "+(currentpage-1)*pagesize+
						") ";
					sql=sql+sqladd;
					preSQLSelect=conn.prepareStatement(sql);
					if(specialityid!=0){
						preSQLSelect.setInt(1,specialityid);
						preSQLSelect.setString(2,"%"+studentname+"%");
					}else{
						preSQLSelect.setString(1,"%"+studentname+"%");
					}				
				}else{//��ǰΪ��1ҳ���ֻ��1ҳ
					preSQLSelect=conn.prepareStatement(sql);
					if(specialityid!=0){//���ѡ����רҵ
						preSQLSelect.setInt(1,specialityid);
						preSQLSelect.setString(2,"%"+studentname+"%");
					}else
						preSQLSelect.setString(1,"%"+studentname+"%");	
				}
				rsselect=preSQLSelect.executeQuery();
			}
		}
		
		ArrayList<Student> stuArray=new ArrayList<Student>();
		while(rsselect!=null&&rsselect.next()){
			Student stu=new Student();
			stu.setStudentName(rsselect.getString("studentname"));
			stu.setSpecialityId(rsselect.getInt("specialityid"));
			stu.setMatriNo(rsselect.getString("matrino"));
			stu.setStudentId(rsselect.getLong("studentid"));
			stuArray.add(stu);
		}
		Map request = (Map)ActionContext.getContext().get("request");
		request.remove("stuArray");
		request.put("stuArray", stuArray);		
		//----��ѯ��רҵ����----
		String sql="select * from Speciality";
		Statement state=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet rs=state.executeQuery(sql);
		ArrayList<Speciality> specialityArray=new ArrayList<Speciality>();
		while(rs.next()){
			Speciality spec=new Speciality();
			spec.setSpecialityid(rs.getInt("specialityid"));
			spec.setSpecialityname(rs.getString("specialityname"));
			specialityArray.add(spec);
		}
		request.remove("specialityArray");
		request.put("specialityArray", specialityArray);
		//----�����ݷ���request----
		request.remove("pagesize");
		request.remove("pagecount");
		request.remove("currentpage");
		request.remove("recount");
		request.put("pagesize",pagesize);
		request.put("pagecount",pagecount);
		request.put("currentpage",currentpage);
		request.put("recount",recount);
		DBConn.closeConn(conn);
		return SUCCESS;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getSpecialityid() {
		return specialityid;
	}

	public void setSpecialityid(int specialityid) {
		this.specialityid = specialityid;
	}

	public String getMatrino() {
		return matrino;
	}

	public void setMatrino(String matrino) {
		this.matrino = matrino;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public long getStudentid() {
		return studentid;
	}

	public void setStudentid(long studentid) {
		this.studentid = studentid;
	}

}
