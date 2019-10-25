package com.zubair.geniusplaza.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.zubair.geniusplaza.R
import com.zubair.geniusplaza.models.NewUser
import com.zubair.geniusplaza.viewmodels.NewUserViewModel
import kotlinx.android.synthetic.main.fragment_new_user.*


class NewUserFragment : Fragment() {
    private lateinit var newUserViewModel: NewUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newUserViewModel = ViewModelProviders.of(this).get(NewUserViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel(){
        newUserViewModel.newUserCreated.observe(this, Observer { user ->
            if(user){
                Toast.makeText(context, getString(R.string.success), Toast.LENGTH_LONG).show()
                goToUserListFragment()
            }
            else{
                Toast.makeText(context, getString(R.string.fail), Toast.LENGTH_LONG).show()
            }
            progress_bar.visibility = View.INVISIBLE
            first_name.visibility = View.VISIBLE
            last_name.visibility = View.VISIBLE
            email.visibility = View.VISIBLE
        })
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_new_user, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newUserButton.setOnClickListener(View.OnClickListener {
            progress_bar.visibility = View.VISIBLE
            first_name.visibility = View.INVISIBLE
            last_name.visibility = View.INVISIBLE
            email.visibility = View.INVISIBLE

            newUserViewModel.createNewUser(
                NewUser(
                first_name.text.toString(),
                last_name.text.toString(),
                email.text.toString()
            )
            )
        })
    }

    private fun goToUserListFragment() {
        activity!!.supportFragmentManager.popBackStack()
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity_container, ListFragment())
            .addToBackStack(null).commit()
    }

}
