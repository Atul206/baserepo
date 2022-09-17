package com.roadster.roam.basesetup.base

abstract class DataMapper<Entity,DomainModel> {

    abstract fun mapToDomainModel(serverResponseObj:Entity):DomainModel

    open fun mapToDomainModel(cacheDataList: List<Entity>): List<DomainModel> {
        return cacheDataList.map {
            mapToDomainModel(it)
        }

    }
}