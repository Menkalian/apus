package de.menkalian.apus.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.menkalian.apus.R
import de.menkalian.apus.ui.adapter.ListListAdapter

class MainListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val listObjects = listOf(
            "Bla",
            "Blub"
        )

        if (listObjects.size > 0) {
            val listView = inflater.inflate(R.layout.mainmenu_list_container, container, false) as RecyclerView
            listView.layoutManager = LinearLayoutManager(context)
            listView.adapter = ListListAdapter(listObjects)
            return listView
        } else {
            return inflater.inflate(R.layout.mainmenu_list_empty, container, false)
        }
    }
}