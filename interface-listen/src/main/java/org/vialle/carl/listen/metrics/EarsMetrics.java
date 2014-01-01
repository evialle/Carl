package org.vialle.carl.listen.metrics;

import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by eric on 31/12/2013.
 */
@Component
@ManagedResource(objectName = "interface-listen:name=CarlEars")
public class EarsMetrics {

    private AtomicInteger messageCounter = new AtomicInteger();

    public AtomicInteger getMessageCounter() {
        return messageCounter;
    }

}
