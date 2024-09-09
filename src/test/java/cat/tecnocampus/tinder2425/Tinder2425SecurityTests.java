package cat.tecnocampus.tinder2425;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ContextConfiguration
@WithMockUser(username="admin",roles={"USER","ADMIN"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Tinder2425SecurityTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    @WithMockUser(username="josep",roles={"ADMIN"})
    void testGetAllProfilesAdmin() throws Exception {
        mockMvc.perform(get("/profiles")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @Order(1)
    @WithMockUser(username="josep",roles={"USER"})
    void testGetAllProfilesUser() throws Exception {
        mockMvc.perform(get("/profiles")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }
}
