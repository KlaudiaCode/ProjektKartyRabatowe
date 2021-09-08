package domanska.klaudia.kartylojalnosciowe.ui.cardList

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import domanska.klaudia.kartylojalnosciowe.databinding.CardRecyclerViewItemBinding
import java.io.File

class CardListAdapter : RecyclerView.Adapter<CardListAdapter.ViewHolder>() {
    private var data = arrayOf<File>()

    class ViewHolder(
        val binding: CardRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardRecyclerViewItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bitmap = BitmapFactory.decodeFile(data[position].absolutePath)
        holder.binding.cardImage.layoutParams.width
        holder.binding.cardImage.setImageBitmap(bitmap)
        holder.binding.imageName.text = data[position].nameWithoutExtension

        holder.binding.root.setOnClickListener {
            val action = CardListFragmentDirections.actionCardListFragmentToCardDetailsFragment(data[position].absolutePath)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: Array<File>?) {
        newData?.let {
            data = it
        }
        notifyDataSetChanged()
    }
}