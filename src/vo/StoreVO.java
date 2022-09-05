package vo;

public class StoreVO {

	String sname, sphone, semail, sid, spw, spw_question, spw_answer, saddress, scategory, sruntime, sofftime, soffday;
	int sno, sdeliveryfee, sminoprice;

	public StoreVO(String sname, String sphone, String semail, String sid, String spw, String spw_question,
			String spw_answer, String saddress) {
		super();
		this.sname = sname;
		this.sphone = sphone;
		this.semail = semail;
		this.sid = sid;
		this.spw = spw;
		this.spw_question = spw_question;
		this.spw_answer = spw_answer;
		this.saddress = saddress;
	}

	// CustomerMenuList - selectCategory()
	public StoreVO(int sno, String sname, int sdeliveryfee, int sminoprice) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.sdeliveryfee = sdeliveryfee;
		this.sminoprice = sminoprice;
	}

	// Login
	public StoreVO(String sid, String spw) {
		super();
		this.sid = sid;
		this.spw = spw;
	}

	// �������� �����Ҷ� �ʿ��� vo -- �ν�
	public StoreVO() {
	}

	public StoreVO(String scategory, String sname, String sphone, String spw, String saddress, String sruntime,
			String sofftime, String soffday, int sdeliveryfee, int sminoprice) {
		super();
		this.scategory = scategory;
		this.sname = sname;
		this.sphone = sphone;
		this.spw = spw;
		this.saddress = saddress;
		this.sruntime = sruntime;
		this.sofftime = sofftime;
		this.soffday = soffday;
		this.sdeliveryfee = sdeliveryfee;
		this.sminoprice = sminoprice;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSphone() {
		return sphone;
	}

	public void setSphone(String sphone) {
		this.sphone = sphone;
	}

	public String getSemail() {
		return semail;
	}

	public void setSemail(String semail) {
		this.semail = semail;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSpw() {
		return spw;
	}

	public void setSpw(String spw) {
		this.spw = spw;
	}

	public String getSpw_question() {
		return spw_question;
	}

	public void setSpw_question(String spw_question) {
		this.spw_question = spw_question;
	}

	public String getSpw_answer() {
		return spw_answer;
	}

	public void setSpw_answer(String spw_answer) {
		this.spw_answer = spw_answer;
	}

	public String getSaddress() {
		return saddress;
	}

	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getSdeliveryfee() {
		return sdeliveryfee;
	}

	public void setSdeliveryfee(int sdeliveryfee) {
		this.sdeliveryfee = sdeliveryfee;
	}

	public int getSminoprice() {
		return sminoprice;
	}

	public void setSminoprice(int sminoprice) {
		this.sminoprice = sminoprice;
	}

	public String getScategory() {
		return scategory;
	}

	public void setScategory(String scategory) {
		this.scategory = scategory;
	}

	public String getSruntime() {
		return sruntime;
	}

	public void setSruntime(String sruntime) {
		this.sruntime = sruntime;
	}

	public String getSofftime() {
		return sofftime;
	}

	public void setSofftime(String sofftime) {
		this.sofftime = sofftime;
	}

	public String getSoffday() {
		return soffday;
	}

	public void setSoffday(String soffday) {
		this.soffday = soffday;
	}

}
