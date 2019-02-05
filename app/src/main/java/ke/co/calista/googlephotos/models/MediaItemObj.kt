package ke.co.calista.googlephotos.models

class MediaItemObj {
    var url: String?=null
    var selected: Boolean?=false

    constructor(url: String?, selected: Boolean?) {
        this.url = url
        this.selected = selected
    }
}