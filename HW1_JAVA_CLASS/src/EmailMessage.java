import java.util.*;

public class EmailMessage extends Message implements IDigital {
    private String subject;
    private ArrayList<File> attachments;

    public EmailMessage(String sender, String content, Date sendDate, String category, String subject, ArrayList<File> attachments) {
        super(sender, content, sendDate, category);
        setSubject(subject); //b constructor 
        this.attachments = (attachments != null) ? new ArrayList<>(attachments) : new ArrayList<>();
    }


    public EmailMessage(String sender, String content, String category, String subject) {
        this(sender, content, new Date(), category, subject, new ArrayList<>());
    }// constructor that gets all but date and dynamic array
	
    public String getSubject() { // a. set and get method
        return this.subject;
    }

    public void setSubject(String subject) {
        if (subject == null || subject.isEmpty()) {
            throw new IllegalArgumentException("Subject cannot be empty");
        }
        this.subject = subject;
    }
    

public ArrayList<File> getAttachments() {
    return new ArrayList<>(attachments);
}
public void addAttachment(File file) //g
{
	if(file==null) {
		throw new IllegalArgumentException("cant append null file");
	} // when constructing a file i already implemented the checks that its not null
	attachments.add(file);
}
public void removeAttachment(File file) throws AttachmentException//h // removes every file with filename and if not found throws check
{
	if(file==null) {
		throw new IllegalArgumentException("cant remove null file");
	} // when constructing a file i already implemented the checks that its not null
	boolean removed = attachments.removeIf(f -> f.equals(file));
    if (!removed) {
        throw new AttachmentException("Attachment not found: " + file);
    }
}

@Override // e.
public String printCommunicationMethod() {
    return "Sent via Email Server";
}

@Override // f
public String generatePreview() {
    return "[Email] Subject: " + subject + " | From: " + getSender();
}

@Override // d tostring override
public String toString() {
    return "[emailMessage] " + super.toString() + " | subject: " + subject + " | attachments: " + attachments;
}
}