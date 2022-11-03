package com.neev.owner.learning

sealed class Either<out L, out R> {

    data class Left<out L>(val error:L) : Either<L, Nothing>()

    data class Right<out R>(val response:R) : Either<Nothing, R>()


    fun either(fnL:(L) -> Unit, fnR:(R) -> Unit): Any =
        when(this){
            is Left -> fnL(error)
            is Right -> fnR(response)
        }

}

infix fun <L, R, R2> Either<L, R>.map(f: (R) -> R2): Either<L, R2> = when (this) {
    is Either.Left -> this
    is Either.Right -> Either.Right(f(this.response))
}


infix fun <L, R, R2> Either<L, R>.flatMap(f: (R) -> Either<L, R2>): Either<L, R2> = when (this) {
    is Either.Left -> this
    is Either.Right -> f(response)

}