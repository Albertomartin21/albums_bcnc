package bcnc.albums.model

import lombok.Getter

@Getter
class Album(private val userId: Int,
            private val id: Int,
            private val title: String) {

    fun getUserId() = this.userId
    fun getId() = this.id
    fun getTitle() = this.title
}