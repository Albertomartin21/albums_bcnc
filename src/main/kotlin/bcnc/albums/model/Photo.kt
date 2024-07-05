package bcnc.albums.model

class Photo(private val albumId: Int,
            private val id: Int,
            private val title: String,
            private val url: String,
            private var thumbnailUrl: String){


    fun getAlbumId(): Int {
        return albumId
    }

    fun getId(): Int {
        return id
    }

    fun getTitle(): String {
        return title
    }

    fun getUrl(): String {
        return url
    }

    fun getThumbnailUrl(): String {
        return thumbnailUrl
    }
}