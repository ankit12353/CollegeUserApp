package com.android.collegeapp.ui.main.faculty

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.collegeapp.R
import com.android.collegeapp.databinding.FragmentFacultyBinding
import com.android.collegeapp.base.BaseFragment
import com.android.collegeapp.util.FireBaseConstants.FB_FACULTY
import com.android.collegeapp.util.FireBaseConstants.FB_FACULTY_CSE
import com.android.collegeapp.util.FireBaseConstants.FB_FACULTY_ECE
import com.android.collegeapp.util.FireBaseConstants.FB_FACULTY_ME
import com.android.collegeapp.util.hide
import com.android.collegeapp.util.show
import com.android.collegeapp.util.toast
import com.google.firebase.database.*
import kotlinx.coroutines.launch

class FacultyFragment : BaseFragment<FragmentFacultyBinding>() {

    private lateinit var databaseReference: DatabaseReference

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        databaseReference = FirebaseDatabase.getInstance().reference.child(FB_FACULTY)

        rv()
    }

    private fun rv() {
        //todo in single rv
        lifecycleScope.launch {
            setRv(FB_FACULTY_CSE, binding.rvDepartmentCS, binding.CSNoData)
            setRv(FB_FACULTY_ECE, binding.rvDepartmentECE, binding.ECENoData)
            setRv(FB_FACULTY_ME, binding.rvDepartmentME, binding.MENoData)
        }
    }

    private suspend fun setRv(child: String, rv: RecyclerView, noData: View) {
        var list: MutableList<Faculty>
        val adapter = FacultyRVAdapter()
        val dbPath = databaseReference.child(child)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list = mutableListOf()
                if (!snapshot.exists()) {
                    noData.show()
                    rv.hide()
                } else {
                    noData.hide()
                    rv.show()
                    snapshot.children.forEach { snap ->
                        list.add(snap.getValue(Faculty::class.java)!!)
                    }
                    adapter.addItems(list)
                    setUpRv(rv, adapter)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                context!!.toast(error.toString())
            }
        }
        dbPath.addValueEventListener(listener)
    }

    private fun setUpRv(rv: RecyclerView, RVAdapter: FacultyRVAdapter) {
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = RVAdapter
        }

    override fun setFragmentView() = R.layout.fragment_faculty
}