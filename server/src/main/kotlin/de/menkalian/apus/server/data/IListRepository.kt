package de.menkalian.apus.server.data

import org.springframework.data.repository.CrudRepository

interface IListRepository : CrudRepository<ListEntity, Int> {
    fun findByHandle(handle: String): ListEntity
}