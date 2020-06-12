package orz.doublexi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import orz.doublexi.pojo.Account;
import orz.doublexi.pojo.UploadInfoBean;
import orz.doublexi.service.FileUploadService;
import orz.doublexi.utils.ServletUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@Controller

public class FileUploadController {
    @Autowired
    FileUploadService fileUploadService;


    @RequestMapping(value = "/uploadvideo.do")
    @ResponseBody
    public HashMap uploadvideo(@RequestParam("file") MultipartFile file)
            throws IllegalStateException, IOException {
        System.out.println("进入了video文件上传");
        // 判断文件是否为空，空则返回失败页面
        if (file.isEmpty()) {
            return null;
        }
        // 获取文件存储路径（绝对路径）
        String path = ServletUtils.getContext().getRealPath("/video/");
        // 获取原文件名

        String fileName = UUID.randomUUID().toString()+file.getOriginalFilename();
        ServletUtils.getSession().setAttribute("videoname", fileName);
        // 创建文件实例
        File filePath = new File(path, fileName);
        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建video目录" + filePath);
        }
        System.out.println(fileName);
        // 写入文件
        file.transferTo(filePath);
        HashMap hashMap = new HashMap();
        hashMap.put("code", "0");
        hashMap.put("msg", "success");
        HashMap data = new HashMap();
        data.put("src", "http:localhost:8080/MyWebSite/video/" + fileName);
        hashMap.put("data", data);
        return hashMap;
    }
    @RequestMapping(value = "/uploadimage.do")
    @ResponseBody
    public HashMap uploadimage(@RequestParam("file") MultipartFile file)
            throws IllegalStateException, IOException {
        System.out.println("进入了image文件上传");
        // 判断文件是否为空，空则返回失败页面
        if (file.isEmpty()) {
            return null;
        }
        // 获取文件存储路径（绝对路径）
        String path = ServletUtils.getContext().getRealPath("/image/");
        // 获取原文件名
        String fileName = UUID.randomUUID().toString()+file.getOriginalFilename();
        // 创建文件实例
        ServletUtils.getSession().setAttribute("imagename", fileName);
        File filePath = new File(path, fileName);
        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建image目录" + filePath);
        }
        System.out.println(fileName);
        // 写入文件
        file.transferTo(filePath);
        HashMap hashMap = new HashMap();
        hashMap.put("code", "0");
        hashMap.put("msg", "success");
        HashMap data = new HashMap();
        hashMap.put("data", data);
        return hashMap;
    }

    @RequestMapping(value = "/updatetouxiang.do")
    @ResponseBody
    public HashMap updateimage(@RequestParam("file") MultipartFile file)
            throws IllegalStateException, IOException {
        System.out.println("进入了image更新上传");
        // 判断文件是否为空，空则返回失败页面
        if (file.isEmpty()) {
            return null;
        }
        // 获取文件存储路径（绝对路径）
        String path = ServletUtils.getContext().getRealPath("/resources/img/");
        // 获取原文件名
        String fileName = UUID.randomUUID().toString()+file.getOriginalFilename();
        // 创建文件实例
        File filePath = new File(path, fileName);
        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建头像目录" + filePath);
        }
        System.out.println(fileName);
        // 写入文件
        file.transferTo(filePath);
       Account account= (Account) ServletUtils.getSession().getAttribute("account");
       account.setAccountimg(fileName);

        fileUploadService.updatetouxiaog(account.getAccountid(), fileName);
        HashMap hashMap = new HashMap();
        hashMap.put("code", "0");
        hashMap.put("msg", "success");
        hashMap.put("imagename", fileName);

        return hashMap;
    }

    @RequestMapping(value = "/uploadinfo.do")
    @ResponseBody
    public HashMap uploadinfo(HttpServletRequest request){
        String videoname = (String)ServletUtils.getSession().getAttribute("videoname");
        String imagename = (String)ServletUtils.getSession().getAttribute("imagename");
        int owner = ((Account)ServletUtils.getSession().getAttribute("account")).getAccountid();
        String title = request.getParameter("title");
        String desc =  request.getParameter("desc");

        UploadInfoBean bean = new UploadInfoBean(videoname,imagename,title,desc,owner);
        System.out.println(fileUploadService);
        System.out.println(bean);
        fileUploadService.upload(bean);
        HashMap hashMap = new HashMap();
        hashMap.put("code", "0");
        hashMap.put("msg", "上传成功了");
        return hashMap;
    }
}


