package de.menkalian.apus.server.data

import org.springframework.data.repository.CrudRepository
import java.sql.Timestamp

interface IChangeRepository : CrudRepository<ChangeEntity, Int> {
    fun findAllByTimestampAfter(timestamp: Timestamp)
}