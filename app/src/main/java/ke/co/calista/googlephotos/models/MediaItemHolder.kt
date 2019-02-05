package ke.co.calista.googlephotos.models

import com.google.photos.library.v1.proto.Filters
import com.kogicodes.sokoni.models.custom.MediaType
import java.io.Serializable


class MediaItemHolder : Serializable{

     var type : MediaType?=null
     var filter : Filters?=null
     var albumId : String?=null
     var token : String?=null


    constructor(type: MediaType?, filter: Filters?, token: String?) {
        this.type = type
        this.filter = filter
        this.token = token
    }

    constructor(type: MediaType?, albumId: String?, token: String?) {
        this.type = type
        this.albumId = albumId
        this.token = token
    }


}