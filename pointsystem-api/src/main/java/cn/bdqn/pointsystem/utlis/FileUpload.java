package cn.bdqn.pointsystem.utlis;

import org.junit.platform.commons.util.StringUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.Date;
import java.util.Map;

/**
 * @Classname FileUpload
 * @Description TODO
 * @Date 2020/1/1 16:53
 * @Created by lenovo
 */
public class FileUpload {
    public static String  getFileURL(MultipartFile file, String path, String systemPath){
        return getPathValue(file,systemPath,path);
    }
    protected static String getPathValue(MultipartFile file,String systemPath,String path){
        if (!file.isEmpty()) {
            try {
                String fileName=file.getOriginalFilename();
                if(StringUtils.isNotBlank(fileName)){
                    String[] str=fileName.split("\\.");
                    if(str.length==1){
                        path=path+new Date().getTime()+str[0]+".JPEG";
                    }else{
                        path=path+new Date().getTime()+"."+str[str.length-1];
                    }

                }
                // 文件保存路径
                File fl=new File(systemPath+path);
                if(!fl.getParentFile().exists()){
                    fl.getParentFile().mkdirs();
                }
                // 转存文件
                file.transferTo(fl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            return null;
        }
        return  path;
    }
    public static String getpath(){
        String outUrl=null;
//        try{
//            //获得项目路径：file:/D:/Document/Document.jar!/BOOT-INF/classes!/static
//            outUrl = ResourceUtils.getURL("classpath:static").getPath().replace("%20", " ");
//        }catch (Exception e){
//        }
//        System.out.println(outUrl);
//        //拿到Document.jar!/在路径中的位置
//        int result1=outUrl.lastIndexOf("pointsystem.jar!/");
//        //截取掉后面的
//        outUrl = outUrl.substring(0,result1);
//        //输出结果为file:/D:/Document/upload/
//        System.out.println(outUrl);
//        //将file:/D:/Document/upload/设置为/upload/**
//        //注意虚拟路径设置时不能将file:/去掉
        return outUrl;
    }
}
