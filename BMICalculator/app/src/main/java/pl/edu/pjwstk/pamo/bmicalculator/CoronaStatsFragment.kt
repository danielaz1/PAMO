package pl.edu.pjwstk.pamo.bmicalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_corona_stats.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [CoronaStatsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoronaStatsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_corona_stats, container, false)

        view.corona_stats_webView.settings.javaScriptEnabled = true
        view.corona_stats_webView.loadUrl("file:///android_asset/index.html")

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment CoronaStatsFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                CoronaStatsFragment()
    }
}
