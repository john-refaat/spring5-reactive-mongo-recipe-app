package guru.springframework.controllers;

import guru.springframework.config.WebConfig;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

/**
 * @author john
 * @since 18/04/2024
 */
@RunWith(MockitoJUnitRunner.class)
public class RouterFunctionTest {


    WebTestClient webTestClient;

    @Mock
    RecipeService recipeService;

    @Before
    public void setUp() throws Exception {
        WebConfig webConfig = new WebConfig();
        RouterFunction<?> routerFunction = webConfig.routes(recipeService);
        webTestClient = WebTestClient.bindToRouterFunction(routerFunction).build();
    }

    @Test
    public void testGetRecipe() throws Exception {
        Recipe recipe1 = Recipe.builder().id("1").description("Recipe1").build();
        Recipe recipe2 = Recipe.builder().id("2").description("Recipe2").build();
        Mockito.when(recipeService.getRecipes()).thenReturn(Flux.just(recipe1, recipe2));
        webTestClient.get().uri("/api/recipes")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }
}
