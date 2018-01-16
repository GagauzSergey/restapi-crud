package com.restapi.crud;

import com.restapi.crud.model.Tester;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestApiCrudTests {

    private RestTemplate restTemplate;
    private final String baseApiUri = "http://localhost:8080/api/testers/";

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void getOneTester() {
        Tester tester = restTemplate.getForObject(baseApiUri + "1",
                Tester.class);
        System.out.println("Results of test 1 'Get one tester'...");
        System.out.println(tester);
    }


    @Test
    public void getAllTesters() {
        System.out.println("Results of test 2 'Get all testers'...");
        Tester[] testers = restTemplate.getForObject(baseApiUri,
                Tester[].class);
        for (Tester t : testers
                ) {
            System.out.println(t.toString());
        }
    }

    @Test
    public void deleteUser() {
        System.out.println("Results of test 3 'Delete tester'");
        restTemplate.delete(baseApiUri + "9", Tester.class);
        Tester[] testerOlegDelete = restTemplate.getForObject(baseApiUri,
                Tester[].class);
        if (testerOlegDelete.length < 4)
            System.out.println("Tetser is deleted" + " quantity is " + testerOlegDelete.length);
        else System.out.println("Tetser is not deleted" + " quantity is " + testerOlegDelete.length);
    }

    @Test
    public void addUser() {
        System.out.println("Results of test 4 'Add new tester'");
        JSONObject testerPost = new JSONObject();


        try {
            testerPost.put("firstname", "Oleg");
            testerPost.put("lastname", "Kushir");
            testerPost.put("position", "MIDDLE");
            testerPost.put("salary", 2500);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(testerPost.toString(), httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(baseApiUri, HttpMethod.POST,
                entity, String.class);
    }

    @Test
    public void updateUser() {
        System.out.println("Results of test 5 'Update new tester'");
        JSONObject testerPost = new JSONObject();

        try {
            testerPost.put("id", 5);
            testerPost.put("firstname", "Oleg");
            testerPost.put("lastname", "Kushir");
            testerPost.put("position", "MIDDLE");
            testerPost.put("salary", 5500);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(testerPost.toString(), httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange
                ("http://localhost:8080/api/testers/5", HttpMethod.PUT,
                entity, String.class);
    }

    @After
    public void tearDown() {
        System.out.println("Finish testing");
    }
}


/* Return of status code
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            try {
                JSONObject userJson = new JSONObject(responseEntity.getBody());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (responseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            System.out.println("bad credentials");
        }*/

