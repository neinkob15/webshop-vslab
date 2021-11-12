package de.hska.iwi.eshop_categories;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.springframework.stereotype.Repository;

@Repository
public class ProductManager {
    final WebTarget target;

    public ProductManager() {
        Client client = ClientBuilder.newClient();
        target = client.target(UriBuilder.fromUri("http://web-shop-products:8081/").build());
    }

    public void deleteProductsByCategoryId(int id) {
        target
            .path("products")
            .queryParam("categoryId", String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .delete(String.class);
    }
}
