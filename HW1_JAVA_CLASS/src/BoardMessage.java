import java.util.*;

public class BoardMessage extends Message {
private PriorityType priority;
private String boardName;

public BoardMessage(String sender, String content, Date sendDate, String category, PriorityType priority, String boardName) {
    super(sender, content, sendDate, category);
    setPriority(priority); // b constractor that gets all properties and checks validaity
    setBoardName(boardName);
}

public BoardMessage(String sender, String content, String category, String boardName) {
    this(sender, content, new Date(), category, PriorityType.REGULAR, boardName);
} // c. constractor that gets everything but date and priority and sets them and priority to regular


public PriorityType getPriority() // sets and gets
{
	return this.priority;
}
public void setPriority(PriorityType priority)
{
	if (priority == null) {
        throw new IllegalArgumentException("Priority cannot be null");
    }
	this.priority=priority;
} 
public String getBoardName() {
    return this.boardName;
}

public void setBoardName(String boardName) {
    if (boardName == null || boardName.trim().isEmpty()) {
        throw new IllegalArgumentException("Board name cannot be empty");
    }
    this.boardName = boardName;
}

public boolean isUrgent() {// e methoda fof my choosing
    return this.priority == PriorityType.URGENT;
}
@Override
public String toString() //d override to string 
{
	return "[BoardMessage] " + super.toString() + " | Board: " + boardName + " | Priority: " + priority;
}
@Override
public String generatePreview() // f generate preview 
{
if (super.getContent().length()<=15)
{return ("[Board] "+ super.getSender() + ": " + super.getContent());	}
else {
return("[Board] "+ super.getSender() + ": " + super.getContent().substring(0, 15)+ "...");	
}
	
}
}
