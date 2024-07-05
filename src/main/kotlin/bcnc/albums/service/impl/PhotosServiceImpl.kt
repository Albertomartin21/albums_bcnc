package bcnc.albums.service.impl

import bcnc.albums.model.Photo
import bcnc.albums.service.AlbumService
import bcnc.albums.service.JsonPlaceholderService
import bcnc.albums.service.PhotosService

class PhotosServiceImpl(private val jsonPlaceholderService: JsonPlaceholderService) : PhotosService {
    override fun getPhotos(): List<Photo> {
        return jsonPlaceholderService.getAllPhotos()
    }
}