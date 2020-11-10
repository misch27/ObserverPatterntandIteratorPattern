class IObserverListCollection {
    private val observers = ArrayList<IObserver>()

    fun add(observer: IObserver) {
        observers.add(observer)
    }

    fun remove(observer: IObserver) {
        observers.remove(observer)
    }


    fun createIterator() = ListIterator(this)

    class ListIterator(private val collection: IObserverListCollection) : Iterator {

        private var index = 0

        override fun next(): IObserver {
            return collection.observers[index++]
        }

        override fun hasNext(): Boolean {
            return index < collection.observers.size
        }
    }
}