package ro.alexmamo.firestorepagination.Oldresults

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ro.alexmamo.firestorepagination.databinding.ProductDataBinding
import ro.alexmamo.firestorepagination.utils.Oldresult

class ProductsAdapter : PagingDataAdapter<Oldresult, ProductsAdapter.OldresultViewHolder>(Companion) {
    private var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OldresultViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = ProductDataBinding.inflate(
                layoutInflater,
                parent,
                false
        )
        context = parent.context
        return OldresultViewHolder(dataBinding)

    }

    override fun onBindViewHolder(holder: OldresultViewHolder, position: Int) {
        val Oldresult = getItem(position) ?: return
        holder.bindOldresult(Oldresult)

    }

    companion object : DiffUtil.ItemCallback<Oldresult>() {
        override fun areItemsTheSame(oldItem: Oldresult, newItem: Oldresult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Oldresult, newItem: Oldresult): Boolean {
            return oldItem == newItem
        }
    }

    inner class OldresultViewHolder(
            private var dataBinding: ProductDataBinding
    ) : RecyclerView.ViewHolder(dataBinding.root) {
        fun bindOldresult(Oldresult: Oldresult) {
            dataBinding.date.text = Oldresult.text
            dataBinding.itemcrd.setOnClickListener {
                Toast.makeText(context, Oldresult.pimg0, Toast.LENGTH_SHORT).show()
            }

        }
    }
}