package com.zubair.geniusplaza.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager

import com.zubair.geniusplaza.R
import com.zubair.geniusplaza.adapters.UserAdapter
import com.zubair.geniusplaza.models.User
import com.zubair.geniusplaza.viewmodels.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {
    private lateinit var listViewModel: ListViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        retainInstance = true

        userAdapter = UserAdapter ()
        userAdapter.setHasStableIds(true)

    }

    private fun observeViewModel(){
        listViewModel.users.observe(this, Observer<PagedList<User>> {
            userAdapter.submitList(it)
            if (userAdapter.currentList!!.size > 0) {
                list_progress_bar.visibility = View.GONE
                user_list_rv.visibility = View.VISIBLE
            }
            else{
                list_progress_bar.visibility = View.VISIBLE
                user_list_rv.visibility = View.INVISIBLE
            }
        })
    }

    private fun goToUserCreationFragment() {
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity_container, NewUserFragment())
            .addToBackStack("new_user_fragment").commit()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setUpRecyclerView()
        create_new_user.setOnClickListener(View.OnClickListener { goToUserCreationFragment()})

    }
    private fun setUpRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        user_list_rv.layoutManager = linearLayoutManager
        user_list_rv.adapter = userAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_list, container, false)

}
