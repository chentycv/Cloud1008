package tk.Cloud1008.service;


public interface HdfsService {
	
	
/*
 * ***********************example******************
 		hdfsService.upload("/user/root/fuck1.txt", "/home/lihua/图片/1.jpg");
		hdfsService.download("/user/root/fuck1.txt/1.jpg", "/home/lihua/hdfs/");
		
		目前还没完成文件夹上传  先做单个文件上传
		fuck1.txt为文件夹      先用此文件做为调试   其他文件还末赋权限
		查看文件系统:   172.31.34.179:50070
 */
	
	
	public void upload(String hdfspath,String localpath);
	
	public void download(String hdfspath,String localpath);
	

}
