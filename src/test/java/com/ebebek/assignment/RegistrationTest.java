package com.ebebek.assignment;

import static com.ebebek.assignment.TestHelper.TEST_USER;
import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.ebebek.assignment.TestHelper;
import com.ebebek.assignment.controller.UserCreationRequest;
import com.ebebek.assignment.model.User;
import com.ebebek.assignment.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RegistrationTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void clean() {
        User user = userRepository.findByUserName(TEST_USER);
        if (user != null) {
            userRepository.delete(user);
        }
    }

    @Test
    public void registration() throws Exception {
        String url = "http://localhost:" + port + "/register";
        MultiValueMap<String, String> map = createMap();
        MockHttpServletRequestBuilder builder = post(url).with(csrf());
        builder.contentType(MediaType.APPLICATION_FORM_URLENCODED).params(map);
        MockHttpServletResponse response = mvc.perform(builder).andReturn().getResponse();
        HttpStatus responseStatus = HttpStatus.valueOf(response.getStatus());
        assertTrue(HttpStatus.FOUND.equals(responseStatus));
    }

    private MultiValueMap<String, String> createMap() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        UserCreationRequest userCreationRequest = TestHelper.createUserCreationRequest();
        ObjectMapper m = new ObjectMapper();
        TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
        };
        Map<String, String> convertedMap = m.convertValue(userCreationRequest, typeRef);
        convertedMap.forEach((key, value) -> map.put(key, Collections.singletonList(value)));
        return map;
    }

}
