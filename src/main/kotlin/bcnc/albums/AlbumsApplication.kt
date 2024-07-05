package bcnc.albums

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class AlbumsApplication

fun main(args: Array<String>) {
	runApplication<AlbumsApplication>(*args)
}
