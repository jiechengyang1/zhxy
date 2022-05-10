package com.jcy.zhxy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jcy.zhxy.pojo.Admin;
import com.jcy.zhxy.pojo.LoginForm;
import com.jcy.zhxy.pojo.Student;
import com.jcy.zhxy.pojo.Teacher;
import com.jcy.zhxy.service.AdminService;
import com.jcy.zhxy.service.StudentService;
import com.jcy.zhxy.service.TeacherService;
import com.jcy.zhxy.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Api(tags = "系统控制器")
@RestController
@RequestMapping("/sms/system")
public class SystemController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;


    @ApiOperation("文件上传")
    @PostMapping("/headerImgUpload")
    public Result header(@ApiParam("头像文件") @RequestPart("multipartFile")  MultipartFile multipartFile){

        HttpServletRequest request;
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String originalFilename = multipartFile.getOriginalFilename();
        int i = originalFilename.lastIndexOf(".");
        String newFileName = uuid + originalFilename.substring(i);
        String Path="D:/programming/javastudy/springboot/zhxy/target/classes/public/upload/".concat(newFileName);
        try {
            multipartFile.transferTo(new File(Path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String TruePath="upload/".concat(newFileName);
        return Result.ok(TruePath);


    }
    @GetMapping("/getInfo")
    public Result getInfo(@RequestHeader("token") String token){
        boolean expiration = JwtHelper.isExpiration(token);
        //token过期
        if (expiration){
            return Result.build(null,ResultCodeEnum.TOKEN_ERROR);
        }

        Long userId = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);
        Map<String,Object>map =new LinkedHashMap<>();
        switch (userType){
            case 1:
                Admin admin=adminService.getAdminById(userId);
                map.put("userType",1);
                map.put("user",admin);
                break;
            case 2:
                Student student=studentService.getStudentById(userId);
                map.put("userType",2);
                map.put("user",student);
                break;
            case 3:
               Teacher teacher=teacherService.getTeachertById(userId);
                map.put("userType",3);
                map.put("user",teacher);
                break;
        }


        return Result.ok(map);

    }

    @GetMapping("/getVerifiCodeImage")
    public void getVerifiCodeImag(HttpServletRequest request, HttpServletResponse response){
        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();
        String verifiCode = new String(CreateVerifiCodeImage.getVerifiCode());
        HttpSession session = request.getSession();
        session.setAttribute("verifiCode",verifiCode);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(verifiCodeImage,"JPEG",outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm,HttpServletRequest request){
        //校验验证码
        HttpSession session = request.getSession();
        String sessionverifiCode = (String) session.getAttribute("verifiCode");
        String loginverifiCode = loginForm.getVerifiCode();
        if("".equals(sessionverifiCode) || null==sessionverifiCode){
            return Result.fail().message("验证码失效(┬┬﹏┬┬)");
        }
        if (!sessionverifiCode.equals(loginverifiCode)){
            return Result.fail().message("验证码错误(┬┬﹏┬┬)");
        }
        //从session除去失效的验证码
        session.removeAttribute("verifiCode");

        Map<String,Object> map =new LinkedHashMap<>();
        //校验用户类型
        switch(loginForm.getUserType()){
            case 1:
                try {
                    Admin admin=adminService.login(loginForm);
                    if(null!=admin){
                        String token = JwtHelper.createToken(admin.getId().longValue(), 1);
                        map.put("token", token);

                    }else{
                        throw  new RuntimeException("账号密码错误(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
            case 2:
                try {
                    Student student= studentService.login(loginForm);
                    if(null!=student){
                        String token = JwtHelper.createToken(student.getId().longValue(), 2);
                        map.put("token", token);

                    }else{
                        throw  new RuntimeException("账号密码错误(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
            case 3:
                try {
                    Teacher teacher= teacherService.login(loginForm);
                    if(null!=teacher){
                        String token = JwtHelper.createToken(teacher.getId().longValue(), 3);
                        map.put("token", token);

                    }else{
                        throw  new RuntimeException("账号密码错误(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }

        }
        return Result.fail().message("没有你啊你个老六^(*￣(oo)￣)^");

    }

    @ApiOperation("更新用户秘码")
    @PostMapping("/updatePwd/{oldPwd}/{newPwd}")
    public Result updatePwd( @RequestHeader("token") String token,
                             @PathVariable("oldPwd") String oldPwd,
                             @PathVariable("newPwd") String newPwd){

        boolean expiration = JwtHelper.isExpiration(token);
        if(expiration){
            return Result.fail().message("登录失效，重新登录");
        }
        Integer userType = JwtHelper.getUserType(token);
        Long userId = JwtHelper.getUserId(token);
        oldPwd= MD5.encrypt(oldPwd);
        newPwd= MD5.encrypt(newPwd);
        switch (userType){
            case 1:
                QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id",userId.intValue());
                queryWrapper.eq("password",oldPwd);
                Admin admin = adminService.getOne(queryWrapper);
                if (admin!=null){
                    admin.setPassword(newPwd);
                    adminService.saveOrUpdate(admin);
                }else {return Result.fail().message("密码错误");
                }
                break;
            case 2:
                QueryWrapper<Student> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.eq("id",userId.intValue());
                queryWrapper2.eq("password",oldPwd);
                Student student = studentService.getOne(queryWrapper2);
                if (student!=null){
                    student.setPassword(newPwd);
                    studentService.saveOrUpdate(student);
                }else {return Result.fail().message("密码错误");
                }
                break;
            case 3:
                QueryWrapper<Teacher> queryWrapper3 = new QueryWrapper<>();
                queryWrapper3.eq("id",userId.intValue());
                queryWrapper3.eq("password",oldPwd);
                Teacher teacher = teacherService.getOne(queryWrapper3);
                if (teacher!=null){
                  teacher.setPassword(newPwd);
                    teacherService.saveOrUpdate(teacher);
                }else {return Result.fail().message("密码错误");
                }
                break;
        }
        return Result.ok();

    }

}
