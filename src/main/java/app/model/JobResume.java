package app.model;
import javax.persistence.*;

@Entity
@Table(name = "job_resume")
public class JobResume {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "user_id")
	private long user_id;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "file_path")
	private String filePath;
	
	@Column(name = "file_hash")
	private String fileHash;

	@Override
	public String toString() {
		return "JobResume [id=" + id + ", user_id=" + user_id + ", fileName=" + fileName + ", filePath=" + filePath
				+ ", fileHash=" + fileHash + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileHash() {
		return fileHash;
	}

	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}
	
	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public JobResume() {}

	public JobResume(long id, long user_id, String fileName, String filePath, String fileHash) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileHash = fileHash;
	}

	
	
	
}
