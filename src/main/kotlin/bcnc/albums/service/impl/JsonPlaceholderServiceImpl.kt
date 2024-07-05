package bcnc.albums.service.impl

import bcnc.albums.client.JsonPlaceholderFeignClient
import bcnc.albums.model.Album
import bcnc.albums.model.Photo
import bcnc.albums.service.JsonPlaceholderService
import org.springframework.stereotype.Service


@Service
class JsonPlaceholderServiceImpl(private val jsonPlaceholderFeignClient: JsonPlaceholderFeignClient) :
    JsonPlaceholderService {


    override fun getAllAlbums(): List<Album> {
        return jsonPlaceholderFeignClient.getAllAlbumsFromJsonPlaceholder();
    }

    override fun getAllPhotos(): List<Photo> {
        return jsonPlaceholderFeignClient.getAllPhotosFromJsonPlaceholder();
    }
}