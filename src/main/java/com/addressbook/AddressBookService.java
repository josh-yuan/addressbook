package com.addressbook;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.addressbook.admin.ServiceShutdownTask;
import com.addressbook.api.application.AddressBook;
import com.addressbook.config.AddressBookConfiguration;
import com.addressbook.health.TemplateHealthCheck;
import com.addressbook.resources.AddressBookResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Environment;

public class AddressBookService extends Service<AddressBookConfiguration> {

    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                // default dispatcher is NopMessagingDispatcher. To override it,
                // pass java command line arg -Ddispatcher=<NewDispatcher>
                // e.g. java -Ddispatcher=Rabbit -jar msgas-0.0.1.jar server
                // msgas.yml
                String dispatcherClassName = System.getProperty("dispatcher");
                Class<? extends AddressBook> dispatcherClass = RabbitMQDispatcher.class;
                if ((dispatcherClassName != null)
                        && (dispatcherClassName.indexOf("Rabbit") != -1)) {
                    dispatcherClass = RabbitMQDispatcher.class;
                }
                bind(AddressBook.class).to(dispatcherClass);
            }
        });
        injector.getInstance(AddressBookService.class).run(args);
    }

    @Inject
    private AddressBookService(AddressBook dispatcher) {
        super("LockMarker Service");
    }

    @Override
    protected void initialize(MessagingConfiguration configuration,
            Environment environment) {
        try {
            final String template = configuration.getTemplate();
            environment.addHealthCheck(new TemplateHealthCheck(template));
            environment.addResource(new AddressBookResource());
            environment.addTask(new ServiceShutdownTask());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
