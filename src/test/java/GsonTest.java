import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class GsonTest {

    @Test
    public void test() {
        Person valentine = new Person("Valentine");
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(valentine).replaceAll("\n", "").replaceAll(" ", "");
        System.out.println(json);
        assertEquals("{\"name\":\"Valentine\"}", json);

        JsonObject x = new JsonObject();
        x.addProperty("name", "Valentine");

        String jsonFromObj = x.toString().replaceAll("\n", "").replaceAll(" ", "");
        assertEquals(json, jsonFromObj);
    }

    private class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
