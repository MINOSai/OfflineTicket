package com.minosai.feature_passenger.home.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.minosai.model.Ticket
import kotlinx.android.synthetic.main.item_ticket.view.*

class TicketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(ticket: Ticket) = with(itemView) {
        item_ticket_cost.text = ticket.amount.toString()
        item_ticket_source.text = ticket.source
        item_ticket_destination.text = ticket.destination
    }

}