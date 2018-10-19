package com.imodel.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    /**
     * 处理查询请求
     * @throws Exception
     */
    @Test
    public void queryUserSuccess()throws  Exception{
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user").param("userName","小明")
                                .param("userSex","男").param("userSchool","山东师范大学")
                                .param("size","15").param("page","3").param("sort","userSex,desc")
                                .contentType(MediaType.APPLICATION_JSON_UTF8))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void getUserSuccess()throws  Exception{
        String result = mockMvc.perform(get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 在get请求时传递的参数
     * 加了数字验证此时传递字母
     * @throws Exception
     */
    @Test
    public void getUserError()throws  Exception{
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/a").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 调用的是delete的requestmapping
     * 执行删除权限
     * @throws Exception
     */
    @Test
    public void delUserSuccess() throws Exception{
        String result= mockMvc.perform(MockMvcRequestBuilders.delete("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }


    /**
     * 添加用户信息
     * @throws Exception
     */
    @Test
    public void addUserSuccess() throws Exception{
        Date date = new Date();
        String content = "{\"userName\":\"tom\",\"address\":null,\"userSex\":\"男\",\"birthday\":"+date.getTime()+"}";
        String result= mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }


    /**
     * 修改用户数据
     * @throws Exception
     */
    @Test
    public void updateUserSuccess() throws  Exception{
        Date date = new Date();
        String content = "{\"id\":\"1\",\"userName\":\"tom\",\"address\":\"修改前的地址\",\"birthday\":"+date.getTime()+"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.put("/user")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 测试附件上传
     * @throws Exception
     */
    @Test
    public void fileUploadSuccess() throws  Exception{
        String result = mockMvc.perform(MockMvcRequestBuilders.fileUpload("/user/upload")
                    .file(new MockMultipartFile("file","test.txt","multipart/form-data", "hello upload".getBytes("UTF-8"))))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
