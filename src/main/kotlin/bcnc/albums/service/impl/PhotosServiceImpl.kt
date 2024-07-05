package bcnc.albums.service.impl

import bcnc.albums.model.Photo
import bcnc.albums.service.AlbumService
import bcnc.albums.service.JsonPlaceholderService
import bcnc.albums.service.PhotosService
import org.springframework.stereotype.Service

@Service
class PhotosServiceImpl(private val jsonPlaceholderService: JsonPlaceholderService) : PhotosService {
    override fun getPhotos(): List<Photo> {
        return jsonPlaceholderService.getAllPhotos()
    }

    override fun getPhotoById(id: Int): Photo {
        return jsonPlaceholderService.getPhotoById(id)
    }
}