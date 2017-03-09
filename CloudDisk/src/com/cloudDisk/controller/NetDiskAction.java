package com.cloudDisk.controller;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.cloudDisk.entity.User;
import com.cloudDisk.entity.Userfile;
import com.cloudDisk.page.PageResult;
import com.cloudDisk.service.FileService;
import com.cloudDisk.utils.PathConvert;
import com.cloudDisk.utils.QueryHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class NetDiskAction extends ActionSupport {
	
	private static int DEFAULT_PAGE_SIZE=10;
	@Resource
	FileService fileService;

	/**
	 * 重定向action 执行list() 方法 再转发到文件列表界面
	 * @return
	 */
	public String toListUI() {
		return "list";
	}

	
	
	/**
	 * 前往文件上传界面
	 * @return
	 */
	public String toFileUploadUI() {
		return "fileUploadUI";
	}

	
	
	/**
	 * 文件搜索；允许模糊搜索
	 * @return
	 */
	private String searchFile(){
		// userFile对象已经有值了
		return "";
	}
	
	
	/**
	 * 删除文件
	 * @return
	 */
	public String deleteFile() {
		// 第一步删除服务器上的文件
		if (userFile != null) {
			userFile = fileService.findById(userFile.getFileId());
			String path = userFile.getFileUrl();
			File file = new File(path);
			if (file.exists()) {
				file.delete();
			}else{
				System.out.println("----------------文件不存在------------");
			}
			// 第二步移除数据库中的记录
			fileService.delete(userFile.getFileId());
		}
		return toListUI();
	}

	
	/**
	 * 下载文件
	 * @return
	 */
	public String downloadFile() {
		// 第一步：查询到目标文件的下载地址
		userFile = fileService.findById(userFile.getFileId());
		return "download";
	}

	/**
	 * 文件上传
	 * @return
	 * @throws Exception
	 */
	public String fileUpload() throws Exception {

		// 将文件的相关信息存储到数据库中
		user = (User) ActionContext.getContext().getSession().get("userInfo");
		if (userFile != null) {
			String userName = user.getUserName();
			// 创建服务器端文件存放路径 路径名为"/upload/用户名/文件名"
			String filepath = ServletActionContext.getServletContext()
					.getRealPath("/upload" + "/" + userName);
			// 创建目标文件对象
			File desFile = new File(filepath, userfileFileName);
			// 直接拷贝
			FileUtils.copyFile(userfile, desFile);
			// 构建实体准备存往数据库
			if (userFile.getFileName().equals("")) {
				userFile.setFileName(userfileFileName);
			}
			userFile.setFileUrl(desFile.getAbsolutePath());
			userFile.setFileSize((float) userfile.length() / 1024); // 转化为以kb为单位再进行存储
			 Date date=new Date();
		    Timestamp timestamp=new Timestamp(date.getTime());
			userFile.setUploadDate(timestamp); // 这里的时间最好再精确一点
			userFile.setUser(user);
			fileService.save(userFile);
		}
		return toListUI();
	}

	/**
	 * 列表展示
	 * @return
	 */
	public String list() {
		System.out.println("执行标记-----");
		
		user = (User) ActionContext.getContext().getSession().get("userInfo");
		QueryHelper helper = new QueryHelper(Userfile.class, "userfile");
		// 判断当前是不是在执行搜索的操作
		if(userFile!=null&&!userFile.getFileName().equals("")){
			// 说明当前还有额外的搜索条件
			helper.setWhereClause("fileName like ?", "%"+userFile.getFileName()+"%");
		}
		
		// 从数据库查询到指定用户的所有上传文件
		
		helper.setWhereClause("userId=?", user.getUserId());
		helper.setOrderByClause(QueryHelper.ORDER_BY_DESC,"uploadDate"); //按照时间降序排列
		// 参数1：查询助手 参数2：当前页号 参数3：页大小[这里先固定每页显示10条数据]
		
		System.out.println("页号"+getPageNo());
		pageResult = fileService.getPageResult(helper, getPageNo(), DEFAULT_PAGE_SIZE);
		return "listUI";
	}

	
	
	
//--------------------------------------------- get set 方法区-------------------------------------
	
	private User user;
	// 接收文件上传的前端数据
	private File userfile;
	private String userfileFileName;
	private String userfileContentType;
	PageResult pageResult;
	// 定义 Userfile 对象 接受前端传递过来的部分数值
	private Userfile userFile;
    private int pageNo; //接收前台传递过来的页号
	private String downloadFileName;

	
	
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}



	// 实现struts中文件下载的方法
	public InputStream getAttrInputStream() {
		// 拿到绝对路径
		String path = userFile.getFileUrl(); 
		// 设置文件名
		setDownloadFileName(path.substring(path.lastIndexOf("\\")+1));
	    // 截取绝对路径获取到相对路径
		String relativePath=PathConvert.convertpath(path);		
	
		return ServletActionContext.getServletContext().getResourceAsStream(relativePath);
	}

	public String getDownloadFileName() {
		try {
			downloadFileName = URLEncoder.encode(downloadFileName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}

	// 处理文件名的编码问题
	public void setDownloadFileName(String downloadFileName) {
		try {
			downloadFileName = new String(
					downloadFileName.getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		this.downloadFileName = downloadFileName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PageResult getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}

	public void setUserfileContentType(String userfileContentType) {
		this.userfileContentType = userfileContentType;
	}

	public void setUserfileFileName(String userfileFileName) {
		this.userfileFileName = userfileFileName;
	}

	public void setUserfile(File userfile) {
		this.userfile = userfile;
	}

	public File getUserfile() {
		return userfile;
	}

	public Userfile getUserFile() {
		return userFile;
	}

	public void setUserFile(Userfile userFile) {
		this.userFile = userFile;
	}

}
