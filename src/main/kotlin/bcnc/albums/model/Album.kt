package bcnc.albums.model

import lombok.Getter

@Getter
class Album(private val userId: Int,
            private val id: Int,
            private val title: String) {

    fun getUserId():Int{
        return userId
    }
    fun getId() :Int{
        return this.id
    }
    fun getTitle():String{
        return this.title
    }
}