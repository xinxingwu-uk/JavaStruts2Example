//������һЩ��ѯѧ���Ƿ��רҵ���Ƿ�ְ���Ƿ������ķ���

package com.kang.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentUtil {
	//�����Ƿ��ѷ�רҵ�������ַ���
	public static String haveSplitSpec(int specialityid){
		String returnString;
		try{
			Connection conn=DBConn.createDBConn();
			if(specialityid==0)
				returnString="����רҵ";
		     else{
		    	  String sql="select * from speciality where specialityid="+specialityid;
		    	  Statement statetemp=conn.createStatement();
		    	  ResultSet rstemp=statetemp.executeQuery(sql);
		    	  if(rstemp.next())
		    		  returnString=rstemp.getString("specialityname");
		    	  else
		    		  returnString="����רҵ";
		      }
			DBConn.closeConn(conn);
			return returnString;
		}catch(Exception e){
			return null;
		}
	}
	
	//�����Ƿ��ѷְ�������ַ���
	public static String haveSplitClass(int classid){
		String returnString;
		try{
			Connection conn=DBConn.createDBConn();
			if(classid==0)
				returnString="��δ�ְ�";
		     else{
		    	  String sql="select * from classTa where classid="+classid;
		    	  Statement statetemp=conn.createStatement();
		    	  ResultSet rstemp=statetemp.executeQuery(sql);
		    	  if(rstemp.next())
		    		  returnString=rstemp.getString("classname");
		    	  else
		    		  returnString="��δ�ְ�";
		      }
			DBConn.closeConn(conn);
			return returnString;
		}catch(Exception e){
			return null;
		}
	}
	//�����Ƿ��ѷ�������������ַ���
	public static String haveSplitBedchamber(int bedchamberid){
		String returnString;
		try{
			Connection conn=DBConn.createDBConn();
			if(bedchamberid==0)
				returnString="��δ��������";
		     else{
		    	  String sql="select * from bedchamber where bedchamberid="+bedchamberid;
		    	  Statement statetemp=conn.createStatement();
		    	  ResultSet rstemp=statetemp.executeQuery(sql);
		    	  if(rstemp.next())
		    		  returnString=rstemp.getString("bedchambername");
		    	  else
		    		  returnString="��δ��������";
		      }
			DBConn.closeConn(conn);
			return returnString;
		}catch(Exception e){
			return null;
		}
	}	
}
