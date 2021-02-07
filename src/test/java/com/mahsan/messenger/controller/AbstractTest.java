package com.mahsan.messenger.controller;

import java.nio.charset.Charset;


import com.mahsan.messenger.mapper.MessageMapper;
import com.mahsan.messenger.mapper.MessageMapperImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@ComponentScan(basePackageClasses = {AbstractTest.class,
        MessageMapper.class,
        MessageMapperImpl.class,

})
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@AutoConfigureRestDocs

public  class AbstractTest {

     MockMvc mvc;


    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();

    }


    @Test
    public void testError() throws Exception {

        String uri = "/user";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON)
                .content("{}")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);

    }

      @Test
    public void testEqual() throws Exception {

          String phone1="09048875060";
          String user1="shabab";
          String phone2="09048875059";
          String user2="mahsan";

        String uri = "/message/byPhone/"+phone1+"/"+phone2+"/0/5";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(document("home"))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String expected="{\"totalPage\":1,\"currentPage\":0,\"pageSize\":5,\"list\":[{\"id\":1,\"fromId\":null,\"toId\":null,\"text\":\"message1 from shabab to mahsan\",\"createdDate\":\" 2021.02.07 10:45:26\"},{\"id\":2,\"fromId\":null,\"toId\":null,\"text\":\"message2 from shabab to mahsan\",\"createdDate\":\" 2021.02.07 10:45:26\"}]}";
      String backendResponse=  mvcResult.getResponse().getContentAsString(Charset.forName("utf8"));
      assertEquals(expected,backendResponse);


    }



}