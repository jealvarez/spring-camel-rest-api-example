package org.spring.camel.example.camel.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.http.common.HttpOperationFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.camel.example.model.error.ErrorResponse;

import static org.apache.camel.Exchange.EXCEPTION_CAUGHT;

public class ErrorResponseProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorResponseProcessor.class);

    @Override
    public void process(final Exchange exchange) throws Exception {
        final HttpOperationFailedException httpOperationFailedException = exchange.getProperty(EXCEPTION_CAUGHT, HttpOperationFailedException.class);
        final ObjectMapper objectMapper = new ObjectMapper();
        final ErrorResponse errorResponse = objectMapper.readValue(httpOperationFailedException.getResponseBody(), ErrorResponse.class);

        LOGGER.error(errorResponse.getMessage(), httpOperationFailedException);
    }

}
