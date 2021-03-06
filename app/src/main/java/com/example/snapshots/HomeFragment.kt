package com.example.snapshots

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.snapshots.databinding.ActivityMainBinding
import com.example.snapshots.databinding.FragmentHomeBinding
import com.example.snapshots.databinding.ItemSnapshotBinding
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.SnapshotHolder
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var mBinding:  FragmentHomeBinding
    private lateinit var mFirebaseAdapter: FirebaseRecyclerAdapter<Snapshot,SnapshotHolder>
    private lateinit var mLayoutManager:
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false )
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val query = FirebaseDatabase.getInstance().reference.child("snapshots")
        val option= FirebaseRecycleroptions.Builder<Snapshot>()
            .setQuery(query, Snapshot::class.java ).build()

        mFirebaseAdapter = object  : FirebaseRecyclerAdapter<Snapshot, SnapshotHolder>{
            private lateinit var mContext: Context
            override fun oncreateviewholder(parent: ViewGroup, viewtype:Int): SnapshotHolder{
               val view = layoutInflater.form(mContext).inflate(R.layout.item_snapshot, parent, false)
                return SnapshotHolder(view)
            }
            override fun oncreateviewholder(holder: SnapshotHolder, position: Int, model:Snapshot){
                val snapshot = getItem(position)
                with(holder){
                    setListener(snapshot)
                    binding.tvTitle.text = snapshot.title
                    Glide.with(mContext)
                        .load(snapshot.photoUrl)
                        .diskCachestrategy(DiskCacheStrategy.All)
                        .centerCrop()
                        .into(binding.imgPhoto)
                }

            }

            override fun onDatachanged(){
                super.onDataChanged()
                m.Binding.progressBar.visibility = View.GONE
            }
            override fun onError(error: DatabaseError){
                super.onError(error)
                Toast.makeText(mContext, error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    class FirebaseRecycleroptions {
        class Builder<T> {

        }

    }

    inner class SnapshotHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemSnapshotBinding.bind(view)
        fun setListener(snapshot: Snapshot){

        }
    }
}

private fun Any.build(): Any {

}

private fun <T> HomeFragment.FirebaseRecycleroptions.Builder<T>.setQuery(query: DatabaseReference, java: Class<T>): Any {

}

interface FirebaseRecyclerAdapter<T, U> {

    fun oncreateviewholder(parent: ViewGroup, viewtype: Int): HomeFragment.SnapshotHolder
}
