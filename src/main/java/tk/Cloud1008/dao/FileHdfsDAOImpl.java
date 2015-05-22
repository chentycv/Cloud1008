package tk.Cloud1008.dao;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Repository;

@Repository
public class FileHdfsDAOImpl implements FileHdfsDAO {
	
	private Configuration conf =null;
	 
	public FileHdfsDAOImpl(){
        conf =new Configuration();
        conf.addResource(new Path("/home/grid/hadoop/hadoop-2.5.2/etc/hadoop/core-site.xml"));
    }

    public FileHdfsDAOImpl(Configuration conf){
        this.conf =conf;
    }
    
    @Override
    public boolean sendFile(String path,String localfile){
        File file=new File(localfile);
        if (!file.isFile()) {
            System.out.println(file.getName());
            return false;
        }
        try {
            FileSystem localFS =FileSystem.getLocal(conf);
            FileSystem hadoopFS =FileSystem.get(conf);
            Path hadPath=new Path(path);

            FSDataOutputStream fsOut=hadoopFS.create(new Path(path+"/"+file.getName()));
            FSDataInputStream fsIn=localFS.open(new Path(localfile));
            byte[] buf =new byte[1024];
            int readbytes=0;
            while ((readbytes=fsIn.read(buf))>0){
                fsOut.write(buf,0,readbytes);
            }
            fsIn.close();
            fsOut.close();

            FileStatus[] hadfiles= hadoopFS.listStatus(hadPath);
            for(FileStatus fs :hadfiles){
                System.out.println(fs.toString());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean downloadFile(String hadfile,String localPath){

        try {
            FileSystem localFS =FileSystem.getLocal(conf);
            FileSystem hadoopFS =FileSystem.get(conf);
            Path hadPath=new Path(hadfile);

            System.out.print(hadPath.getName());
            String pathlocal = localPath+hadPath.getName();
            
            FSDataOutputStream fsOut=localFS.create(new Path(pathlocal));
            FSDataInputStream fsIn=hadoopFS.open(hadPath);
            byte[] buf =new byte[1024];
            int readbytes=0;
            while ((readbytes=fsIn.read(buf))>0){
                fsOut.write(buf,0,readbytes);
            }
            fsIn.close();
            fsOut.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
