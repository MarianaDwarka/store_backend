package com.marianadwarka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marianadwarka.dto.CategoryDTO;
import com.marianadwarka.dto.GenericResponseRecord;
import com.marianadwarka.security.JwtRequest;
import com.marianadwarka.security.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class RestTemplateController {

    //JDK 11 HttpClient .get post put ... | RestTemplate MVC deprecated | WebClient Webflux

    private final RestTemplate restTemplate;

    @GetMapping("/pokemon/{name}")
    public ResponseEntity<String> getPokemon(@PathVariable("name") String name) {
        String pokemonUrl = "https://pokeapi.co/api/v2/pokemon/" + name;
        String response = restTemplate.getForEntity(pokemonUrl, String.class).getBody();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/test1")
    public ResponseEntity<GenericResponseRecord<CategoryDTO>> test1() {
        String url = "http://localhost:8080/categories";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ParameterizedTypeReference<GenericResponseRecord<CategoryDTO>> typeRef = new ParameterizedTypeReference<>(){};

        return restTemplate.exchange(url, HttpMethod.GET, entity, typeRef);
    }

    @GetMapping("/test2")
    public ResponseEntity<String> test2(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ){
        String url = "http://localhost:8080/categories/pagination2?p=" + page + "&s=" + size;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/test3")
    public ResponseEntity<Map> test3(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ){
        String url = "http://localhost:8080/categories/pagination2?p={page}&s={size}";

        Map<String, Integer> uriVariables = new HashMap<>();
        uriVariables.put("page", page);
        uriVariables.put("size", size);

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, Map.class, uriVariables);
    }

    @PostMapping("/test4")
    public ResponseEntity<CategoryDTO> test4(@RequestBody CategoryDTO dto){
        String url = "http://localhost:8080/categories";

        HttpEntity<CategoryDTO> requestEntity = new HttpEntity<>(dto);

        CategoryDTO response = restTemplate.postForObject(url, requestEntity, CategoryDTO.class);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/test5/{id}")
    public ResponseEntity<CategoryDTO> test5(@PathVariable("id") String id, @RequestBody CategoryDTO dto){
        String url = "http://localhost:8080/categories/" + id;

        HttpEntity<CategoryDTO> requestEntity = new HttpEntity<>(dto);

        return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, CategoryDTO.class);
    }

    @DeleteMapping("/test6/{id}")
    public ResponseEntity<Void> test6(@PathVariable("id") Integer id){
        String url = "http://localhost:8080/categories/" + id;
        restTemplate.delete(url);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/test7/{id}")
    public ResponseEntity<Void> test7(@PathVariable("id") Integer id){
        String url = "http://localhost:8080/categories/{idCategory}";

        Map<String, Integer> uriVariables = new HashMap<>();
        uriVariables.put("idCategory", id);

        restTemplate.delete(url, uriVariables);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/test8/{id}")
    public ResponseEntity<Void> test8(@PathVariable("id") Integer id, @RequestBody JwtRequest request) throws Exception{
        String token = generateToken(request.getUsername(), request.getPassword());

        //Enviar el token para pedir o hacer acciones
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);

        String url = "http://localhost:8080/categories/{idCategory}";
        Map<String, Integer> uriVariables = new HashMap<>();
        uriVariables.put("idCategory", id);

        restTemplate.exchange(url, HttpMethod.DELETE, jwtEntity, String.class, uriVariables);

        return ResponseEntity.noContent().build();
    }

    private String generateToken(String username, String password) throws JsonProcessingException {
        final String AUTHENTICATION_URL = "http://localhost:8080/login";
        JwtRequest userRequest = new JwtRequest(username, password);
        String userRequestJSON = new ObjectMapper().writeValueAsString(userRequest);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<String> authEntity = new HttpEntity<>(userRequestJSON, httpHeaders);
        ResponseEntity<JwtResponse> authResponse = restTemplate.exchange(AUTHENTICATION_URL, HttpMethod.POST, authEntity, JwtResponse.class);

        if(authResponse.getBody() != null){
            return authResponse.getBody().jwtToken();
        }
        return "INVALID_TOKEN";

    }

}
