package liql.svn;

public class SVNRowData {
	String commitlog;
	String commitauthor;
	String commitdate;
	long version;

	public SVNRowData(String log, String author, String date,long ver) {
		commitlog = log;
		commitauthor = author;
		commitdate = date;
		version = ver;
		
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getCommitlog() {
		return commitlog;
	}

	public void setCommitlog(String commitlog) {
		this.commitlog = commitlog;
	}

	public String getCommitauthor() {
		return commitauthor;
	}

	public void setCommitauthor(String commitauthor) {
		this.commitauthor = commitauthor;
	}

	public String getCommitdate() {
		return commitdate;
	}

	public void setCommitdate(String commitdate) {
		this.commitdate = commitdate;
	}
}
