<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<body>
<s:form method="post" action="AdminUser" theme="simple">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="600">
  <tr>
    <td width="100%" bgcolor="#C0C0C0">
    <font color="#0000FF">����һ���û�</font></td>
  </tr>
  <tr>
    <td width="100%">��
    �û�����
    <s:textfield name="adminusername"/>
    ����:
    <s:password name="adminuserpassword"/><br>
    &nbsp;&nbsp;�û���ɫ:
    <s:select name="adminuserrole" list="#{'':'====��ѡ��====','1':'ϵͳ������Ա',
    		'2':'ϵ��������Ա','3':'���������Ա','4':'���������Ա'}">
    </s:select>
    <s:hidden name="action" value="add"/>
    <s:submit value="�ύ"/>
    </td>
  </tr>
</table>
</s:form>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="600">
  <tr>
    <td width="100%" bgcolor="#C0C0C0" align="center" colspan="4">
    <font color="#0000FF">�����û�����</font></td>
  </tr>
  <tr>
    <td width="20%" align="center">��
		���
    </td>
    <td width="30%" align="center">��
		�û���
    </td> 
    <td width="30%" align="center">��
		��ɫ
    </td> 
    <td width="20%" align="center">��
		ɾ����
    </td>      
  </tr>
<s:iterator value="#request.userArray" status="status">
  <tr>
    <td width="20%" align="center">��
		<s:property value="#status.count"/>
    </td>
    <td width="30%" align="center">
    	<s:property value="adminusername"/>��
    </td> 
    <td width="30%" align="center">��
    	<s:if test="adminuserrole==1">
    		ϵͳ������Ա
    	</s:if>
    	<s:if test="adminuserrole==2">
    		ϵ��������Ա
    	</s:if>
    	<s:if test="adminuserrole==3">
    		���������Ա
    	</s:if>
    	<s:if test="adminuserrole==4">
    		���������Ա
    	</s:if>
    </td> 
    <td width="20%" align="center">��
		<a href="AdminUser.action?action=del&adminusername=<s:property value="adminusername"/>">
		ɾ����</a>
    </td>      
  </tr>
</s:iterator>
</table>
</body>
</html>