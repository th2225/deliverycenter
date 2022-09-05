package vo;

public class MenuVO {
    String mname, msoldout, mdetail;
    int mno, mprice;


    // CustomerMenu - showMenuByStore() - �냼�씗
// StoreMenuSetting - selectMenuByStore()
    public MenuVO(int mno, String mname, int mprice, String msoldout, String mdetail) {
        super();
        this.mno = mno;
        this.mprice = mprice;
        this.mname = mname;
        this.msoldout = msoldout;
        this.mdetail = mdetail;
    }


    // StoreMenuSetting - selectOne() - �냼�씗
    public MenuVO(String mname, int mprice, String msoldout, String mdetail) {
        super();
        this.mname = mname;
        this.mprice = mprice;
        this.msoldout = msoldout;
        this.mdetail = mdetail;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMsoldout() {
        return msoldout;
    }

    public void setMsoldout(String msoldout) {
        this.msoldout = msoldout;
    }

    public String getMdetail() {
        return mdetail;
    }

    public void setMdetail(String mdetail) {
        this.mdetail = mdetail;
    }

    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public int getMprice() {
        return mprice;
    }

    public void setMprice(int mprice) {
        this.mprice = mprice;
    }
}
