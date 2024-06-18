/*
 * Copyright contributors to the Galasa project
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package dev.galasa.events.kafka.internal;

import javax.validation.constraints.NotNull;

import org.osgi.service.component.annotations.Component;

import dev.galasa.framework.spi.EventsException;
import dev.galasa.framework.spi.IConfigurationPropertyStoreService;
import dev.galasa.framework.spi.IEventsServiceRegistration;
import dev.galasa.framework.spi.IFramework;
import dev.galasa.framework.spi.IFrameworkInitialisation;
import dev.galasa.framework.spi.SystemEnvironment;

@Component(service = { IEventsServiceRegistration.class })
public class KafkaEventsServiceRegistration implements IEventsServiceRegistration {

    private final String NAMESPACE = "kafka";
    
    @Override
    public void initialise(@NotNull IFrameworkInitialisation frameworkInitialisation)
            throws EventsException {

        try {

            IFramework framework = frameworkInitialisation.getFramework();

            SystemEnvironment env = new SystemEnvironment();
            KafkaEventProducerFactory producerFactory = new KafkaEventProducerFactory(env);
            IConfigurationPropertyStoreService cpsService = framework.getConfigurationPropertyService(NAMESPACE);

            frameworkInitialisation.registerEventsService(new KafkaEventsService(cpsService, producerFactory));

        } catch (Exception e) {
            throw new KafkaException("Unable to register the Kafka Events Service", e);
        }

    }

}