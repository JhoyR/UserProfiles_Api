package api.kotlin.project.users.mapper

interface Mapper<U, V> {

    //Recebe um objeto do tipo T, e retorna um objeto do tipo U
    fun map(t: U): V
}
