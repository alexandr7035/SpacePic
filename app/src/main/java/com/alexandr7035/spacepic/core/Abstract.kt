package com.alexandr7035.spacepic.core

abstract class Abstract {
    abstract class Object<T, M: Mapper> {
        abstract fun map(mapper: M): T
    }

    interface Mapper
}