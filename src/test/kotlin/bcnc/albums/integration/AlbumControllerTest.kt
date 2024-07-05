package bcnc.albums.integration

import bcnc.albums.AlbumsApplication
import bcnc.albums.model.Album
import org.apache.commons.lang.ObjectUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

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


}