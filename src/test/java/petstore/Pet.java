package petstore;


import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;


public class Pet {
  String uri = "https://petstore.swagger.io/v2/pet"; // end da entidade Pet


  public String lerJson(String caminhoJson) throws IOException {
    return new String(Files.readAllBytes(Paths.get(caminhoJson)));

  }

     //Incluir - Create - Post
      @Test
     public void incluirPet() throws IOException {
       String jsonBody = lerJson("db/pet1.json");

       //Dado - Quando - Então (Given - When - Then)

        given()
                .contentType("application/json") //comum em API REST
                .log().all() //Registrar - ida
                .body(jsonBody) //Transmitir mgs
        .when()
                .post(uri)
        .then()
                .log().all() //volta
                .statusCode(200)
        ;
     }


}