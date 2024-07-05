package bcnc.albums.model.dto

import bcnc.albums.model.Album
import bcnc.albums.model.Photo

class AlbumDTO(private val album: Album,
               private val listPhoto: List<Photo>) {

    fun getAlbum(): Album {
        return album
    }

    fun getList(): List<Photo> {
        return listPhoto
    }
}