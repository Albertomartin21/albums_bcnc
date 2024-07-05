package bcnc.albums.service

import bcnc.albums.model.Album
import bcnc.albums.model.Photo

interface PhotosService {
    fun getPhotos(): List<Photo>
    fun getPhotoById(id:Int): Photo
}