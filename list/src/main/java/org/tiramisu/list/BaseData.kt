package org.tiramisu.list

/**
 * @author felixxfwang
 */
open class BaseData {
}

open class BaseSelectableData: BaseData(), Selectable {
    override var selected: Boolean = false
}