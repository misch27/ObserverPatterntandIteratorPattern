class IObserverArrayCollection {
    private val observers = arrayOfNulls<IObserver>(10)
    private var count = 0

    fun add(product: IObserver) {
        if (count < observers.size)
            observers[count++] = product
    }

    fun createIterator() = ArrayIterator(this)

    class ArrayIterator(private val collection: IObserverArrayCollection) : Iterator {

        private var index = 0

        override fun next(): IObserver {
            return collection.observers[index++]!!
        }

        override fun hasNext(): Boolean {
            return index < collection.count
        }

    }
}