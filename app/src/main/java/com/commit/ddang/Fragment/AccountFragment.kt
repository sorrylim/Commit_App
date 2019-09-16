package com.commit.ddang.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.commit.ddang.Activity.ProfileActivity
import com.commit.ddang.R

class AccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_account, container, false)
        val btnLogin = rootView.findViewById<Button>(R.id.account_btn_login)
        val profileIntent = Intent(activity, ProfileActivity::class.java)
        btnLogin?.setOnClickListener {
            startActivity(profileIntent)
        }
        return rootView
    }
}
