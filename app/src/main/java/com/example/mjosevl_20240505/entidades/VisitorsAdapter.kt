package com.example.mjosevl_20240505.entidades


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mjosevl_20240505.R

class VisitorsAdapter(var visitors: List<Visitor>) : RecyclerView.Adapter<VisitorsAdapter.VisitorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitorViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_visitor, parent, false)
        return VisitorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VisitorViewHolder, position: Int) {
        val visitor = visitors[position]
        holder.bind(visitor)
    }

    override fun getItemCount() = visitors.size

    class VisitorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val rutTextView: TextView = itemView.findViewById(R.id.textRut)
        private val nameTextView: TextView = itemView.findViewById(R.id.textName)
        private val apartmentTextView: TextView = itemView.findViewById(R.id.textApartment)

        fun bind(visitor: Visitor) {
            rutTextView.text = visitor.rut
            nameTextView.text = "${visitor.firstName} ${visitor.lastName}"
            apartmentTextView.text = visitor.apartment
        }
    }
}
