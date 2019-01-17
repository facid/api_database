package ru.bellintegrator.practice.organization.controller;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.bellintegrator.practice.Application;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
public class OrganizationControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testFilter() throws Exception{
        Integer id = 1;
        String name = "JetBrains";
        Boolean isActive = true;

        JSONObject body = new JSONObject();
        body.put("name", name);

        mockMvc.perform(
                post("/api/organization/list").contentType(APPLICATION_JSON).content(body.toString())
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id", is(id)))
                .andExpect(jsonPath("$.data[0].name", is(name)))
                .andExpect(jsonPath("$.data[0].isActive", is(isActive)));
    }

    @Test
    public void testGetById() throws Exception{
        Integer id = 1;

        mockMvc.perform(get("/api/organization/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(id)))
                .andExpect(jsonPath("$.data.name", is("JetBrains")))
                .andExpect(jsonPath("$.data.fullName", is("JetBrains")))
                .andExpect(jsonPath("$.data.inn", is("1234567890")))
                .andExpect(jsonPath("$.data.kpp", is("123456789")))
                .andExpect(jsonPath("$.data.address", is("Краснопресненская наб., 14, стр. 1")))
                .andExpect(jsonPath("$.data.phone", is("849595951")))
                .andExpect(jsonPath("$.data.isActive", is(true)));
    }

    @Test
    public void testUpdate() throws Exception{
        Long id = 1L;
        String name = "Jet";
        String fullName = "JetBrains";
        String inn = "1112223334";
        String kpp = "222333789";
        String address = "Краснопресненская наб., 14, стр. 2";

        JSONObject body = new JSONObject();
        body.put("id", id);
        body.put("name", name);
        body.put("fullName", fullName);
        body.put("inn", inn);
        body.put("kpp", kpp);
        body.put("address", address);

        mockMvc.perform(
                post("/api/organization/update").contentType(APPLICATION_JSON).content(body.toString())
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result", is("success")));
    }

    @Test
    public void testSave() throws Exception{
        String name = "Сбертех";
        String fullName = "Сбербанк Технологии";
        String inn = "1415161718";
        String kpp = "757476712";
        String address = "ул. Технологий, д.24";
        Boolean isActive = true;

        JSONObject body = new JSONObject();
        body.put("name", name);
        body.put("fullName", fullName);
        body.put("inn", inn);
        body.put("kpp", kpp);
        body.put("address", address);
        body.put("isActive", isActive);

        mockMvc.perform(
                post("/api/organization/save").contentType(APPLICATION_JSON).content(body.toString())
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result", is("success")));
    }
}
