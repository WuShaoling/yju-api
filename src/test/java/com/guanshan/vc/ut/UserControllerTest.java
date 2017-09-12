package com.guanshan.vc.ut;

import com.guanshan.nlp.webapp.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by bennettqian on 04/08/2017.
 */
@RunWith(SpringRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
//@ComponentScan(basePackages = {"com.guanshan.vc.webapp"})
public class UserControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        //       mvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testUserInfo() throws Exception {
        RequestBuilder requestBuilder = get("/user/company/13661828533/info");
//                .requestAttr("organizationId","company")
//                .requestAttr("phone","13661828533");
        this.mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testGetAllUser() throws Exception {
        RequestBuilder requestBuilder = get("/fetchAllUser")
                .param("organizationId","company");
        this.mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());
    }


}

