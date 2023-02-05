package com.example.project

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.InputStream
import java.net.URL

//recycleviewadapter class extends the RecyclerView.Adapter class to create a custom implementation of the RecyclerView adapter
// It takes an ArrayList of "listobj" objects as an argument and stores it in a variable "data".
//the parameter of the class is a view holder that manages items within the adapter
class recycleviewadapter(private var data: ArrayList<listobj>) : RecyclerView.Adapter<recycleviewadapter.ViewHolder>() {


    //The class ViewHolder extends the class RecyclerView.ViewHolder
    //ViewHolder has one argument, itemView of type View, that we then pass to the RecyclerView.ViewHolder as parameter
    //basically, "ViewHolder" is a custom implementation of the ViewHolder class
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {       // View Holder Class for getting text and image in the item
        //the ViewHolder class holds the text and image views of the items in the RecyclerView
        //which are accessed by referencing the id specified to them in the itemView xml
        val text: TextView = itemView.findViewById(R.id.textView);
        val image:ImageView=itemView.findViewById(R.id.imageView);


        //val modelTextView: TextView = itemView.findViewById(R.id.car_model)
        //val yearTextView: TextView = itemView.findViewById(R.id.car_year)
    }





    //current list data is passed as an argument
    fun setFliteredList(data:ArrayList<listobj>){                //setting the filtered list to this class data so that the changed data will be displayed
        //set the current data to the new filtered data
        this.data=data;
        //notify the RecyclerView that the data has changed so it would generate a list with the updated data
        //this method will trigger the onBindViewHolder to bind the new data
        notifyDataSetChanged()
    }


    //inflating the item layout and returning a new ViewHolder:

    //this method gets called by the RecyclerView everytime a new ViewHolder has to be created for a new item in the list
    //parameters of the function: the parent view of the item, and viewtype, the type of the item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {          // this function inflates the layout which will be displayed repetitively in the recycler view
        //1- inflate the item layout using the LayoutInflater class
        //-- the item xml contains a view for each item -a text / an image-
        //2- store the inflated layout in a variable called view
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        //3- pass view as an argument to the ViewHolder class
        //4- return the new instance of the ViewHolder class
        return ViewHolder(view)
    }

    // Updating the ImageView so the RecyclerView can display the data set -images-:

    //this method gets called by the RecyclerView everytime we need to display a new item in the list
    //parameters of the function: holder: an instance of the custom "ViewHolder" class we created earlier
    //and position: an integer that represents the position of the new item within the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {      // entering the data from the data list to the recycler view
        //obj is a variable that holds a listobj from the array list in a given position
        val obj = data[position]
        //set the text of the TextView in the item to the text property of the listobj
        holder.text.text = obj.text;
        //set the image of the ImageView in the item to display an image from a specific position within the ListData
        holder.image.setImageResource(obj.image)
    }

    //This method returns the number of items in the list
    //This helps the RecyclerView to:
    // 1-determine the number of items to display
    // 2- determine the number of times the "onBindViewHolder" method should be called to update the views for each item in the list.
    override fun getItemCount(): Int {
        return data.size;
    }
}