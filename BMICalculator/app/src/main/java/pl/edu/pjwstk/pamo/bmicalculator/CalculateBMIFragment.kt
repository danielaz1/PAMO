package pl.edu.pjwstk.pamo.bmicalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_calculate_b_m_i.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [CalculateBMIFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalculateBMIFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculate_b_m_i, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment CalculateBMI.
         */

        @JvmStatic
        fun newInstance() =
                CalculateBMIFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCalculateButton(view)
    }

    private fun setCalculateButton(view: View) {
        view.button_calculate.setOnClickListener {
            val calculated: Double = calculate(view.mass_input.text.toString().toDouble(),
                    view.height_input.text.toString().toDouble())
            view.textviev_result.text = calculated.toString()
        }
    }

    private fun calculate(mass:Double, height:Double): Double {
        val heightMeters:Double = height / 100
        return mass / (heightMeters * heightMeters)
    }
}
