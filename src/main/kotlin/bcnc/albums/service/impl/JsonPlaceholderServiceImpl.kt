package bcnc.albums.service.impl

import bcnc.albums.client.JsonPlaceholderFeignClient
import bcnc.albums.exception.JsonPlaceHolderException
import bcnc.albums.model.Album
import bcnc.albums.model.Photo
import bcnc.albums.service.JsonPlaceholderService
import feign.FeignException
import org.springframework.boot.json.JsonParseException
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

    override fun getAlbumById(id:Int): Album {
        try {
            return jsonPlaceholderFeignClient.getAlbumByIdFromJsonPlaceholder(id)
        }catch (feignException: FeignException){
            throw JsonPlaceHolderException(feignException.status(),"Not found the album with id: $id")
        }
    }

    override fun getPhotoById(id: Int): Photo {
        try {
            return jsonPlaceholderFeignClient.getPhotoByIdFromJsonPlaceholder(id)
        }catch (feignException: FeignException){
            throw JsonPlaceHolderException(feignException.status(),"Not found the photo with id: $id")
        }
    }

    override fun getAllPhotosByAlbumId(id: Int): List<Photo> {
        try {
            return jsonPlaceholderFeignClient.getAllPhotosFromAlbumFromJsonPlaceholder(id)
        }catch (feignException: FeignException){
            throw JsonPlaceHolderException(feignException.status(),"Not found the album with id: $id")
        }
    }
}