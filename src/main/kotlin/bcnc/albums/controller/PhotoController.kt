package bcnc.albums.controller

import bcnc.albums.model.Photo
import bcnc.albums.service.PhotosService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PhotoController(private val photosService: PhotosService) {

    @GetMapping("/photos")
    fun getAllPosts(): List<Photo> {
        return photosService.getPhotos()
    }
}