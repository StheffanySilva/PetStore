package petstore;


import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;


public class Pet {
    String uri = "https://petstore.swagger.io/v2/pet"; // end da entidade Pet


    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));

    }

    //Incluir - Create - Post
    @Test (priority = 1)
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        // Sintaxe Gherkin
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
                .body("name", is("Snoopy"))
                .body("status", is("available"))
                .body("category.name", is("CODOG01"))
                .body("tags.name", contains("data"))

        ;
    }
    //Get
    @Test (priority = 2)
    public void consultarPet() {
        String petId = "1995061926";

        String token =
        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/" +petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Snoopy"))
                .body("status", is("available"))
                .body("category.name", is("CODOG01"))

        .extract()
                .path("category.name")
        ;
                System.out.println("O token é " + token);

    }
}
