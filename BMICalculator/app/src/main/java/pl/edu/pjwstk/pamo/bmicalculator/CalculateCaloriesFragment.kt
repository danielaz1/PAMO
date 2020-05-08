package pl.edu.pjwstk.pamo.bmicalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_calculate_calories.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [CalculateCaloriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalculateCaloriesFragment : Fragment() {

    internal enum class Gender {
        WOMAN, MAN
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculate_calories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCalculateButton(view)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment CalculateCaloriesFragment.
         */
        @JvmStatic
        fun newInstance() =
                CalculateCaloriesFragment()

    }

    private fun setCalculateButton(view: View) {
        val heightInput = view.editText_height
        val massInput = view.editText_mass
        val ageInput = view.editText_age
        val manRadio = view.radioButton_man
        val res = view.textView_result
        val gender = if (manRadio.isChecked) Gender.MAN else Gender.WOMAN
        view.button_calculate.setOnClickListener {
            val calculated = kcalToString(massInput.text.toString(), heightInput.text.toString(), ageInput.text.toString(), gender)
            res.text = calculated
        }
    }

    private fun kcalToString(massString: String, heightString: String, ageString: String, gender: Gender): String {
        val mass = massString.toDouble()
        val height = heightString.toDouble()
        val age = ageString.toInt()
        return calculate(mass, height, age, gender).toString()
    }

    private fun calculate(mass: Double, height: Double, age: Int, gender: Gender): Double {
        return if (gender == Gender.MAN) {
            66.47 + 13.7 * mass + 5.0 * height - 6.76 * age
        } else {
            655.1 + 9.567 * mass + 1.85 * height - 4.68 * age
        }
    }
}
