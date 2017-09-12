package com.guanshan.vc.ut;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.guanshan.nlp.webapp.Application;
import com.guanshan.vc.webapp.controller.IndexController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
//@ComponentScan(basePackages = {"com.guanshan.vc.webapp"})
public class IndexControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        //       mvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testInitialUrl() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
//                .andExpect(content().string(containsString("Hello Mock")));
    }

    @Test
    public void testPayload() throws Exception {
        this.mockMvc.perform(get("/authpayload")).andDo(print()).andExpect(status().isOk());
    }


}
