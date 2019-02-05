package ke.co.calista.googlephotos.adapters

import android.content.Context
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dev.lishabora.Utils.OnclickRecyclerListener

import java.lang.ref.WeakReference
import java.util.LinkedList

import androidx.recyclerview.widget.RecyclerView
import ke.co.calista.googlephotos.R
import ke.co.calista.googlephotos.models.MediaItemObj


class GridAdapter(private val context: Context, internal var mediaItems: LinkedList<MediaItemObj>, private val recyclerListener: OnclickRecyclerListener) : RecyclerView.Adapter<GridAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridAdapter.ImageViewHolder {
        var itemView: View? = null

        itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(itemView, recyclerListener)
    }

    fun setData(images: LinkedList<MediaItemObj>?) {
        if (images != null) {
            Log.d("sfsf",""+images.size)
        }else{
            Log.d("sfsf","nuu")

        }
        this.mediaItems.clear()
        if (images != null) {
            this.mediaItems.addAll(images)
        }
        notifyDataSetChanged()
    }

    fun setDataChange(position: Int, image: MediaItemObj) {
        mediaItems[position].selected = image.selected
        notifyItemChanged(position)


    }

    override fun onBindViewHolder(holder: GridAdapter.ImageViewHolder, position: Int) {


        val photo = mediaItems[position]

        val options = RequestOptions()
                .placeholder(R.drawable.imagepicker_image_placeholder)
                .error(R.drawable.imagepicker_image_placeholder)
                .centerCrop().diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        Glide.with(context)
                .load(photo.url)
                .apply(options)
                .into(holder.thumbnail)


        if (photo.selected!!) {
            holder.thumbnail.alpha = 0.4f
            holder.imgSelect.visibility = View.VISIBLE
        } else {
            holder.thumbnail.alpha = 0.9f
            holder.imgSelect.visibility = View.GONE
        }


    }

    override fun getItemCount(): Int {
        return mediaItems.size
    }

    inner class ImageViewHolder(itemView: View, listener: OnclickRecyclerListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal var thumbnail: ImageView = itemView.findViewById(R.id.image_thumbnail)
        internal var imgSelect: ImageView = itemView.findViewById(R.id.image_select)
        private val listenerWeakReference: WeakReference<OnclickRecyclerListener> = WeakReference(listener)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {

            listenerWeakReference.get()?.onClickListener(adapterPosition)

        }
    }
}
