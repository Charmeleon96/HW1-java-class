import java.util.Date;
//6.
public class SmsMessage extends Message implements IDigital {
    private String phoneNumber;

    public SmsMessage(String sender, String content, Date sendDate, String category, String phoneNumber) throws SmsException {
        super(sender, content, sendDate, category);
        setPhoneNumber(phoneNumber);
    }

    public SmsMessage(String sender, String content, String category, String phoneNumber) throws SmsException {
        this(sender, content, new Date(), category, phoneNumber);
    }
//sets and gets 6a.
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) throws SmsException {
        if (phoneNumber == null || (phoneNumber.length())!= 10) { // c my own exception
            throw new SmsException("invalid phone number (must be 10 digits)");
        }
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String printCommunicationMethod() {
        return "sent via cellular SMS Network";
    }

    @Override
    public String generatePreview() {
        if (super.getContent().length() <= 15) {
            return "[sms] " + super.getSender() + ": " + super.getContent();
        } else {
            return "[sms] " + super.getSender() + ": " + super.getContent().substring(0, 15) + "...";
        }
    }

    @Override
    public String toString() {
        return "[smsMessage] " + super.toString() + " | Phone: " + phoneNumber;
    }
}