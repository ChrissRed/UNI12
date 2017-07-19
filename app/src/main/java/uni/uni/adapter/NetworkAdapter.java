package uni.uni.adapter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import uni.uni.R;
import uni.uni.fragments.NetworkInfoFragment;
import uni.uni.fragments.NetworksFragment;

public class NetworkAdapter extends RecyclerView.Adapter<NetworkAdapter.CustomViewHolder> {


    public static String networkString;
    private String[] networks;
    private NetworksFragment context;

    public NetworkAdapter(String[] networks, NetworksFragment context) {
        this.networks = networks;
        this.context = context;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView name_network;
        private ImageButton imageButton;


        public CustomViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view_networks);
            name_network = (TextView) itemView.findViewById(R.id.txt_view_network);
            imageButton = (ImageButton) itemView.findViewById(R.id.txt_imageButton);

            imageButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){

                    networkString = String.valueOf(name_network.getText());
                    Fragment fragment = new Fragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("networkString", networkString);
                    fragment.setArguments(bundle);

                    AppCompatActivity rootActivity = (AppCompatActivity) view.getContext();
                    Fragment newFragment = new NetworkInfoFragment();

                    rootActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, newFragment)
                            //.addToBackStack(null)
                            .commit();

                }
            });

        }
    }

    @Override
    public void onBindViewHolder(CustomViewHolder networksViewHolder, int i) {
        networksViewHolder.name_network.setText(networks[i]);

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, viewGroup, false);
        CustomViewHolder networksViewHolder = new CustomViewHolder(view);
        return networksViewHolder;
    }

    @Override
    public int getItemCount() {
        int size = networks.length;
        return size;
    }



}