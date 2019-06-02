package com.smore.rpncalculator.model

import com.smore.rpncalculator.RpnCalculatorCommandTool
import com.smore.rpncalculator.exception.PopFromEmptyStackException
import java.util.*
import kotlin.collections.ArrayList

class NumberStack {

    private var stack = LinkedList<NumberNode>()
    private val numberStackHistory = NumberStackHistory()

    init {
        numberStackHistory.storeStack(stack)
    }

    fun getSize(): Int {
        return stack.size
    }

    @Throws(PopFromEmptyStackException::class)
    fun popNumber(): NumberNode {
        if (stack.isEmpty()) {
            throw PopFromEmptyStackException()
        }
        return stack.pop()
    }

    fun pushNumber(numberNode: NumberNode) {
        if (getSize() == LIMIT_OF_STACK_SIZE) {
            stack.removeFirst()
        }
        stack.push(numberNode)
        numberStackHistory.storeStack(stack)
    }

    fun pushNumber(number: String) {
        val numberNode = NumberNode(number)
        numberNode.position = getSize()
        pushNumber(numberNode)
    }

    fun hasEnoughNumber(sizeNeeded: Int): Boolean {
        return stack.size >= sizeNeeded
    }

    fun clear() {
        stack.clear()
        numberStackHistory.storeStack(stack)
    }

    fun getNumbers(): List<NumberNode> {
        return LinkedList(stack)
    }

    fun loadPrevious() {
        numberStackHistory.revocerPrevious()
        stack = numberStackHistory.getCurrentStack()
    }

    fun loadCurrent() {
        stack = numberStackHistory.getCurrentStack()
    }

    fun loadFuture() {
        numberStackHistory.recoverFuture()
        stack = numberStackHistory.getCurrentStack()
    }

    class NumberStackHistory {

        private val numberStackList = ArrayList<List<NumberNode>>()
        private var currentVersionPosition: Int

        init {
            currentVersionPosition = numberStackList.size - 1
        }

        fun storeStack(numberStack: List<NumberNode>) {
            cleanVersionsAfterCurrentPosition()
            cleanVersionsOutOfLimit()
            numberStackList.add(LinkedList(numberStack))
            currentVersionPosition = numberStackList.size - 1
        }

        fun revocerPrevious() {
            if (currentVersionPosition > 0) {
                currentVersionPosition--
            }
        }

        fun recoverFuture() {
            if (currentVersionPosition < numberStackList.size - 1) {
                currentVersionPosition++
            }
        }

        fun getCurrentStack(): LinkedList<NumberNode> {
            return LinkedList(numberStackList[currentVersionPosition])
        }

        private fun cleanVersionsAfterCurrentPosition() {
            while (currentVersionPosition + 1 < numberStackList.size) {
                numberStackList.removeAt(currentVersionPosition + 1)
            }
        }

        private fun cleanVersionsOutOfLimit() {
            if (numberStackList.size == LIMIT_OF_VERSIONS) {
                numberStackList.removeAt(0)
            }
        }

        companion object {
            private val LIMIT_OF_VERSIONS =
                RpnCalculatorCommandTool.getProperty(propertyName = "limitOfHistoryVersion", default = "20").toInt()
        }
    }

    companion object {
        private val LIMIT_OF_STACK_SIZE =
            RpnCalculatorCommandTool.getProperty(propertyName = "limitOfStackSize", default = "20").toInt()
    }
}
