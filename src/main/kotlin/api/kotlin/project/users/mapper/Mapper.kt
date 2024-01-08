package api.kotlin.project.users.mapper

interface Mapper<U, V> {
    fun map(t: U): V
}
