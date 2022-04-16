package app.management.project.servlets;

import app.management.project.beans.User;
import org.json.simple.parser.*;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.Servlet;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static app.management.project.constants.Constants.*;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = {"/bin/register"}
)
public class RegisterServlet extends SlingAllMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(RegisterServlet.class);

    @Override
    protected void doPost(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws IOException {
        final String username = request.getParameter(USERNAME);
        final String password = request.getParameter(PASSWORD);
        final String email = request.getParameter(EMAIL);

        User user = new User(username, password, email);

        String smtpUsername = parseJsonField(USERNAME);
        String smtpPassword = parseJsonField(PASSWORD);

        if (smtpUsername != null && smtpPassword != null) {

            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.starttls.required", "true");
            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(smtpPassword, smtpPassword);
                }
            });

            Message message = new MimeMessage(session);
            sendMessage(message, user);
        }
        final String linkToRedirect = request.getScheme() + COLON_WITH_TWO_SLASHES + request.getServerName()
                + COLON + request.getServerPort() + CONTENT_HOME + HTML;
        response.sendRedirect(linkToRedirect);
    }

    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) {

    }

    public String parseJsonField(String field) {
        try {
            Object obj = new JSONParser().parse(new FileReader("../../resources/smtp.json"));
            JSONObject jsonObject = (JSONObject) obj;
            return (String) jsonObject.get(USERNAME);
        } catch (IOException | ParseException | JSONException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public void sendMessage(Message message, User user) {
        final String MAIL_SUBJECT = "Project Management App";
        final String MESSAGE = "Hello " + user.getUsername() + ", you registered on my site. Welcome!";
        try {
            message.setFrom(new InternetAddress(SmtpMessage.FROM_ADDRESS));

            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            message.setSubject(MAIL_SUBJECT);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(MESSAGE, SmtpMessage.MESSAGE_TYPE);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
