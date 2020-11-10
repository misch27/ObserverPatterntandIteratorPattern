class EventManager(
    override val observerIterator: IObserverListCollection = IObserverListCollection(),
    val rulesMap: MutableMap<SmartThing, SmartThing>
) : IObservable {
//    private fun findRuleForSmartThing(smartThing: SmartThing)


    override fun sendUpdateEvent(thingType: SmartThingType, place: HomePlace, state: State) {
        val iterator = observerIterator.createIterator()

        while (iterator.hasNext()) {
            val currentObserver = iterator.next()
            if (currentObserver.homePlace == place && currentObserver.smartThingType == thingType) {
                currentObserver.update(state)
            }
        }
    }

    fun changeThingState(observerObject: IObserver) {
        //find action to do
        val objectType = SmartThingType.LIGHT
        val objectPlace = HomePlace.SLEEPING_ROOM
        val newState = State.ON
        sendUpdateEvent(
            thingType = objectType,
            place = objectPlace,
            state = newState
        )
    }
}

fun main(args: Array<String>) {
    val eventManager = EventManager(
        rulesMap = mutableMapOf(
            SmartThing(
                type = SmartThingType.MOTION_SENSOR,
                place = HomePlace.SLEEPING_ROOM,
                newState = State.ON
            ) to SmartThing(
                type = SmartThingType.LIGHT,
                place = HomePlace.SLEEPING_ROOM,
                newState = State.ON
            )
        )
    )


    val motionSensorXiaomi1 = MotionSensorXiaomi(
        homePlace = HomePlace.SLEEPING_ROOM,
        smartThingType = SmartThingType.MOTION_SENSOR,
        eventManager = eventManager
    )
    val lampPhilipsHue1 = LampPhilipsHue(
        homePlace = HomePlace.SLEEPING_ROOM,
        smartThingType = SmartThingType.LIGHT,
        eventManager = eventManager
    )

    eventManager.add(motionSensorXiaomi1)
    eventManager.add(lampPhilipsHue1)

    motionSensorXiaomi1.setSmartThingState(State.ON)
}