package uni.uni.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import uni.uni.R;
import uni.uni.adapter.NetworkAdapter;
import uni.uni.model.NetworksModel;


public class NetworksFragment extends Fragment {

  //  NetworkAdapter adapter;
    RecyclerView recyclerView;

    private void setUpRecyclerView() {
        // There may be a better way to init this
        Realm realm = Realm.getDefaultInstance();


        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        RealmResults<NetworksModel> results = realm.where(NetworksModel.class)
                .notEqualTo("network" , "NoSim")
                .findAllAsync();
        results = results.sort("network");
        ArrayList<NetworksModel> arraylist = new ArrayList(results);

        String[] array = new String[arraylist.size()];

        for(int i = 0; i < arraylist.size(); ++i ){

            array[i] = arraylist.get(i).getNetwork();
        }

        NetworkAdapter adapter = new NetworkAdapter(array , NetworksFragment.this);
        recyclerView.setAdapter(adapter);

        recyclerView.setHasFixedSize(true);

        realm.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_networks, container, false);
        //ButterKnife.bind(this, view);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        setHasOptionsMenu(true);
        setUpRecyclerView();
        Log.v("NetFrag", "oncreateview");
        return view;

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
