class MotionSensorXiaomi(
    override val homePlace: HomePlace,
    override val smartThingType: SmartThingType,
    override val eventManager: EventManager
) : IObserver {
    private var state = State.OFF

    override fun update(newState: State) {
        this.state = newState
    }

    fun setSmartThingState(state: State) {
        this.state = state
        println("$smartThingType in $homePlace changed state to $state")
        eventManager.changeThingState(this)
    }
}

class LampPhilipsHue(
    override val homePlace: HomePlace,
    override val smartThingType: SmartThingType,
    override val eventManager: EventManager
) : IObserver {
    private var state = State.OFF

    override fun update(newState: State) {
        this.state = newState
        println("$smartThingType in $homePlace changed state to $state")
    }
}