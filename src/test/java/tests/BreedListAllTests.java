package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import support.BreedListAllUtils;

public class BreedListAllTests {
    @Test
    public void testBreedListAll() {
        Response response = BreedListAllUtils.getBreedListAll();
        BreedListAllUtils.assertBreedListAllResponse(response);
    }

}
