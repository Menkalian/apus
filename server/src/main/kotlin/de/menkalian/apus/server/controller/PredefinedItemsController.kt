package de.menkalian.apus.server.controller

import de.menkalian.apus.server.data.IChangeRepository
import de.menkalian.apus.server.data.IPredefinedRepository
import de.menkalian.apus.server.data.PredefinedEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.sql.Timestamp
import java.time.Instant
import javax.websocket.server.PathParam

@RestController
class PredefinedItemsController(val predefinedRepo: IPredefinedRepository, val changesRepo: IChangeRepository) {

    @GetMapping("/predefined/{id}")
    fun getPredefined(@PathParam("id") id: Int) = predefinedRepo.findById(id).get()

    @GetMapping("/predefined/all")
    fun getAllPredefined(): List<PredefinedEntity> =
        predefinedRepo.findAll().toList()

    @GetMapping("/predefined/changes")
    fun getChangesSince(@RequestParam("since") since: Long) {
        val ts = Timestamp.from(Instant.ofEpochSecond(since))
        return changesRepo.findAllByTimestampAfter(ts)
    }
}