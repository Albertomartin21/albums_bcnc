package bcnc.albums.client

import bcnc.albums.model.Album
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.cloud.openfeign.FeignClientsConfiguration
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "json-placeholder", url = "\${app.client.jsonPlaceHolderRoute}", configuration = [FeignClientsConfiguration::class])
interface JsonPlaceholderFeignClient {

    @GetMapping("/albums", produces = ["application/json"])
    fun getAllAlbumsFromJsonPlaceholder(): List<Album>
}