package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.zhouchaoyuan.excelpanel.BaseExcelPanelAdapter
import com.example.myapplication.model.Cell
import com.example.myapplication.model.ColTitle
import com.example.myapplication.model.RowTitle

class CustomAdapter(
    val context: Context,
    val blockListener: (View) -> Unit
): BaseExcelPanelAdapter<RowTitle, ColTitle, Cell>(context) {
    override fun onCreateCellViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder? {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.room_status_normal_cell, parent, false)
        return CellHolder(layout)
    }

    override fun onBindCellViewHolder(
        holder: RecyclerView.ViewHolder?,
        verticalPosition: Int,
        horizontalPosition: Int
    ) {
        val cell = getMajorItem(verticalPosition, horizontalPosition)
        if (null == holder || holder !is CellHolder || cell == null) {
            return
        }
        val viewHolder = holder
        viewHolder.cellContainer.tag = cell
        viewHolder.cellContainer.setOnClickListener(blockListener)
        viewHolder.channelName.text = cell.toString()
    }

    internal class CellHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val channelName: TextView
        val cellContainer: LinearLayout

        init {
            channelName = itemView.findViewById<View>(R.id.channel_name) as TextView
            cellContainer = itemView.findViewById<View>(R.id.pms_cell_container) as LinearLayout
        }
    }


    //=========================================top cell===========================================
    override fun onCreateTopViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder? {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.room_status_top_header_item, parent, false)
        return TopHolder(layout)
    }

    override fun onBindTopViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val rowTitle = getTopItem(position)
        if (null == holder || holder !is TopHolder || rowTitle == null) {
            return
        }
        holder.availableRoomCount.text = rowTitle.title
        if (position == topData.size-1){
            holder.availableRoomCount.setBackgroundResource(R.drawable.bg_text_excel_radius_right)
        } else {
            holder.availableRoomCount.setBackgroundResource(R.drawable.bg_text_excel_no_coner)
        }
    }

    internal class TopHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val availableRoomCount: TextView
        init {
            availableRoomCount = itemView.findViewById<View>(R.id.available_room_count) as TextView
        }
    }

    //=========================================left cell===========================================
    override fun onCreateLeftViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder? {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.room_status_left_header_item, parent, false)
        return LeftHolder(layout)
    }

    override fun onBindLeftViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val colTitle = getLeftItem(position)
        if (null == holder || holder !is LeftHolder || colTitle == null) {
            return
        }
        val viewHolder = holder
        viewHolder.roomTypeLabel.text = colTitle.title
        val lp = viewHolder.root.layoutParams
        viewHolder.root.layoutParams = lp
    }

    internal class LeftHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomTypeLabel: TextView
        val root: View

        init {
            root = itemView.findViewById(R.id.root)
            roomTypeLabel = itemView.findViewById<View>(R.id.room_type_label) as TextView
        }
    }

    //=========================================left-top cell===========================================
    override fun onCreateTopLeftView(): View? {
        return LayoutInflater.from(context).inflate(R.layout.room_status_normal_cell, null)
    }
}