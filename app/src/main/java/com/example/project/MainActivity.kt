package com.example.project

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    //define a variable named data
    // data is an array list of items -individual objects- that will be displayed in the recyclerview
    val data= arrayListOf<listobj>();


    // declare an adapter for the recycleview's Adapter class
    lateinit var adapter:recycleviewadapter;


    //have a method that gets called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setting the content view of the main activity class to its xml layout
        setContentView(R.layout.activity_main)
        // declare a variable "searchview" of type SearchView
        //initialize searchview by using its id, the id is found in the SearchView widget layout
        val searchview:androidx.appcompat.widget.SearchView=findViewById(R.id.searchview);        // basically getting the searchview layout
        // declare a variable "viewPager" of type ViewPager2
        //initialize viewPager by using its id, the id is found in the ViewPager2 widget layout
        val viewPager = findViewById<ViewPager2>(R.id.view_pager)                                //basically getting the viewPager layout




        //using 'apply' function to apply some configuration changes (swiping mechanisms) to viewPage
        viewPager.apply {
            //allow each child to draw outside of its own bounds, within the parent. And don't allow children to draw outside of the parent itself.
            clipChildren = false // No clipping to the left and right items
            clipToPadding = false  // Show the viewpager in full width without clipping the padding

            //to improve performance, pages that are not visible don't need to be loaded.
            offscreenPageLimit = 3  // Render the left and right items

            //access the 1st child of ViewPager2, the RecyclerView
            (getChildAt(0) as RecyclerView).overScrollMode =
                    //once we have access to the RecyclerView, we set the overScrollMode property to never
                    //so now the user can't scroll beyond the edge of the content - vertically
                RecyclerView.OVER_SCROLL_NEVER // Remove the scroll effect, only swipe, no scroll
        }



        //set the carousel_adapter class as the adapter of the viewPager
        viewPager.adapter = carousel_adapter();
        //create an instance of the CompositePageTransformer class
        // This class Applies mechanics of swipe controls to the viewPager
        val compositePageTransformer = CompositePageTransformer()
        //The MarginPageTransformer class is used to add a margin between the pages in the viewPager. The margin size is calculated based on the device's display metrics density.
        compositePageTransformer.addTransformer(MarginPageTransformer((40 * Resources.getSystem().displayMetrics.density).toInt()))
        //set the compositePageTransformer as the page transformer for the viewPager.
        viewPager.setPageTransformer(compositePageTransformer)




        //Adding data in the data items that will be displayed
        data.add(listobj("Life is amazing",R.drawable.list1))
        data.add(listobj("Mushroom",R.drawable.list2));


        // declare a variable "recyclerView" of type RecyclerView
        //initialize recyclerView by using its id, the id is found in the RecyclerView widget layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)                //basically we're getting the recylerview layout
        //create an instance of the LinearLayoutManager class, which is used to set the layout of the recyclerView
        // -vertical layout
        val layoutmanager = LinearLayoutManager(this)
        //set the layoutmanager as the layout manager for the recyclerView.
        recyclerView.layoutManager = layoutmanager        // applying the layout manager which is by default vertical for recyclerview
        //create an instance of the recycleviewadapter class and set it to the adapter object.
        adapter = recycleviewadapter(data)            //initializing the object of recyclerviewadapter class
        //set the adapter as the adapter for the recyclerView.
        recyclerView.adapter = adapter                // applying adapter object to recycler view adapter
        //clear the focus of the searchview - to prevent the app from starting with the cursor on the search bar
        searchview.clearFocus()



        //setting up a listener to the searchview:

        // This method is called when the user submits his search query by pressing the submit button
        // in this case it will "false"
        // = listener is not handling the submit action.
        searchview.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener{                 // search view query getter
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            //This method is called whenever the text in the search bar changes
            // The method has a single argument "newText" that represents the new text in the SearchView.
            // it first calls the "filterList" function and passes the "newText" to it
            // then returns "true",
            // = listener is handling the text change event.

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText);                        //function call when user type something on search bar
                return true
            }

        })
    }

    //filter the list items based on the search bar input:
    private fun filterList(query:String?){
        //make sure the search query isn't empty
        if(query!=null)
        {
            //if so then create an array list with individual listobj s
            val filteredList=ArrayList<listobj>()                          // create a new filtered list
            //loop through the data in the list to find the data that contains the same characters as the search query
            for(i in data){
                //have them both in lower case so its case insensitive
                if(i.text.lowercase(Locale.ROOT).contains(query)){        // if query matches with the text in the data then we add in filtered list
                    //if there's a match, add it to the new filtered array list
                    filteredList.add(i);
                }
            }
            //show tost msg if empty, and set filtered array if it has items
            if(filteredList.isEmpty()){
                Toast.makeText(this,"No data Found",Toast.LENGTH_SHORT).show()
            }
            else
            {
                adapter.setFliteredList(filteredList);                   //notify this change in the recycler view adapter
            }
        }
    }
}