package uni.uni.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
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


public class MyNetworkFragment extends Fragment {


    public MyNetworkFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_my_network, container, false);

        Context context = getActivity();
        final NetworksModel object;
        String carrier;

        if (isSimSupport(context) == true){
        TelephonyManager tManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        carrier = tManager.getNetworkOperatorName();
        Log.d("as", "" + carrier);
        }else{
            carrier = "No Sim";
        }

        object = NetworksService.queryNetworksDB(carrier);

        TextView textView1 = (TextView) view.findViewById(R.id.txt_network);
        if (carrier == "NoSim") {
            textView1.setText("No SIM card was detected...");
        }else{textView1.setText(object.getNetwork());}

        TextView textView2 = (TextView) view.findViewById(R.id.txt_website);
        ImageButton imageButtonWebsite1 = (ImageButton) view.findViewById(R.id.txt_imageButtonWebsite);
        LinearLayout linearLayout1 = (LinearLayout) view.findViewById(R.id.txt_layout_website);
        if (object.getWebsite().equals("empty")) {
            linearLayout1.setVisibility(View.GONE);
        } else {
            textView2.setText("Website");
            switchAction(imageButtonWebsite1, object.getWebsite());
        }

        TextView textView3 = (TextView) view.findViewById(R.id.txt_payasyougo);
        if (object.getPgbalance().equals("empty") && object.getPgremainallow().equals("empty")) {

            textView3.setVisibility(View.GONE);
        } else {
            textView3.setText("Pay as you go details: ");
        }

        TextView textView4 = (TextView) view.findViewById(R.id.txt_pgbalance);
        ImageButton imageButtonWebsite2 = (ImageButton) view.findViewById(R.id.txt_imageButtonPgbalance);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.txt_layout_pgbalance);
        if (object.getPgbalance().equals("empty")) {
            linearLayout2.setVisibility(View.GONE);
        } else {
            textView4.setText("Balance");
            switchAction(imageButtonWebsite2, object.getPgbalance()); }


        TextView textView5 = (TextView) view.findViewById(R.id.txt_pgremainallow);
        ImageButton imageButtonWebsite3 = (ImageButton) view.findViewById(R.id.txt_imageButtonPgremainallow);
        LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.txt_layout_pgremainallow);
        if (object.getPgremainallow().equals("empty")) {
            linearLayout3.setVisibility(View.GONE);
        } else {
              textView5.setText("Remaining Allowance");
              switchAction(imageButtonWebsite3, object.getPgremainallow());
        }

        TextView textView6 = (TextView) view.findViewById(R.id.txt_paymonthly);
        if (object.getPmremainallow().equals("empty") && object.getPgremainallow().equals("empty")) {
            textView6.setVisibility(View.GONE);
        } else {
            textView6.setText("Pay monthly details: ");
        }

        TextView textView7 = (TextView) view.findViewById(R.id.txt_pmremainallow);
        ImageButton imageButtonWebsite4 = (ImageButton) view.findViewById(R.id.txt_imageButtonPmremainallow);
        LinearLayout linearLayout4 = (LinearLayout) view.findViewById(R.id.txt_layout_pmremainallow);
        if (object.getPmremainallow().equals("empty")) {
            linearLayout4.setVisibility(View.GONE);
        } else {
            textView7.setText("Remaining Allowance");
            switchAction(imageButtonWebsite4, object.getPmremainallow());
        }

        TextView textView8 = (TextView) view.findViewById(R.id.txt_pmmakepay);
        ImageButton imageButtonWebsite5 = (ImageButton) view.findViewById(R.id.txt_imageButtonPmmakepay);
        LinearLayout linearLayout5 = (LinearLayout) view.findViewById(R.id.txt_layout_pmmakepay);
        if (object.getPmmakepay().equals("empty")) {
            linearLayout5.setVisibility(View.GONE);
        } else {
            textView8.setText("Make a payment");
            switchAction(imageButtonWebsite5, object.getPmmakepay());
        }

        TextView textView9 = (TextView) view.findViewById(R.id.txt_custserv);
        if (object.getCustserv1().equals("empty") && object.getCustserv2().equals("empty")) {
            textView9.setVisibility(View.GONE);
        } else {
            textView9.setText("Customer Service numbers: ");
        }

        TextView textView10 = (TextView) view.findViewById(R.id.txt_custserv1);
        ImageButton imageButtonWebsite6 = (ImageButton) view.findViewById(R.id.txt_imageButtonCustserv1);
        LinearLayout linearLayout6 = (LinearLayout) view.findViewById(R.id.txt_layout_custserv1);
        if (object.getCustserv1().equals("empty")) {
            linearLayout6.setVisibility(View.GONE);
        } else {
            textView10.setText("Customer Service internal number");
            switchAction(imageButtonWebsite6, object.getCustserv1());
        }

        TextView textView11 = (TextView) view.findViewById(R.id.txt_custserv2);
        ImageButton imageButtonWebsite7 = (ImageButton) view.findViewById(R.id.txt_imageButtonCustserv2);
        LinearLayout linearLayout7 = (LinearLayout) view.findViewById(R.id.txt_layout_custserv2);
        if (object.getCustserv2().equals("empty")) {
            linearLayout7.setVisibility(View.GONE);
        } else {
            textView11.setText("Customer Service landline number");
            switchAction(imageButtonWebsite7, object.getCustserv2());
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
    public void switchAction(ImageButton imageButton, String data){

        String identifier = data.substring( 0, 4);
        String information =  data.substring( 4);

        switch (identifier) {
            case "webs":
                // it's going to open the website
                imageButton.setImageResource(R.drawable.ic_website_icon_hdpi);
                openWebsite(imageButton, information);
                break;
            case "call":
                // it's going to make the call
                imageButton.setImageResource(R.drawable.ic_call_icon_hdpi);
                callAction(imageButton, information);
                break;
            case "text":
                // It's going to sned the message
                imageButton.setImageResource(R.drawable.ic_message_icon_hdpi);
                List<String> output = Parse(information);
                String[] array = new String[2];
                array[0] = output.get(0);
                Log.v("Parse array[0] ", array[0] );
                array[1] = output.get(1);
                Log.v("Parse array[1] ", array[1] );
                sendText(imageButton, array[0], array[1]);
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
    public static boolean isSimSupport(Context context)
    {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);  //gets the current TelephonyManager
        return !(tm.getSimState() == TelephonyManager.SIM_STATE_ABSENT);

    }
}

