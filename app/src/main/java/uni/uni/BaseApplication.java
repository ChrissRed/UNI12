package uni.uni;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static uni.uni.service.NetworksService.saveNetworks;


public class BaseApplication extends Application{


    @Override
    public void onCreate(){
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        saveNetworks();

//           Realm realm = Realm.getDefaultInstance();
//           if (realm.isEmpty() == true) {
//                saveNetworks();
//                Log.v("database", "stored ok");
//                }
//            realm.close();
    }
}




