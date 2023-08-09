package turniplabs.industry.Interfaces

interface IMachineCondition {
    val getMachineHealth: Int

    fun setMachineHealth(newHealth: Int)
}