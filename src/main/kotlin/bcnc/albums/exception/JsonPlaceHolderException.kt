package bcnc.albums.exception

import feign.FeignException

class JsonPlaceHolderException(status: Int, message: String) : FeignException(status, message) {

}