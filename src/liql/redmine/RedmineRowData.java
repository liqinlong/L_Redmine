package liql.redmine;

public class RedmineRowData {
	String pro_name;
	Integer issue_id;
	String subject;
	String tracker_name;
	String status;
	String priority;
	String author;
	String assignee;
	String create_time;
	String start_time;
	String update_time;
	String due_time;
	String desc;
	
	public String toString(){
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("issue_id["+getIssue_id()+"]");
		sbuf.append("author["+getAuthor()+"]");
		sbuf.append("assignee["+getAssignee()+"]");
		sbuf.append("pro_name["+getPro_name()+"]");
		sbuf.append("tracker_name["+getTracker_name()+"]");
		sbuf.append("issue_id["+getIssue_id()+"]");
		sbuf.append("priority["+getPriority()+"]");
		sbuf.append("status["+getStatus()+"]");
		sbuf.append("create_time["+getCreate_time()+"]");
		
		return sbuf.toString();
		
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public Integer getIssue_id() {
		return issue_id;
	}

	public void setIssue_id(Integer issue_id) {
		this.issue_id = issue_id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTracker_name() {
		return tracker_name;
	}

	public void setTracker_name(String tracker_name) {
		this.tracker_name = tracker_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getDue_time() {
		return due_time;
	}

	public void setDue_time(String due_time) {
		this.due_time = due_time;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public RedmineRowData(String pro_name, Integer issue_id, String subject, String tracker_name, String status,
			String priority, String author, String assignee, String create_time, String start_time, String update_time,
			String due_time, String desc) {
		super();
		this.pro_name = pro_name;
		this.issue_id = issue_id;
		this.subject = subject;
		this.tracker_name = tracker_name;
		this.status = status;
		this.priority = priority;
		this.author = author;
		this.assignee = assignee;
		this.create_time = create_time;
		this.start_time = start_time;
		this.update_time = update_time;
		this.due_time = due_time;
		this.desc = desc;
	}
}
