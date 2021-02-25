package org.tiramisu.list

/**
 * @author felixxfwang
 */
abstract class BaseData {
    abstract var type: Int
}

abstract class BaseSelectableData: BaseData(), Selectable {
    override var selected: Boolean = false
}