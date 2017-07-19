package uni.uni.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uni.uni.R;
import uni.uni.model.NetworksModel;
import uni.uni.service.NetworksService;

import static uni.uni.adapter.NetworkAdapter.networkString;

public class NetworkInfoFragment extends android.support.v4.app.Fragment {

    public NetworkInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_network_info, container, false);

        final NetworksModel object;

        Bundle bundle = this.getArguments();
        if (bundle != null && bundle.containsKey("networkString")) {
            networkString = bundle.getString("networkString");
        }

        object = NetworksService.queryNetworksDB(networkString);

        Log.v("networkString", networkString);
        Log.v("object.getNetwork", object.getNetwork());

        TextView textView1 = (TextView) view.findViewById(R.id.txt_network2);
        textView1.setText(object.getNetwork());



        TextView textView2 = (TextView) view.findViewById(R.id.txt_website2);
        ImageButton imageButtonWebsite1 = (ImageButton) view.findViewById(R.id.txt_imageButtonWebsite2);
        LinearLayout linearLayout1 = (LinearLayout) view.findViewById(R.id.txt_layout_website2);
        if (object.getWebsite().equals("empty")) {
            linearLayout1.setVisibility(View.GONE);
        } else {
            switchAction(textView2, "Website: ", imageButtonWebsite1, object.getWebsite(), true);
        }


        TextView textView3 = (TextView) view.findViewById(R.id.txt_payasyougo2);
        if (object.getPgbalance().equals("empty") && object.getPgremainallow().equals("empty")) {

            textView3.setVisibility(View.GONE);
        } else {
            textView3.setText("Pay as you go details ");
        }

        TextView textView4 = (TextView) view.findViewById(R.id.txt_pgbalance2);
        ImageButton imageButtonWebsite2 = (ImageButton) view.findViewById(R.id.txt_imageButtonPgbalance2);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.txt_layout_pgbalance2);
        if (object.getPgbalance().equals("empty")) {
            linearLayout2.setVisibility(View.GONE);
        } else {

            switchAction(textView4, "Balance: ", imageButtonWebsite2, object.getPgbalance(), false);
        }


        TextView textView5 = (TextView) view.findViewById(R.id.txt_pgremainallow2);
        ImageButton imageButtonWebsite3 = (ImageButton) view.findViewById(R.id.txt_imageButtonPgremainallow2);
        LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.txt_layout_pgremainallow2);
        if (object.getPgremainallow().equals("empty")) {
            linearLayout3.setVisibility(View.GONE);
        } else {

            switchAction(textView5, "Remaining Allowance: ",imageButtonWebsite3, object.getPgremainallow(), false);
        }

        TextView textView6 = (TextView) view.findViewById(R.id.txt_paymonthly2);
        if (object.getPmremainallow().equals("empty") && object.getPgremainallow().equals("empty")) {

            textView6.setVisibility(View.GONE);
        } else {
            textView6.setText("Pay monthly details ");
        }

        TextView textView7 = (TextView) view.findViewById(R.id.txt_pmremainallow2);
        ImageButton imageButtonWebsite4 = (ImageButton) view.findViewById(R.id.txt_imageButtonPmremainallow2);
        LinearLayout linearLayout4 = (LinearLayout) view.findViewById(R.id.txt_layout_pmremainallow2);
        if (object.getPmremainallow().equals("empty")) {
            linearLayout4.setVisibility(View.GONE);
        } else {
            switchAction(textView7, "Remaining Allowance: ",imageButtonWebsite4, object.getPmremainallow(), false);
        }

        TextView textView8 = (TextView) view.findViewById(R.id.txt_pmmakepay2);
        ImageButton imageButtonWebsite5 = (ImageButton) view.findViewById(R.id.txt_imageButtonPmmakepay2);
        LinearLayout linearLayout5 = (LinearLayout) view.findViewById(R.id.txt_layout_pmmakepay2);
        if (object.getPmmakepay().equals("empty")) {
            linearLayout5.setVisibility(View.GONE);
        } else {
            switchAction(textView8, "Make a payment: ",imageButtonWebsite5, object.getPmmakepay(), false);
        }

        TextView textView9 = (TextView) view.findViewById(R.id.txt_custserv2);
        if (object.getCustserv1().equals("empty") && object.getCustserv2().equals("empty")) {

            textView9.setVisibility(View.GONE);
        } else {
            textView9.setText("Customer Service numbers");
        }

        TextView textView10 = (TextView) view.findViewById(R.id.txt_custserv12);
        ImageButton imageButtonWebsite6 = (ImageButton) view.findViewById(R.id.txt_imageButtonCustserv12);
        LinearLayout linearLayout6 = (LinearLayout) view.findViewById(R.id.txt_layout_custserv12);
        if (object.getCustserv1().equals("empty")) {
            linearLayout6.setVisibility(View.GONE);
        } else {
            switchAction( textView10, "Customer Service: ",imageButtonWebsite6, object.getCustserv1(), false);
        }

        TextView textView11 = (TextView) view.findViewById(R.id.txt_custserv22);
        ImageButton imageButtonWebsite7 = (ImageButton) view.findViewById(R.id.txt_imageButtonCustserv22);
        LinearLayout linearLayout7 = (LinearLayout) view.findViewById(R.id.txt_layout_custserv22);
        if (object.getCustserv2().equals("empty")) {
            linearLayout7.setVisibility(View.GONE);
        } else {
            switchAction( textView11, "Customer Service: ",imageButtonWebsite7, object.getCustserv2(), true);
        }
        return view;


    }

    private List<String> Parse(String str) {
        List<String> output = new ArrayList<String>();
        Matcher match = Pattern.compile("[0-9]+|[a-z]+|[A-Z]+").matcher(str);
        while (match.find()) {
            output.add(match.group());
        }

        return output;
    }
    public void switchAction(TextView textView, String action,ImageButton imageButton, String data, boolean counter){

        String identifier = data.substring( 0, 4);
        String information =  data.substring( 4);

        switch (identifier) {
            case "webs":
                // it's going to open the website
                imageButton.setImageResource(R.drawable.ic_website_icon_hdpi);
                openWebsite(imageButton, information);
                textView.setText(action + information);
                break;
            case "call":
                // it's going to make the call
                if (counter){
                    imageButton.setImageResource(R.drawable.ic_call_icon_hdpi);
                callAction(imageButton, information);
                }else{
                    imageButton.setVisibility(View.GONE);
                }
                textView.setText(action + "call " + information);
                break;
            case "text":
                // It's going to send the message
                imageButton.setVisibility(View.GONE);
                List<String> output = Parse(information);
                textView.setText(action + " send message to number " + output.get(1) + " with text " + output.get(0));
                break;
            default:
                throw new IllegalArgumentException("Invalid switch identifier: " + identifier);
        }

    }

    public void callAction(ImageButton imageButton, final String number){

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, null));
                startActivity(callIntent);
            }
        });
    }

    public void openWebsite(ImageButton imageButton, final String website){

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
                startActivity(browserIntent);
            }
        });
    }

    public void sendText(ImageButton imageButton, final String message, final String number){

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Add the phone number in the data
                Uri uri = Uri.parse("smsto:" + number);
                // Create intent with the action and data
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
                // smsIntent.setData(uri); // We just set the data in the constructor above
                // Set the message
                smsIntent.putExtra("sms_body", message);
                startActivity(smsIntent);

            }
        });
    }
}

