package de.menkalian.apus.server.controller

import de.menkalian.apus.server.api.ListCreationData
import de.menkalian.apus.server.data.IListRepository
import de.menkalian.apus.server.data.IPredefinedRepository
import de.menkalian.apus.server.data.ListEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException
import java.util.UUID
import javax.websocket.server.PathParam

@RestController
class ListController(val listRepo: IListRepository, predefinedRepo: IPredefinedRepository) {
    @PostMapping("/list")
    fun createList(@RequestBody desc: ListCreationData): ListEntity {
        val list = ListEntity()
        list.name = desc.name
        list.handle = UUID.randomUUID().toString()

        return listRepo.save(list)
    }

    @GetMapping("/list/{handle}")
    fun readList(@PathParam("handle") handle: String) = listRepo.findByHandle(handle)

    @PostMapping("/list/{handle}")
    fun updateList(@PathParam("handle") handle: String, @RequestBody toUpdate: ListEntity): ListEntity {
        val dbList = listRepo.findByHandle(handle)

        if (dbList.id != toUpdate.id)
            throw HttpClientErrorException(HttpStatus.BAD_REQUEST, "ID invalid")
        if (toUpdate.handle != handle)
            throw HttpClientErrorException(HttpStatus.BAD_REQUEST, "Handles do not match")

        return listRepo.save(toUpdate)
    }
}