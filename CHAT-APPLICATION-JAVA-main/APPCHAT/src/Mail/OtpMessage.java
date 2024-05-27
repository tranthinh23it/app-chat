package Mail;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class OtpMessage {
    private String to;
    private String otp;

    public OtpMessage() {
    }

    public OtpMessage(String to, String otp) {
        this.to = to;
        this.otp = otp;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void send() {
        final String from = "sendmaildacs@gmail.com";
        final String pass = "xiqu kcyc hnpg ivzk";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        };

        Session session = Session.getInstance(properties, authenticator);

        try {
            MimeMessage message = new MimeMessage(session);
            message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            message.setFrom(new InternetAddress(from));
            message.addRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(this.to, false));
            message.setSubject("OTP Verification");
            message.setText("Your OTP is: " + this.otp + "\nThis OTP is valid for 2 minutes.");

            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while sending email.");
        }
    }
}