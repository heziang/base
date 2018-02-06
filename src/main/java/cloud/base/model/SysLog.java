package cloud.base.model;

import java.util.Date;

/**
 * @author Heziang
 * 系统日志类
 */
public class SysLog extends BaseModel{
	private String logid;
	private String logtitle;
	private String logcontent;
	private String logtype;
	private Date createtime;
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getLogtitle() {
		return logtitle;
	}
	public void setLogtitle(String logtitle) {
		this.logtitle = logtitle;
	}
	public String getLogcontent() {
		return logcontent;
	}
	public void setLogcontent(String logcontent) {
		this.logcontent = logcontent;
	}
	public String getLogtype() {
		return logtype;
	}
	public void setLogtype(String logtype) {
		this.logtype = logtype;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
