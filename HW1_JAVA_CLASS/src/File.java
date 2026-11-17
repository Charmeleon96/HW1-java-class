import java.util.Objects;

public class File {
private String fileName;
private String fileType;
public File(String fileName, String fileType)
{
if(fileName==null || fileName.isEmpty() ||  fileType== null || fileType.isEmpty()) //b. no empty verb
{
	throw new IllegalArgumentException("File name and type cannot be empty");
}
this.fileName=fileName; //seeting
this.fileType=fileType;

}

public String getFileName() //a. get / sets
{
	return this.fileName;
}
public String getFileType()
{
	return this.fileType;
}
public void setFileName(String fileName)
{
	if(fileName==null || fileName.isEmpty()  ) {
		throw new IllegalArgumentException("File name cannot be empty");
	}
	this.fileName=fileName;
}
public void setFileType(String fileType)
{
	if(fileType==null || fileType.isEmpty()  ) {
		throw new IllegalArgumentException("File type cannot be empty");
	}
	this.fileType=fileType;
}


@Override
public boolean equals(Object obj) { // d adding methoda to my taste
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    File other = (File) obj;
    return Objects.equals(fileName, other.fileName) && Objects.equals(fileType, other.fileType);
}

@Override
public String toString() { // c override to to string
    return "File: " + fileName + "." + fileType;
}
}
