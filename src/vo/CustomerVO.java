package vo;

public class CustomerVO {
    String cname, cphone, cemail, cid, cpw, cpw_question, cpw_answer, caddress;
    int cno;


    // �쉶�썝媛��엯�떆 �븘�슂�븳 �쁺�뿭 -- 誘쇱떇
    public CustomerVO(String cname, String cphone, String cemail, String cid, String cpw, String cpw_question, String cpw_answer, String caddress) {
        super();
        this.cname = cname;
        this.cphone = cphone;
        this.cemail = cemail;
        this.cid = cid;
        this.cpw = cpw;
        this.cpw_question = cpw_question;
        this.cpw_answer = cpw_answer;
        this.caddress = caddress;
    }

    public CustomerVO(int cno, String cid, String cpw, String cname, String caddress, String cphone,
                      String cpw_question, String cpw_answer, String cemail) {
        super();
        this.cno = cno;
        this.cid = cid;
        this.cpw = cpw;
        this.cname = cname;
        this.caddress = caddress;
        this.cphone = cphone;
        this.cpw_question = cpw_question;
        this.cpw_answer = cpw_answer;
        this.cemail = cemail;
    }

    // CustomerOrder �뿉 �븘�슂�븳 �깮�꽦�옄
    public CustomerVO(int cno, String caddress, String cphone) {
        super();
        this.cno = cno;
        this.caddress = caddress;
        this.cphone = cphone;
    }

    public CustomerVO(String cid, String cpw) {
        super();
        this.cid = cid;
        this.cpw = cpw;
    }

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCphone() {
		return cphone;
	}

	public void setCphone(String cphone) {
		this.cphone = cphone;
	}

	public String getCemail() {
		return cemail;
	}

	public void setCemail(String cemail) {
		this.cemail = cemail;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCpw() {
		return cpw;
	}

	public void setCpw(String cpw) {
		this.cpw = cpw;
	}

	public String getCpw_question() {
		return cpw_question;
	}

	public void setCpw_question(String cpw_question) {
		this.cpw_question = cpw_question;
	}

	public String getCpw_answer() {
		return cpw_answer;
	}

	public void setCpw_answer(String cpw_answer) {
		this.cpw_answer = cpw_answer;
	}

	public String getCaddress() {
		return caddress;
	}

	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}
    
    
    
}
