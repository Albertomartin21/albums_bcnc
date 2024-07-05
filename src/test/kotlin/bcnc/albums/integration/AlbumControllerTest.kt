package bcnc.albums.integration

import bcnc.albums.AlbumsApplication
import bcnc.albums.exception.JsonPlaceHolderException
import bcnc.albums.model.Album
import bcnc.albums.model.dto.AlbumDTO
import org.apache.commons.lang.ObjectUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import kotlin.test.assertNotNull

@AutoConfigureMockMvc
@SpringBootTest(
    classes = arrayOf(AlbumsApplication::class),
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlbumControllerTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate


    @Test
    fun getAllAlbumTest(){
        val result = restTemplate.getForEntity("/albums", Array<Album>::class.java)

        assertThat(result.statusCode.value()).isEqualTo(200)
        assertThat(result.body!!.size).isGreaterThan(0)
    }

    @Test
    fun getAlbumByIdTest(){
        val result = restTemplate.getForEntity("/albums/1", Album::class.java)

        assertThat(result.statusCode.value()).isEqualTo(200)
        assertNotNull(result.body!!)
    }

    @Test
    fun getAlbumByIdAndReturn404Test(){
        val result = restTemplate.getForEntity("/albums/131", String::class.java)

        assertThat(result.statusCode.value()).isEqualTo(404)
        assertNotNull(result.body!!)
        assertThat(result.body!!).isEqualTo("Not found the album with id: 131")
    }

    @Test
    fun getAllPhotosFromAlbumTest(){
        val result = restTemplate.getForEntity("/albums/1/photos", Any::class.java)

        assertThat(result.statusCode.value()).isEqualTo(200)
    }
    @Test
    fun getAllPhotosFromAlbumAndReturn404Test(){
        val result = restTemplate.getForEntity("/albums/321", String::class.java)

        assertThat(result.statusCode.value()).isEqualTo(404)
        assertThat(result.body!!).isEqualTo("Not found the album with id: 321")
    }

    @Test
    fun getAllAlbumsAndPhotosTest(){
        val result = restTemplate.getForEntity("/albums/photos", Any::class.java)

        assertThat(result.statusCode.value()).isEqualTo(200)
    }



}