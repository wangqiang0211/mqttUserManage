//package com.cdzic.mqttUserManage.web;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//
///**
// * @Date 2019/06/04 下午 04:35
// * @Author 靳东
// * @ClassName VersionTestController
// */
//@RestController
////@RequestMapping("/v")
//public class VersionTestController {
//
//    @GetMapping("/test")
//    public String responseV() {
//        return "v1.1";
//    }
//
//    @GetMapping(value = "/img")
//    public void getImage(String path, HttpServletRequest request, HttpServletResponse response) {
//        try {
//            String url="C:\\Users\\Administrator\\Desktop\\360wallpaper.jpg";
//            File file = new File(url);
//            String l=request.getRealPath("/")+"/"+url;
//            String filename = file.getName();
//            InputStream fis = new BufferedInputStream(new FileInputStream(file));
//            byte[] buffer = new byte[fis.available()];
//            fis.read(buffer);
//            fis.close();
//            response.reset();
//            // 设置response的Header
//            response.addHeader("Content-Length", "" + file.length());
//            response.setContentType("image/jpg");
//
//            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//            toClient.write(buffer);
//            toClient.flush();
//            toClient.close();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
////        return response;
//    }
//
//
//}
