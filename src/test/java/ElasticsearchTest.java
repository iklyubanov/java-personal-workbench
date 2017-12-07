import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;
import spark.ModelAndView;
import spark.Spark;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class ElasticsearchTest {

    @Test
    public void test() throws UnknownHostException {
        Client client = TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
//        Spark.get("/", (request, response) -> {
//            SearchResponse searchResponse =
//                    client.prepareSearch("music").setTypes("lyrics").execute().actionGet();
//            SearchHit[] hits = searchResponse.getHits().getHits();
//
//            Map<String, Object> attributes = new HashMap<>();
//            attributes.put("songs", hits);
//
//            return new ModelAndView(attributes, "index.mustache");
//        }, new MustacheTemplateEngine());

    }
}
