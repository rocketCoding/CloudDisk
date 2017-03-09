<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%@ include file="header.jsp" %>
<html>

	<head>
		<meta charset="utf-8">
		<title>Cloud-DISK</title>
		<link rel="stylesheet" type="text/css"   href="${pageContext.request.contextPath }/css/bootstrap.min.css">
		<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<script type="text/javascript">

		  		//翻页
			function doGoPage(pageNo){
			    var list_url = "${pageContext.request.contextPath}/netdisk/netdisk_list.action?pageNo="+pageNo;
			
				document.getElementById("pageNo").value = pageNo; // 把页号输入框的值更改一下
			
				document.forms[1].action=list_url;
				document.forms[1].submit();
			}
		</script>
	</head>

	<body>


		<!--  网盘内容div  -->
		<div style="padding-left: 20px;padding-right: 20px">
			<div class="panel panel-default " >
				<div class="panel-heading ">
				<h2 class="panel-title ">
				  内容清单
			     </h2>
				</div>
				<form  method="post"  >
				<table class="table table-hover ">
					<thead>	
						<tr>
							<th>文件名</th>
							<th>大小</th>
							<th>上传时间</th>
							<th> 备注 </th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="pageResult.items" status="st">
                           <tr>
                               <td ><s:property value="fileName"/></td>
                               <td ><s:property value="fileSize"/>KB</td>
                               <td >
                              <%--  <s:date name="uploadDate" format="yyyy-MM-dd HH:mm"/> --%>
                               <s:date name="uploadDate" format="yyyy-MM-dd"/>
                               </td>
                               <td ><s:property value="fileRemarks"/></td>
                               <td >
                                   <a href="${pageContext.request.contextPath }/netdisk/netdisk_deleteFile.action?userFile.fileId=<s:property value='fileId'/> ">
                                                                       删除
                                   </a> 
                                   |  
                                   <a href="${pageContext.request.contextPath }/netdisk/netdisk_downloadFile.action?userFile.fileId=<s:property value='fileId'/>">
                                                                       下载</a>
                               </td>
                           </tr>
                       </s:iterator>
					</tbody>
				</table>
				</form>
			</div>
		   </div> 
		
		<!--  网盘内容div结束  -->
		
		<!-- 分页 -->
	     <div style="margin-top: 5px;">
	         <s:if test="pageResult.totalCount > 0">
			 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="right">
	                 	总共 <s:property value="pageResult.totalCount"/> 条记录，当前第 <s:property value="pageResult.pageNo"/> 页，
	                 	共 <s:property value="pageResult.totalPageCount"/> 页 
	                 	<s:if test="pageResult.pageNo > 1">
	                 	        &nbsp;&nbsp;<a href="javascript:doGoPage(<s:property value='pageResult.pageNo-1'/>)">上一页</a>
	              	    </s:if>
	              	    <s:if test="pageResult.pageNo < pageResult.totalPageCount">
	                            &nbsp;&nbsp;<a href="javascript:doGoPage(<s:property value='pageResult.pageNo+1'/>)">下一页</a>
	                    </s:if>        
						到&nbsp;<input id="pageNo" name="pageNo" type="text" style="width: 30px;" onkeypress="if(event.keyCode == 13){doGoPage(this.value);}" min="1"
						max="3" value="<s:property value='pageResult.pageNo'/>" /> &nbsp;&nbsp;
				    </td>
				</tr>
			</table>
			</s:if><s:else>暂无数据！</s:else>	
	        </div>
		
	</body>

</html>