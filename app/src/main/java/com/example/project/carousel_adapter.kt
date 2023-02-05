package com.example.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

//the carousel_adapter class extends the RecyclerView.Adapter class that is usually used to display data in a list format
//the parameter of the class is a view holder that manages items within the adapter, cache the views for the items of the list
class carousel_adapter():RecyclerView.Adapter<carousel_adapter.carousel_ViewHolder>() {
    val ListData= intArrayOf(                    // carousel list of images
        R.drawable.carousel1,
        R.drawable.carousel2,
        R.drawable.carousel3,
    )

    //The class carousel_ViewHolder extends the class RecyclerView.ViewHolder
    //carousel_ViewHolder has one argument, view of type View, that we then pass to the RecyclerView.ViewHolder as parameter
    //basically, "carousel_ViewHolder" is a custom implementation of the ViewHolder class
    class carousel_ViewHolder(view: View) : RecyclerView.ViewHolder(view)




    //inflating the carousel_item layout and returning a new ViewHolder:

    //this method gets called by the RecyclerView everytime a new ViewHolder has to be created for a new item in the carousel
    //parameters of the function: the parent view of the item, and viewtype, the type of the item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): carousel_ViewHolder {                   //basically inflating the carousel_item layout, which contains an image
        //1- inflate the carousel_item layout using the LayoutInflater class
        //-- the carousel_item xml contains a view for each item -an image-
        //2- store the inflated layout in a variable called viewHolder
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.carousel_item, parent, false)
        //3- pass viewHolder as an argument to the carousel_ViewHolder class
        //4- return the new instance of the carousel_ViewHolder class
        return carousel_ViewHolder(viewHolder)
    }





    // Updating the ImageView so the RecyclerView can display the data set -images-:

    //this method gets called by the RecyclerView everytime we need to display a new item in the carousel
    //parameters of the function: holder: an instance of the custom "carousel_ViewHolder" class we created earlier
    //and position: an integer that represents the position of the new item within the carousel
    override fun onBindViewHolder(holder: carousel_ViewHolder, position: Int) {               // entering the data from list of image to setting it to the imageview
        //1- find the ImageView in the now inflated carousel_item by referencing its id
        val ImageView = holder.itemView.findViewById<ImageView>(R.id.image)
        //2- set the ImageView to display an image from a specific position within the ListData
        ImageView.setImageResource(ListData[position]);
    }


    //This method returns the number of items in the carousel list
    //This helps the RecyclerView to:
    // 1-determine the number of items to display
    // 2- determine the number of times the "onBindViewHolder" method should be called to update the views for each item in the list.
    override fun getItemCount(): Int {
        return ListData.size;
    }
}