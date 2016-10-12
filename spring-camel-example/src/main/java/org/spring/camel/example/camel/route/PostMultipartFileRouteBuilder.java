package org.spring.camel.example.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.common.HttpOperationFailedException;
import org.spring.camel.example.camel.processor.ErrorResponseProcessor;
import org.spring.camel.example.camel.processor.PostMultipartFileProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PostMultipartFileRouteBuilder extends RouteBuilder {

    @Value("${org.spring.camel.example.directory.path.in:/opt/apps/spring-camel-example/input}")
    private String directoryPathIn;

    @Value("${org.spring.camel.example.output.rest.endpoint:http4://127.0.0.1:8080/authority-group/upload-file}")
    private String outputRestEndpoint;

    @Override
    public void configure() throws Exception {
        // @formatter:off
        from("file:" + directoryPathIn + "?moveFailed=.failed/${date:now:yyyyMMdd}/${file:onlyname}&move=.processed/${date:now:yyyyMMdd}/${file:onlyname}&doneFileName=${file:name}.done")
            .process(new PostMultipartFileProcessor())
            .doTry()
                .to(outputRestEndpoint)
            .doCatch(HttpOperationFailedException.class)
                .process(new ErrorResponseProcessor());
        // @formatter:on
    }

}
