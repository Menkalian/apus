package de.menkalian.apus.server.data

import org.springframework.data.repository.CrudRepository

interface IActionTypeRepository : CrudRepository<ActionTypeEntity, Int> {
    fun findActionTypeEntityByValue(value: String): ActionTypeEntity
}