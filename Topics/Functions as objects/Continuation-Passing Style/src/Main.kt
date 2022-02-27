fun square(value: Int, context: Any, continuation: (Int, Any) -> Unit) {
    // TODO: provide implementation here
    continuation(value.times(value), context)
}