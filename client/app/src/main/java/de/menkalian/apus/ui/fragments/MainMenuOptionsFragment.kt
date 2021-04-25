package de.menkalian.apus.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import de.menkalian.apus.R

class MainMenuOptionsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Choose layout depending on the percentage of the screen taken in
        val percentage = container!!.height.toDouble() / container.rootView.height

        println(container)
        if (percentage >= 0.2) {
            return inflater.inflate(R.layout.mainmenu_options_small, container, false)
        } else {
            return inflater.inflate(R.layout.mainmenu_options_small, container, false)
        }
    }
}