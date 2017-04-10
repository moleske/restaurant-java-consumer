package com.oleske;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class PactTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("restaurant_api", this);

    @Pact(consumer = "restaurant_consumer")
    public PactFragment createGetRestaurantFragment(PactDslWithProvider builder) {
        return builder
                .given("there are available restaurants")
                .uponReceiving("restaurant list request")
                .path("/restaurant")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(new HashMap<String, String>(){{put("Content-Type", "application/json");}})
                .body(PactDslJsonArray
                        .arrayMinLike(0, 1)
                        .id("id")
                        .stringMatcher("name", ".*")
                        .stringMatcher("ownerName", ".*")
                        .stringMatcher("headChefName", ".*")
                        .stringMatcher("cuisineType", ".*")
                        .stringMatcher("shortDescription", ".*")
                        .stringMatcher("fullDescription", ".*")
                        .stringMatcher("websiteUrl", ".*")
                        .integerType("rating")
                        .integerType("michelinStarRating")
                        .integerType("zagatRating"))
                .toFragment();
    }

    @Test
    @PactVerification(fragment = "createGetRestaurantFragment")
    public void getRestaurantContract() throws Exception {
        ResponseEntity<List<Restaurant>> entity = testRestTemplate.exchange(
                mockProvider.getConfig().url() + "/restaurant",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Restaurant>>() {
                });
        assertThat(entity.getStatusCodeValue()).isEqualTo(200);
        assertThat(entity.getBody().size()).isGreaterThan(0);
        Restaurant restaurant = entity.getBody().get(0);
        assertThat(restaurant.getId()).isGreaterThanOrEqualTo(0);
        assertThat(restaurant.getName()).isNotNull();
        assertThat(restaurant.getOwnerName()).isNotNull();
        assertThat(restaurant.getHeadChefName()).isNotNull();
        assertThat(restaurant.getCuisineType()).isNotNull();
        assertThat(restaurant.getShortDescription()).isNotNull();
        assertThat(restaurant.getFullDescription()).isNotNull();
        assertThat(restaurant.getWebsiteUrl()).isNotNull();
        assertThat(restaurant.getRating()).isGreaterThanOrEqualTo(0);
        assertThat(restaurant.getMichelinStarRating()).isGreaterThanOrEqualTo(0);
        assertThat(restaurant.getZagatRating()).isGreaterThanOrEqualTo(0);
    }
}
