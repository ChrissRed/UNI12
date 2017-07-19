package uni.uni.service;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import uni.uni.model.NetworksModel;

import static io.realm.Case.INSENSITIVE;



public class NetworksService {

    Realm realm = Realm.getDefaultInstance();

    public static NetworksModel queryNetworksDB(String carrier) {
        Realm realm = Realm.getDefaultInstance();
        String first2char = carrier.substring(  0, 2);
        RealmResults<NetworksModel> result = realm.where(NetworksModel.class)
                .beginsWith("network", first2char, INSENSITIVE)
                .findAllAsync();

        NetworksModel object = new NetworksModel();

        object.setNetwork(result.first().getNetwork());
        object.setWebsite(result.first().getWebsite());
        object.setPgbalance(result.first().getPgbalance());
        object.setPgremainallow(result.first().getPgremainallow());
        object.setPmremainallow(result.first().getPmremainallow());
        object.setPmmakepay(result.first().getPmmakepay());
        object.setCustserv1(result.first().getCustserv1());
        object.setCustserv2(result.first().getCustserv2());


        realm.close();

        return object;
    }


    public static void saveNetwork(final int id, final String network, final String website, final String pgbalance,
                                   final String pgremainallow, final String pmremainallow, final String pmmakepay,
                                   final String custserv1, final String custserv2) {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm netsave) {
                NetworksModel networks = netsave.createObject(NetworksModel.class, id);
                networks.setNetwork(network);
                networks.setWebsite(website);
                networks.setPgbalance(pgbalance);
                networks.setPgremainallow(pgremainallow);
                networks.setPmremainallow(pmremainallow);
                networks.setPmmakepay(pmmakepay);
                networks.setCustserv1(custserv1);
                networks.setCustserv2(custserv2);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.v("DATABASE", "stored ok");
            }

        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.v("DATABASE", "not stored");
            }

        });

        realm.close();
    }

    public static void saveNetworks() {

        saveNetwork(1, "EE", "webshttp://ee.co.uk/", "textbalance150", "empty", "textAL150", "call360", "call150", "call07953 966 250");
        saveNetwork(2, "Vodafone", "webshttps://www.vodafone.co.uk/", "call*#1345#", "call2345", "call44555", "call56677", "call191","call03333040191");
        saveNetwork(3, "Vasile", "webshttp://ee.co.uk/", "textBalance150", "empty", "textAL150", "call360", "call150", "call07953 966 250");
        saveNetwork(4, "Three", "webshttp://ee.co.uk/", "textBalance150", "empty", "textAL150", "call360", "call150", "call07953 966 250");
        saveNetwork(5, "O2", "webshttp://ee.co.uk/", "textBalance150", "empty", "textAL150", "call360", "call150", "call07953 966 250");
        saveNetwork(6, "Virgin Media", "webshttp://ee.co.uk/", "textBalance150", "empty", "textAL150", "call360", "call150", "call07953 966 250");
        saveNetwork(7, "Tesco Mobile", "webshttp://ee.co.uk/", "textBalance150", "empty", "textAL150", "call360", "call150", "call07953 966 250");
        saveNetwork(8, "Sky Mobile", "webshttp://ee.co.uk/", "textBalance150", "empty", "textAL150", "call360", "call150", "call07953 966 250");
        saveNetwork(9, "T-Mobile", "webshttp://ee.co.uk/", "textBalance150", "empty", "textAL150", "call360", "call150", "call07953 966 250");
        saveNetwork(10, "Plusnet", "webshttp://ee.co.uk/", "textBalance150", "empty", "textAL150", "call360", "call150", "call07953 966 250");
        saveNetwork(11, "Orange", "webshttp://ee.co.uk/", "textBalance150", "empty", "textAL150", "call360", "call150", "call07953 966 250");
        saveNetwork(12, "Talkmobile", "webshttp://ee.co.uk/", "textBalance150", "empty", "textAL150", "call360", "call150", "call07953 966 250");
        saveNetwork(13, "FreedomPop", "webshttp://ee.co.uk/", "textBalance150", "empty", "textAL150", "call360", "call150", "call07953 966 250");
        saveNetwork(14, "giffgaff", "webshttp://ee.co.uk/", "textBalance150", "empty", "textAL150", "call360", "call150", "call07953 966 250");
        saveNetwork(15, "rds", "webshttp://ee.co.uk/", "textBalance150", "empty", "textAL150", "call360", "call150", "call07953 966 250");
        saveNetwork(16, "NoSim", "empty", "empty", "empty", "empty", "empty", "empty", "empty");
    }

}
