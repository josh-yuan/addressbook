package test.java.com.addressbook.resources;

import com.addressbook.resources.AddressBookResource;
import com.yammer.dropwizard.testing.ResourceTest;

import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;


/**
 * Unit tests on LockMarker Service resources
 */
public class AddressBookResourcesTest extends ResourceTest {

    private static final String serviceEndpoint = "/v1.0/";
    private static final AddressBookResource testServer = new AddressBookResource(); 


    @Override
    protected void setUpResources() {
        addResource(testServer);
    }

    // @Test
    public void testPingPong() throws Exception {
        ObjectNode response = JsonNodeFactory.instance.objectNode();
        response.put("status", 200);
        response.put("message", "pong");

        assertThat(
                "Test Ping Pong API",
                client().resource(serviceEndpoint + "ping")
                        .type("application/json").accept("application/json")
                        .get(ObjectNode.class), equalTo(response));
    }

}
