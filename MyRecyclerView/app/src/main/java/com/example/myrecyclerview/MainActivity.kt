package com.example.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var title = "Mode List"
    private val list = ArrayList<Vegetable>()
    private var mode: Int = 0

    companion object {
        private const val STATE_TITLE = "state_string"
        private const val STATE_LIST = "state_list"
        private const val STATE_MODE = "state_mode"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_vegetables.setHasFixedSize(true)

        if (savedInstanceState == null) {
            setActionBarTitle(title)
            list.addAll(getListVegetables())
            showRecyclerList()
            mode = R.id.action_list
        } else {
            title = savedInstanceState.getString(STATE_TITLE).toString()
            val stateList = savedInstanceState.getParcelableArrayList<Vegetable>(STATE_LIST)
            val stateMode = savedInstanceState.getInt(STATE_MODE)
            setActionBarTitle(title)
            if (stateList != null) {
                list.addAll(stateList)
            }
            setMode(stateMode)
        }
    }

    private fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }

    fun getListVegetables(): ArrayList<Vegetable> {
        val dataName =
            resources.getStringArray(R.array.data_name) // mengambil resource dari string.xml
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)

        val listVegetable = ArrayList<Vegetable>()
        for (position in dataName.indices) {
            val vegetable = Vegetable(
                dataName[position],
                dataDescription[position],
                dataPhoto[position]
            )
            listVegetable.add(vegetable)
        }
        return listVegetable
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_TITLE, title)
        outState.putParcelableArrayList(STATE_LIST, list)
        outState.putInt(STATE_MODE, mode)
    }

    private fun showRecyclerList() {
        rv_vegetables.layoutManager = LinearLayoutManager(this)
        val listVegetableAdapter = ListVegetableAdapter(list)
        rv_vegetables.adapter = listVegetableAdapter

        listVegetableAdapter.setOnItemClickCallback(object :
            ListVegetableAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Vegetable) {
                showSelectedVegetable(data)
            }
        })
    }

    private fun showRecyclerGrid() {
        rv_vegetables.layoutManager = GridLayoutManager(this, 2) // banyak kolom grid
        val gridVegetableAdapter = GridVegetableAdapter(list)
        rv_vegetables.adapter = gridVegetableAdapter


        gridVegetableAdapter.setOnItemClickCallback(object :
            GridVegetableAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Vegetable) {
                showSelectedVegetable(data)
            }
        })
    }

    private fun showRecyclerCardView() {
        rv_vegetables.layoutManager = LinearLayoutManager(this)
        val cardViewVegetableAdapter = CardViewVegetableAdapter(list)
        rv_vegetables.adapter = cardViewVegetableAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                title = "Mode List"
                showRecyclerList()
            }

            R.id.action_grid -> {
                title = "Mode Grid"
                showRecyclerGrid()
            }

            R.id.action_cardview -> {
                title = "Mode CardView"
                showRecyclerCardView()
            }
        }
        mode = selectedMode
        setActionBarTitle(title)
    }

    private fun showSelectedVegetable(vegetable: Vegetable) {
            Toast.makeText(this, "Kamu memilih ${vegetable.name}", Toast.LENGTH_SHORT).show()
    }
}