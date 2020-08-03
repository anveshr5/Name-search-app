package com.anvesh.myapplication

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var recyclerNames: RecyclerView
    lateinit var searchNameAdapter: SearchNameAdapter
    lateinit var toolbar: Toolbar

    var namesList = arrayListOf<String>(
            "Anvesh", "Pragnya", "Abhiman", "Reshwanth", "Sreenija", "Anusha", "Sanjana", "Siddarth",
            "Yaseen", "Sathwik", "Pallavi", "Nikitha", "Rishita", "Karthik", "Rohan", "Surya",
            "Krishna", "Praharsh", "Tarun", "Nandini", "Shreyas", "Jasvita", "Akshay", "Swasthik",
            "Prajwal", "Sunil", "Navneeth", "Anitha", "Catherine", "Saketh", "Preetham", "Aamuktha",
            "Vrunlika", "Ruthvik", "Srikar", "Keerthana", "Pavithra", "Shashank", "Swetha", "Tejaswini",
            "Manasa", "Kashyap", "Maruthi", "Naresh", "Urvashi", "Rashmika", "Keerthi", "Arjun",
            "Vaishnavi", "Prashanth", "Tanishq", "Pawan", "Umaisha", "Vaishnav", "Raunak", "Sreeja",
            "Hareen", "Sushma", "Anirudh", "Goutham", "Eshwar", "Revanth", "Swaroop", "Yoshita", "Roshan",
            "Tahani", "Spoorthi", "Ojasvi", "Farhaan", "Shiva", "Meghana", "Abhinav", "Dheeraj",
            "Rishikesh", "Padmini", "Shilesh", "Varshith", "Bharath", "Hrithik", "Divesh", "Balaji",
            "Abdul", "Bhargavan", "Chetana", "Zeal", "Yatharth", "Emma", "Samantha", "Nora", "Faisal",
            "Ganga", "Himaja", "Jacqueline", "Gaurav", "Harshith", "Yukta", "Vismrithi", "Ujjwal", "Taapsi",
            "Ramya", "Qamar", "Priwik", "Omkar"
    )
    var searchList = arrayListOf<String>()
    var posArray: ArrayList<NameDetails> = arrayListOf()

    val nameComparator = Comparator<NameDetails> { name1, name2 ->
        if (name1.pos == name2.pos) {
            name1.name.compareTo(name2.name, false)
        } else {
            name1.pos.compareTo(name2.pos)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpToolbar()

        recyclerNames = findViewById(R.id.recyclerviewNames)

        recyclerNames.adapter = SearchNameAdapter(namesList)
    }

    fun getSearch(searchText: String) {
        refreshLists()
        val search = searchText.toLowerCase().capitalize().trimEnd()
        Log.d("getSearch",search)
        for (name in namesList) {
            if (name.contains(search,true)){
                posArray.add(NameDetails(name, name.substringBefore(search).length))
                Log.d("names", name)
            }
        }
        Collections.sort(posArray,nameComparator)
        for (name in posArray){
            searchList.add(name.name)
        }
        searchNameAdapter = SearchNameAdapter(searchList)
        recyclerNames.adapter = searchNameAdapter
    }

    private fun refreshLists() {
        searchNameAdapter = SearchNameAdapter(namesList)
        recyclerNames.adapter = searchNameAdapter
        posArray.removeAll(posArray)
        searchList.removeAll(searchList)

    }

    private fun setUpToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_view_menu, menu)

        val searchItem = menu?.findItem(R.id.searchView)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null){
                    getSearch(query)
                    Log.d("submit", query)
                }
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText != null) {
                    getSearch(newText)
                    Log.d("change", newText)
                }
                return true
            }
        })
        return true
    }
}