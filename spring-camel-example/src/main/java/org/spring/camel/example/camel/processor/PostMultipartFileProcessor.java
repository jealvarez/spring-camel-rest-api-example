package org.spring.camel.example.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import java.io.File;

import static org.apache.camel.Exchange.FILE_NAME;
import static org.apache.http.entity.ContentType.MULTIPART_FORM_DATA;

public class PostMultipartFileProcessor implements Processor {

    private static final String REQUEST_PART_ATTRIBUTE_NAME = "file";

    @Override
    public void process(final Exchange exchange) throws Exception {
        final MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        final String filename = exchange.getIn().getHeader(FILE_NAME, String.class);
        final File file = exchange.getIn().getBody(File.class);

        multipartEntityBuilder.addPart(REQUEST_PART_ATTRIBUTE_NAME, new FileBody(file, MULTIPART_FORM_DATA, filename));
        final HttpEntity httpEntity = multipartEntityBuilder.build();
        exchange.getIn().setBody(httpEntity);
    }

}
