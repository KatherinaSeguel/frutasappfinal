package com.example.frutos.model.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.frutos.R
import com.example.frutos.model.local.local.entities.DetalleFrutos
import kotlinx.android.synthetic.main.item_list_view.view.*


class FrutosAdapter (var mPasstheData:PasstheData):RecyclerView.Adapter<FrutosAdapter.FrutosViewHolder>() { //paso 4 ,Implementar Recycler View

    private  var mData= emptyList<DetalleFrutos>()  //paso 1

    //cada vez que haya un cambio actualiza la lista

    fun updateFrutos(mStringList: List<DetalleFrutos>) {  //paso 2

        mData = mStringList        //paso 2
       notifyDataSetChanged()
    }

    inner class FrutosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{  //paso 3
        // val itemView = itemView.setOnClickListener(this) cuando es imagen

        val mitemView = itemView.idNombreFruta  //texview que está en ItemLIst
        val itemView = itemView.setOnClickListener(this)  // Cuando se hace click en el texto
        val imgfav = itemView.imgFavorito

        override fun onClick(p0: View?) {    //paso 5
            mPasstheData.passTheData(mData[adapterPosition])
        }



    }


    //esto se genera después del paso 4, son los métodos que se generan
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrutosViewHolder {//paso 4.1
        //creas el viewholder, trabaja en el xml,se crea el XML
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.item_list_view,parent,false)

      return FrutosViewHolder(itemView)

    }


    override fun onBindViewHolder(holder: FrutosViewHolder, position: Int) {//paso 4.2
        //llena el xml con los objetos del listado, une los datos con los elementos

        val mfrut= mData[position]
        holder.mitemView.text= mfrut.tfvname

        holder.imgfav.setTag("No Select")
            holder.imgfav.setOnClickListener(object : View.OnClickListener{
                override fun onClick(p0: View?) {
                   if (holder.imgfav.getTag()=="No Select" ){
                       holder.imgfav.setColorFilter(Color.RED)
                       holder.imgfav.setTag("Select")
                   }else{
                       holder.imgfav.setColorFilter(Color.BLACK)
                       holder.imgfav.setTag("No Select")
                   }
                }

            })

    }

    override fun getItemCount(): Int {   // paso 4.3
        //este tienen el total de elementos
        return mData.size
    }


    //esta interface se declara y se ocupa en el primer fragmanto .
    interface PasstheData{

        fun passTheData(mFrut:DetalleFrutos)
}
}