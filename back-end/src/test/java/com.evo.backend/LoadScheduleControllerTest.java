package com.evo.backend;

import com.evo.backend.controllers.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LoadScheduleControllerTest {

    private MockMvc mockMvc;

    @Mock
    UserController userController;


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                    .build();
    }

    @Test
//    @WithMockOAuth2Scope(scope = "b")
    public void testSchedule() throws Exception {
        mockMvc.perform(get("/me")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

    }
}