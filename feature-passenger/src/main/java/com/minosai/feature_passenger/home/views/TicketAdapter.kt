package com.minosai.feature_passenger.home.views

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minosai.common.extensions.inflate
import com.minosai.feature_passenger.R
import com.minosai.model.Ticket

class TicketAdapter(private var tickets: List<Ticket> = listOf()) :
    RecyclerView.Adapter<TicketViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val itemView = parent.inflate(R.layout.item_ticket)
        return TicketViewHolder(itemView)
    }

    override fun getItemCount() = tickets.size

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.bind(tickets[position])
    }

    fun update(newTickets: List<Ticket>) {
        tickets = newTickets
        notifyDataSetChanged()
    }

}