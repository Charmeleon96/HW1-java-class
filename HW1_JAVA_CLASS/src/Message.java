import java.util.*;
public abstract class Message {

	protected String sender; // cant be empty
	protected String content; // cant be empty
	protected Date sendDate;
	protected String category;
		
	public Message(String sender, String content, Date sendDate, String category) {
        setSender(sender); // b constractor that gets all parameters, the setters check that they are valid
        setContent(content);
        if (sendDate == null) {
            throw new IllegalArgumentException("Send date cannot be null");
        }
        this.sendDate = sendDate;
        setCategory(category);
    }

    public Message(String sender, String content, String category) {
        this(sender, content, new Date(), category); // c. gets everything but date and updates it
    }
	public void setSender(String sender) // a . sets and gets ! 
	{
		if(sender==null||  sender.isEmpty()  ) {
			throw new IllegalArgumentException("sender cannot be empty");
		}
		this.sender=sender;
	}
	public void setContent(String content)
	{
		if(content==null||  content.isEmpty()  ) {
			throw new IllegalArgumentException("content cannot be empty");
		}
		this.content=content;
	}
	public String getSender()
	{
		return this.sender;
	}
	public String getContent()
	{
		return this.content;
	}
	public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("category cannot be empty");
        }
        this.category = category;
    }
    
	public Boolean find(ArrayList<String> wordList) // e find methoda checks if one of the words is in the message
	{
		for (String word : wordList)
		{
			if(this.content.contains(word))
			{return true;}
		}
		return(false);
		}
	
		public int getWordCount() { //f my own methoda checks word count
	        return this.content.split("\\s+").length;
	    }

	    public abstract String generatePreview(); // g returns shortened preview

	    @Override // d override represnets all of it
	    public String toString() {
	        return "From: " + sender + " | Date: " + sendDate + " | Category: " + category + " | Content: " + content;
	    }
	}