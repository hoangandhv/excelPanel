package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.Cell
import com.example.myapplication.model.ColTitle
import com.example.myapplication.model.RowTitle

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customAdapter = CustomAdapter(this){

        }
        binding.contentContainer.setAdapter(customAdapter)
        binding.contentContainer.setTitle("Đơn vị quản lý")
        val topTitles: MutableList<RowTitle> = mutableListOf()
        val leftTitles: MutableList<ColTitle> = mutableListOf()
        val cells: MutableList<MutableList<Cell>> = mutableListOf()
        var topTitlesFilter: List<RowTitle> = mutableListOf()
        var cellsFilter: List<List<Cell>> = listOf()

        for (i in 0..20) {
            topTitles.add(RowTitle("Col $i",i))
        }
        for (i in 0..30) {
            leftTitles.add(ColTitle("Row $i",i))
        }
        for (i in 0 until leftTitles.size) {
            val cellsI: MutableList<Cell> = mutableListOf()
            for (j in 0 until topTitles.size) {
                cellsI.add(Cell(i,i,j))
            }
            cells.add(cellsI)
        }
        topTitlesFilter = topTitles.map { it.copy() }
        cellsFilter = cells.map { it.map { it.copy() } }
        customAdapter.setAllData(leftTitles, topTitlesFilter, cellsFilter)

        binding.btnFilter.setOnClickListener {
            val listFilter = listOf(2,3,4)
            topTitlesFilter = topTitles.filter { it.position in listFilter } as MutableList<RowTitle>
            val cellsNew: MutableList<List<Cell>> = mutableListOf()
            cells.forEach { item ->
                val itemNew = item.filter { it.col in listFilter } as MutableList<Cell>
                cellsNew.add(itemNew)
            }
            cellsFilter = cellsNew
            customAdapter.setAllData(leftTitles, topTitlesFilter, cellsFilter)
        }

        binding.btnShowAll.setOnClickListener {
            topTitlesFilter = topTitles
            cellsFilter = cells
            customAdapter.setAllData(leftTitles, topTitlesFilter, cellsFilter)
        }

    }
}