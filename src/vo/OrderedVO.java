package vo;

public class OrderedVO {

    int ono, cno, mno, sno, oamount, oprice;
    String sname, odate, ostatus, opayment, oaddress, ophone, orderlist; // OrderList


    public OrderedVO(int ono, int cno, int mno, int sno, int oamount, int oprice, String odate, String ostatus) {
        this.ono = ono;
        this.cno = cno;
        this.mno = mno;
        this.sno = sno;
        this.oamount = oamount;
        this.oprice = oprice;
        this.odate = odate;
        this.ostatus = ostatus;
    }

	public OrderedVO(int ono, String sname, int oamount, int oprice, String odate, String ostatus, String opayment, String oaddress, String ophone, String orderlist){
		this.ono = ono;
		this.sname = sname;
		this.oamount = oamount;
		this.oprice = oprice;
		this.odate = odate;
		this.ostatus = ostatus;
		this.opayment = opayment;
		this.oaddress = oaddress;
		this.ophone = ophone;
		this.orderlist = orderlist;
	}

    public int getOno() {
        return ono;
    }

    public void setOno(int ono) {
        this.ono = ono;
    }

    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
    }

    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public int getOamount() {
        return oamount;
    }

    public void setOamount(int oamount) {
        this.oamount = oamount;
    }

    public int getOprice() {
        return oprice;
    }

    public void setOprice(int oprice) {
        this.oprice = oprice;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getOdate() {
        return odate;
    }

    public void setOdate(String odate) {
        this.odate = odate;
    }

    public String getOstatus() {
        return ostatus;
    }

    public void setOstatus(String ostatus) {
        this.ostatus = ostatus;
    }

    public String getOpayment() {
        return opayment;
    }

    public void setOpayment(String opayment) {
        this.opayment = opayment;
    }

    public String getOaddress() {
        return oaddress;
    }

    public void setOaddress(String oaddress) {
        this.oaddress = oaddress;
    }

    public String getOphone() {
        return ophone;
    }

    public void setOphone(String ophone) {
        this.ophone = ophone;
    }

    public String getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(String orderlist) {
        this.orderlist = orderlist;
    }
}
