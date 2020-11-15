package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_activity, container, false)
        val book_recipe_link: TextView = view.findViewById(R.id.recipe_menu)
        book_recipe_link.setOnClickListener{
            val transaction = getFragmentManager()?.beginTransaction()
            if (transaction != null) {
                transaction.replace(R.id.main, RecipeBookFragment()).addToBackStack(null).commit()
            }
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}