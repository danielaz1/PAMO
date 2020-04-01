package pl.edu.pjwstk.pamo.bmicalculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoronaStats#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoronaStats extends Fragment {

    public CoronaStats() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment CoronaStats.
     */
    public static CoronaStats newInstance(String param1, String param2) {
        CoronaStats fragment = new CoronaStats();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_corona_stats, container, false);

        WebView webView = view.findViewById(R.id.corona_stats_webView);
        webView.loadUrl("file:///android_asset/index.html");
        webView.getSettings().setJavaScriptEnabled(true);

        return view;
    }
}
