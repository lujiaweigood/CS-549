package edu.stevens.cs549.dhts.main;

import edu.stevens.cs549.dhts.resource.NodeResource;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.sse.SseFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class Application extends ResourceConfig {
    public Application() {
        // TODO register SseFeature
//        super(NodeResource.class, SseFeature.class);
        packages("edu.stevens.cs549.dhts.resource")
        .register(ObjectMapperProvider.class)
        .register(JacksonFeature.class)
                .register(SseFeature.class);
    }
}
