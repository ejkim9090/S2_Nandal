package common.smtp;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticatior extends Authenticator {
	@Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("보낼메일입력","비번입력");
    }

}
