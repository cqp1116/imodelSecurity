package com.imodel.User.controller;

import com.imodel.User.exception.UserNotFoundException;
import com.imodel.User.pojo.FilePojo;
import com.imodel.User.pojo.UserPojo;
import com.imodel.User.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private String folder = "D:\\IDEASPS\\imodelSecurity\\imodel-security-demo\\src\\main\\java\\com\\imodel\\demo\\User\\controller";

    @GetMapping
    @ApiOperation(value = "查询用户信息")
    public List<UserPojo> query(UserPojo user, @PageableDefault(page = 2,size = 15,sort = "userSex,desc") Pageable page){
        System.out.println(ReflectionToStringBuilder.toString(user,ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(page.getPageSize()+"=============="+page.getPageNumber());

        List<UserPojo> list = new ArrayList<>();
        UserPojo join = new UserPojo();
        list.add(new UserPojo());

        return list;
    }


    /**
     * :\\  为参数添加
     * 正则表达式
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")
    @ApiOperation(value = "根据用户id得到相应用户数据")
    public UserPojo getInfo(@ApiParam(value = "用户id") @PathVariable String id){
        System.out.println(id+"================");
        if(id .equals("1")){
            UserPojo user = new UserPojo();
            user.setUserName("tome");
            return user;
        }else{
            throw new UserNotFoundException(id);
        }
    }

    /**
     * 执行删除操作
     * @param id
     */
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation(value = "根据用户id删除数据")
    public void delInfo(@ApiParam(value = "用户的id") @PathVariable String id){
        System.out.println(id+"==================");
    }

    /**
     * 在实体类中加入验证处理
     * @param user
     * @param errors
     */
    @PostMapping
    @ApiOperation(value = "添加用户信息")
    public UserPojo addInfo(@Valid @RequestBody UserPojo user, BindingResult errors){
        //jpa对数据实体类进行异常处理
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                System.out.println(fieldError.getField()+"===="+fieldError.getDefaultMessage());
                System.out.println(error.getDefaultMessage());
            });
        }
        System.out.println(user.getUserName()+"===这是用户名");
        System.out.println(user.getAddress()+"=====这是地址");

        user.setId("1");
        return user;
    }


    /**
     * 用putmapping进行修改数据
     * @param user
     * @param errors
     * @return
     */
    @PutMapping
    @ApiOperation(value = "修改用户信息")
    public UserPojo updateInfo(@Valid @RequestBody UserPojo user, BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(user.getId()+"======================");
        System.out.println(user.getUserName()+"===============");
        user.setAddress("这是修改后的地址");
        return user;
    }


    /**
     * 上传接口
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/upload")
    public FilePojo uploadImage(MultipartFile file) throws  Exception{
        FilePojo obj = new FilePojo();
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile = new File(folder, new Date().getTime() + ".txt");
        file.transferTo(localFile);
        obj.setFilePath(localFile.getAbsolutePath());

        return obj;
    }


    /**
     * 下载的get请求
     * @param id
     * @param request
     * @param response
     * @throws Exception
     */
            @GetMapping(value = "/down/{id}")
            public void download(@PathVariable String id , HttpServletRequest request, HttpServletResponse response)throws  Exception{
                try(InputStream inputStream = new FileInputStream(new File(folder,id+".txt"));
                    OutputStream outputStream = response.getOutputStream();
                ){
                    response.setContentType("application/x-download");
                    response.addHeader("Content-Disposition", "attachment;filename=test.txt");
                    IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        }
    }


    /**
     * 跳转道freemarker页面
     * @param map
     * @return
     */
    @RequestMapping("/freeMarker")
    public String freeMarker(Map<String,Object> map){
        map.put("name", "Joe");
        map.put("sex", 1);    //sex:性别，1：男；0：女；

        // 模拟数据
        List<Map<String, Object>> friends = new ArrayList<Map<String, Object>>();
        Map<String, Object> friend = new HashMap<String, Object>();
        friend.put("name", "xbq");
        friend.put("age", 22);
        friends.add(friend);
        friend = new HashMap<String, Object>();
        friend.put("name", "July");
        friend.put("age", 18);
        friends.add(friend);
        map.put("friends", friends);
        return "freeMarker";
    }
}
