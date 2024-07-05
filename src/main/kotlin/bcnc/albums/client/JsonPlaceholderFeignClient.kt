package bcnc.albums.client

import bcnc.albums.model.Album
import bcnc.albums.model.Photo
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.cloud.openfeign.FeignClientsConfiguration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "json-placeholder", url = "\${app.client.jsonPlaceHolderRoute}", configuration = [FeignClientsConfiguration::class])
interface JsonPlaceholderFeignClient {

    @GetMapping("/albums", produces = ["application/json"])
    fun getAllAlbumsFromJsonPlaceholder(): List<Album>

    @GetMapping("/photos", produces = ["application/json"])
    fun getAllPhotosFromJsonPlaceholder(): List<Photo>

    @GetMapping("/albums/{id}", produces = ["application/json"])
    fun getAlbumByIdFromJsonPlaceholder(@PathVariable id:Int): Album

    @GetMapping("/photos/{id}", produces = ["application/json"])
    fun getPhotoByIdFromJsonPlaceholder(@PathVariable id:Int): Photo

    @GetMapping("/albums/{id}/photos", produces = ["application/json"])
    fun  getAllPhotosFromAlbumFromJsonPlaceholder(@PathVariable id:Int): List<Photo>

}