package cn.bdqn.pointsystem.utlis;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Date;

/**
 * @author yajun
 * @create 2019/12/31
 */
public class FileUtlis {
    /**
     * 读取图片
     * @param url  图片路径
     * @return
     * @throws IOException
     */
    public static String getPath(String url) throws IOException {
        //读取图片
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(url));
        //给图片从新命名
        String path=new Date().getTime()+".jpg";
        //获取项目所在物理绝对路径
        String  outUrl = ResourceUtils.getURL("classpath:static").getPath().replace("%20", " ");
        //输出的结果outUrl为：file:/D:/Document/Document.jar!/BOOT-INF/classes!/static
        System.out.println(outUrl);
        if(outUrl.contains("file")){
            //获得Document.jar!/在路径中的位置
            int getFile=outUrl.indexOf("file")+6;
            //去掉file:/
            outUrl=outUrl.substring(getFile,outUrl.length());
        }
        //获得Document.jar!/在路径中的位置
        int result1=outUrl.lastIndexOf("Document.jar!/");
        //去掉Document.jar!/及后面的路径
        outUrl = outUrl.substring(0,result1)+"upload/";

        File file=new File(outUrl);
        //判断路径是否存在（file.exists() 如果存在返回true不存在返回false）
        if(!file.exists()){
            //不存在创建文件夹
            file.mkdirs();
        }
        outUrl=outUrl+path;
        //输出结果为D:/Document/upload/1564643609350.jpg
        System.out.println(outUrl);
        //输出到指定目录
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outUrl));

        int i;
        while((i=in.read())!=-1){
            out.write(i);
        }
        out.flush();

        out.close();
        in.close();
        return path;
    }
}
