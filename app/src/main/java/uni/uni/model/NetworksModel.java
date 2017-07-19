package uni.uni.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class NetworksModel extends RealmObject{

    public static final String ID = "id";
    public static final String NETWORK = "network";
    public static final String WEBSITE = "website";
    public static final String PGBALANCE = "payasyougobalance";
    public static final String PGREMAINALLOW = "payasyougoremainingallowance";
    public static final String PMREAMAINALLOW = "paymonthlyremainingallowance";
    public static final String PMMAKEPAYM = "paymonthlymakepayment";
    public static final String CUSTSERV1 = "customerservice1";
    public static final String CUSTSERV2 = "customerservice2";


    @PrimaryKey
    private int id;
    private String network;
    private String website;
    private String pgbalance;
    private String pgremainallow;
    private String pmremainallow;
    private String pmmakepay;
    private String custserv1;
    private String custserv2;


    public NetworksModel setId(int id){
        this.id = id;
        return this;
    }
    public int getId(){
        return this.id;
    }

    public NetworksModel setNetwork(String network){
        this.network = network;
        return this;
    }

    public String getNetwork(){
        return this.network;
    }

    public NetworksModel setWebsite(String website){
        this.website = website;
        return this;
    }

    public String getWebsite(){
        return this.website;
    }

    public String getPgbalance() {
        return pgbalance;
    }

    public void setPgbalance(String pgbalance) {
        this.pgbalance = pgbalance;
    }

    public String getPgremainallow() {
        return pgremainallow;
    }

    public void setPgremainallow(String pgremainallow) {
        this.pgremainallow = pgremainallow;
    }

    public String getPmremainallow() {
        return pmremainallow;
    }

    public void setPmremainallow(String pmremainallow) {
        this.pmremainallow = pmremainallow;
    }

    public String getPmmakepay() {
        return pmmakepay;
    }

    public void setPmmakepay(String pmmakepay) {
        this.pmmakepay = pmmakepay;
    }

    public String getCustserv1() {
        return custserv1;
    }

    public void setCustserv1(String custserv1) {
        this.custserv1 = custserv1;
    }

    public String getCustserv2() {
        return custserv2;
    }

    public void setCustserv2(String custserv2) {
        this.custserv2 = custserv2;
    }


}
