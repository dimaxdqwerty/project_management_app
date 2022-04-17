package app.management.project.configs.impl;

import app.management.project.configs.SmtpConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@Component(service = SmtpConfig.class, immediate = true)
@Designate(ocd = SmtpConfigImpl.SmtpConfiguration.class)
public class SmtpConfigImpl implements SmtpConfig {

    @ObjectClassDefinition(name = "Smtp Configuration")
    public @interface SmtpConfiguration {

        @AttributeDefinition(
                name = "Username",
                description = "Enter username of your email account which will send messages",
                type = AttributeType.STRING
        )
        String getUsername();

        @AttributeDefinition(
                name = "Password",
                description = "Enter password of your email account which will send messages",
                type = AttributeType.STRING
        )
        String getPassword();
    }

    private String username;
    private String password;

    @Activate
    protected void activate(SmtpConfiguration smtpConfiguration) {
        username = smtpConfiguration.getUsername();
        password = smtpConfiguration.getPassword();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
