package com.gzz.mybatis.action;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;


@RunWith(SpringRunner.class)
@SpringBootTest
//配置mock
@AutoConfigureMockMvc
public class TestIndexAction {
    @Autowired
    private MockMvc mvc;

    @Before
    public void setupMockMvc() {
        System.out.printf("开始测试。。。。。。。");
        //mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    /**
     * GET 单元测试
     */
    @Test
    public void testGetHello() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/hello")  //请求的url,请求的方法是get
                .accept(MediaType.APPLICATION_JSON)//数据的格式
                 .param("参数名", "值")  //参数值
                )
                .andExpect(MockMvcResultMatchers.status().isOk())   //返回的状态是200
                .andExpect((ResultMatcher) MockRestRequestMatchers.content().string(equalTo("Hello World!")))
                .andDo(MockMvcResultHandlers.print())  //打印出请求和相应的内容
                .andReturn();
        //将相应的数据转换为字符
        Assert.assertNotNull(result.getResponse().getContentAsString());
    }
    /**
     * Post 单元测试
     */
    @Test
    public void testPostHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/")    //请求的url,请求的方法是get
                .contentType(MediaType.APPLICATION_JSON)//数据的格式
                .content("{\"sid\":1,\"name\":\"admin\",\"birthday\":\"1983-10-22\",\"salary\":\"1000\",\"bonus\":100}") //参数值
                )
                .andExpect(MockMvcResultMatchers.status().isOk())   //返回的状态是200
                .andExpect((ResultMatcher) MockRestRequestMatchers.content().string(equalTo("Hello World!")))
                .andDo(MockMvcResultHandlers.print())  //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符
    }
}
