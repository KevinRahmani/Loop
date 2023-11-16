package utils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleRefreshTokenRequest;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import com.google.auth.Credentials;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.apache.commons.codec.binary.Base64;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.*;

import static com.google.api.services.gmail.GmailScopes.GMAIL_SEND;
import static javax.mail.Message.RecipientType.TO;
public class GMailerUtils {

    public static final String TEST_EMAIL = "loop.cytech@gmail.com";
    private final Gmail service;

    public GMailerUtils() throws Exception {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        service = new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport, jsonFactory))
                .setApplicationName("TestProd")
                .build();
    }

    /*
    public static Credentials getCredentials() throws IOException, GeneralSecurityException {

        // Load client secrets.

        String CREDENTIAL_PATH = "/client_secret_374793696744-jibejlvrihi3tb2o5hdn3ebndqq82n4c.apps.googleusercontent.com.json";
        InputStream in = GMailerUtils.class.getResourceAsStream(CREDENTIAL_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIAL_PATH);
        }
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(in)
                .createDelegated(TEST_EMAIL)
                .createScoped(List.of(GmailScopes.GMAIL_SEND, GmailScopes.GMAIL_LABELS));

        credentials.refreshIfExpired();
        return credentials;
    }


     */

    public Credential getCredentials(NetHttpTransport HTTP_TRANSPORT,JsonFactory JSON_FACTORY ) throws GeneralSecurityException, IOException, FileNotFoundException {
        // Load client secrets.
        String CREDENTIALS_FILE_PATH = "/credentials.json"; //OAuth 2.0 client credentials json
        InputStream in = GMailerUtils.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        String clientId = clientSecrets.getDetails().getClientId();
        String clientSecret = clientSecrets.getDetails().getClientSecret();

        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(HTTP_TRANSPORT)
                .setJsonFactory(JSON_FACTORY)
                .setClientSecrets(clientId, clientSecret)
                .build();

        String refreshToken = "1//04fUrqgyLZUfdCgYIARAAGAQSNwF-L9IrcGLmfBnjU8LHD2NleTbD3WwQMsXL9lgs8PdHJd4zBVcwk3pNirnKiCfKUgy-_cummfA";
        credential.setAccessToken(getNewToken(refreshToken, clientId, clientSecret));
        credential.setRefreshToken(refreshToken);

        return credential;
    }


    private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory)
            throws IOException {
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(GMailerUtils.class.getResourceAsStream("/credentials.json")));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, Set.of(GMAIL_SEND))
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                .setAccessType("offline")
                .build();

        String clientId = clientSecrets.getDetails().getClientId();
        String clientSecret = clientSecrets.getDetails().getClientSecret();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();

        Credential credential =  new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        String refreshToken = "1//04fUrqgyLZUfdCgYIARAAGAQSNwF-L9IrcGLmfBnjU8LHD2NleTbD3WwQMsXL9lgs8PdHJd4zBVcwk3pNirnKiCfKUgy-_cummfA";
        credential.setAccessToken(getNewToken(refreshToken, clientId, clientSecret));
        credential.setRefreshToken(refreshToken);

        return credential;

    }





    public void sendMail(String subject, String message, String mailClient) throws Exception {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(TEST_EMAIL));
        email.addRecipient(TO, new InternetAddress(mailClient));
        email.setSubject(subject);
        email.setContent(message, "text/html");

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message msg = new Message();
        msg.setRaw(encodedEmail);

        try {
            msg = service.users().messages().send("me", msg).execute();
            System.out.println("Message id: " + msg.getId());
            System.out.println(msg.toPrettyString());
        } catch (GoogleJsonResponseException e) {
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 403) {
                System.err.println("Unable to send message: " + e.getDetails());
            } else {
                throw e;
            }
        }
    }

    public static String getNewToken(String refreshToken, String clientId, String clientSecret) throws IOException {
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(new NetHttpTransport())
                .setJsonFactory(new JacksonFactory())
                .setClientSecrets(clientId, clientSecret)
                .build();

        credential.setRefreshToken(refreshToken);

        // Rafraîchit le token
        credential.refreshToken();

        return credential.getAccessToken();
    }

}