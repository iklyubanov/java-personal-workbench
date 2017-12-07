import com.alibaba.fastjson.JSON;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FastjsonTest {

    @Test
    public void test() {
        // init class
        Place place = new Place();
        place.setName("Washington DC");

        Human human = new Human();
        human.setMessage("Hi, dude!");
        human.setPlace(place);

        // convert to json
        String jsonString = JSON.toJSONString(human);
        System.out.println(jsonString);
        assertEquals("{\"message\":\"Hi, dude!\",\"place\":{\"name\":\"Washington DC\"}}", jsonString);

        // convert from json
        Human newHuman = JSON.parseObject(jsonString, Human.class);
        newHuman.say(); // print "Hi , World!"
        assertEquals(human.getMessage(), newHuman.getMessage());

    }

    private static class Human {
        private String message;
        private Place place;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Place getPlace() {
            return place;
        }

        public void setPlace(Place place) {
            this.place = place;
        }

        public void say() {
            System.out.println();
            System.out.println(getMessage() + " " + getPlace().getName() + "!");
        }
    }

    private static class Place {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
