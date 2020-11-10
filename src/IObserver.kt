interface IObserver {
    val homePlace: HomePlace
    val smartThingType: SmartThingType
    val eventManager: EventManager

    fun update(newState: State)
}

interface IObservable {
    val observerIterator: IObserverListCollection

    fun add(observer: IObserver) {
        observerIterator.add(observer)
    }

    fun remove(observer: IObserver) {
        observerIterator.remove(observer)
    }

    fun sendUpdateEvent(
        thingType: SmartThingType,
        place: HomePlace,
        state: State
    )
}