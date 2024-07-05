package bcnc.albums.integration

import bcnc.albums.AlbumsApplication
import bcnc.albums.model.Album
import bcnc.albums.model.Photo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import kotlin.test.assertNotNull

@AutoConfigureMockMvc
@SpringBootTest(
    classes = arrayOf(AlbumsApplication::class),
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PhotoControllerTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate


    @Test
    fun getAllPhotosTest(){
        val result = restTemplate.getForEntity("/photos", Array<Photo>::class.java)

        assertThat(result.statusCode.value()).isEqualTo(200)
        assertThat(result.body!!.size).isGreaterThan(0)
    }

    @Test
    fun getPhotoByIdTest(){
        val result = restTemplate.getForEntity("/photos/1", Photo::class.java)

        assertThat(result.statusCode.value()).isEqualTo(200)
        assertNotNull(result.body!!)
    }

    @Test
    fun getPhotoByIdAndReturn404Test(){
        val result = restTemplate.getForEntity("/photos/5013", String::class.java)

        assertThat(result.statusCode.value()).isEqualTo(404)
        assertNotNull(result.body!!)
        assertThat(result.body!!).isEqualTo("Not found the photo with id: 5013")
    }
}