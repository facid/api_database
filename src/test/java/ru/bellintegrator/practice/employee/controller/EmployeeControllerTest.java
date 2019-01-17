package ru.bellintegrator.practice.employee.controller;

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
public class EmployeeControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testFilter() throws Exception{
        Long officeId = 1L;

        JSONObject body = new JSONObject();
        body.put("officeId", officeId);

        mockMvc.perform(
                post("/api/user/list").contentType(APPLICATION_JSON).content(body.toString())
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].firstName", is("Владимир")))
                .andExpect(jsonPath("$.data[0].secondName", is("Иванов")))
                .andExpect(jsonPath("$.data[0].middleName", is("Иванович")))
                .andExpect(jsonPath("$.data[0].position", is("Программист")))
                .andExpect(jsonPath("$.data[0].isIdentified", is(true)))
                .andExpect(jsonPath("$.data[1].id", is(6)))
                .andExpect(jsonPath("$.data[1].firstName", is("Захар")))
                .andExpect(jsonPath("$.data[1].secondName", is("Мазуров")))
                .andExpect(jsonPath("$.data[1].middleName", is("")))
                .andExpect(jsonPath("$.data[1].position", is("Программист")))
                .andExpect(jsonPath("$.data[1].isIdentified", is(true)));
    }

    @Test
    public void testGetById() throws Exception{
        Integer id = 1;

        mockMvc.perform(get("/api/user/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(id)))
                .andExpect(jsonPath("$.data.firstName", is("Владимир")))
                .andExpect(jsonPath("$.data.secondName", is("Иванов")))
                .andExpect(jsonPath("$.data.middleName", is("Иванович")))
                .andExpect(jsonPath("$.data.position", is("Программист")))
                .andExpect(jsonPath("$.data.phone", is("89685061821")))
                .andExpect(jsonPath("$.data.docData.docNumber", is("1234567891")))
                .andExpect(jsonPath("$.data.docData.docDate", is("1981-03-23")))
                .andExpect(jsonPath("$.data.docData.docType.docName", is("Паспорт гражданина РФ")))
                .andExpect(jsonPath("$.data.docData.docType.docCode", is("21")))
                .andExpect(jsonPath("$.data.country.citizenshipName", is("Российская Федерация")))
                .andExpect(jsonPath("$.data.country.citizenshipCode", is("643")))
                .andExpect(jsonPath("$.data.isIdentified", is(true)));
    }

    @Test
    public void testUpdate() throws Exception{
        Long id = 1L;
        String firstName = "Владислав";
        String position = "Старший программист";

        JSONObject body = new JSONObject();
        body.put("id", id);
        body.put("firstName", firstName);
        body.put("position", position);

        mockMvc.perform(
                post("/api/user/update").contentType(APPLICATION_JSON).content(body.toString())
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result", is("success")));
    }

    @Test
    public void testSave() throws Exception{
        Long officeId = 1L;
        String firstName = "Александр";
        String position = "Программист";
        Boolean isIdentified = true;

        JSONObject body = new JSONObject();
        body.put("officeId", officeId);
        body.put("firstName", firstName);
        body.put("position", position);
        body.put("isIdentified", isIdentified);

        mockMvc.perform(
                post("/api/user/save").contentType(APPLICATION_JSON).content(body.toString())
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result", is("success")));
    }
}
