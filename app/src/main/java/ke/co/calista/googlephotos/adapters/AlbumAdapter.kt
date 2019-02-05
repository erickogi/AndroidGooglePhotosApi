package ke.co.calista.googlephotos.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dev.lishabora.Utils.OnclickRecyclerListener
import com.google.photos.library.v1.proto.Album
import ke.co.calista.googlephotos.R
import java.lang.ref.WeakReference
import java.util.*

class AlbumAdapter(private val context: Context, internal var albums: LinkedList<Album>, private val recyclerListener: OnclickRecyclerListener) : RecyclerView.Adapter<AlbumAdapter.MyImageViewHolder>() {


    private val options: RequestOptions

    init {
        this.options = RequestOptions()
                .placeholder(R.drawable.imagepicker_image_placeholder)
                .error(R.drawable.imagepicker_image_placeholder)
                .centerCrop().diskCacheStrategy(DiskCacheStrategy.RESOURCE)


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumAdapter.MyImageViewHolder {
        var itemView: View? = null

        itemView = LayoutInflater.from(parent.context).inflate(R.layout.album, parent, false)
        return MyImageViewHolder(itemView, recyclerListener)

    }

    override fun onBindViewHolder(holder: AlbumAdapter.MyImageViewHolder, position: Int) {
        val image = albums[position]

        try {

            Glide.with(context)
                    .load(image.coverPhotoBaseUrl + "=w2048-h1024")
                    .apply(options)
                    .into(holder.imageView)



            if (android.text.TextUtils.isDigitsOnly(image.title)) {

                holder.title.text = image.title

            } else {
                holder.title.text = image.title
            }

        } catch (nm: Exception) {
            nm.printStackTrace()
        }

        holder.location.text = ""

        try {
            holder.no.setText(image.mediaItemsCount.toInt())
        } catch (nm: Exception) {
            holder.no.text = ""

            nm.printStackTrace()
        }


    }

    override fun getItemCount(): Int {
        return albums.size
    }

    fun setData(albums: LinkedList<Album>?) {
        this.albums.clear()
        if (albums != null) {
            this.albums.addAll(albums)
        }
        notifyDataSetChanged()
    }


    inner class MyImageViewHolder(itemView: View, listener: OnclickRecyclerListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        internal var imageView: ImageView = itemView.findViewById(R.id.img)
        internal var title: TextView = itemView.findViewById(R.id.atitle)

        internal var location: TextView = itemView.findViewById(R.id.alocation)
        internal var no: TextView = itemView.findViewById(R.id.ano_p)

        private val listenerWeakReference: WeakReference<OnclickRecyclerListener> = WeakReference(listener)


        init {


            itemView.setOnClickListener(this)

        }


        override fun onClick(view: View) {
            listenerWeakReference.get()?.onClickListener(adapterPosition)
        }
    }
}