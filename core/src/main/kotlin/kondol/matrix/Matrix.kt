package kondol.matrix

import java.util.*

class Matrix(private vararg val rows: DoubleArray) {
    
    constructor(vararg elements: Double): this(elements)
    constructor(vararg intRows: IntArray): this(*toDoubleArray(intRows))
    constructor(vararg elements: Int): this(elements)

    val rowSize: Int
    val colSize: Int
    
    init {
        if (this.rows.isEmpty()) {
            throw IllegalArgumentException("rows must have any row.")
        }
        if (this.rows[0].isEmpty()) {
            throw IllegalArgumentException("row must have any elements.")
        }
        
        val firstRowSize = this.rows[0].size
        this.rows.forEachIndexed { i, row ->
            if (row.size != firstRowSize) {
                throw IllegalArgumentException("Expected length is $firstRowSize. However row[$i].length is ${row.size}.")
            }
        }
        
        this.rowSize = this.rows.size
        this.colSize = this.rows[0].size
    }
    
    fun sum() = this.rows.sumByDouble(DoubleArray::sum)
    
    fun exp() = this.map { Math.exp(it) }

    operator fun unaryMinus() = this.map { -it }

    operator fun get(rowIndex: Int) = Matrix(this.rows[rowIndex])
    operator fun get(rowIndex: Int, colIndex: Int) = this.rows[rowIndex][colIndex]
    
    operator fun plus(l: Long) = this.map { it + l }
    operator fun plus(l: Double) = this.map { it + l }
    operator fun minus(l: Long) = this.map { it - l }
    operator fun minus(l: Double) = this.map { it - l }
    operator fun times(l: Long) = this.map { it * l }
    operator fun times(l: Double) = this.map { it * l }
    operator fun div(l: Long) = this.map { it / l }
    operator fun div(l: Double) = this.map { it / l }
    
    operator fun plus(other: Matrix) = this.mapWith(other, { thisValue, otherValue -> thisValue + otherValue})
    operator fun minus(other: Matrix) = this.mapWith(other, { thisValue, otherValue -> thisValue - otherValue})
    operator fun times(other: Matrix): Matrix {
        if (this.colSize != other.rowSize) {
            throw IllegalArgumentException("Expected row size is ${this.colSize}. However actual is ${other.rowSize}.")
        }
        
        val rows = Array(this.rowSize, {DoubleArray(other.colSize)})
        
        for (rowIndex in 0 until this.rowSize) {
            for (colIndex in 0 until other.colSize) {
                rows[rowIndex][colIndex] = (0 until this.colSize).sumByDouble { 
                    this[rowIndex, it] * other[it, colIndex]
                }
            }
        }
        
        return Matrix(*rows)
    }
    
    fun map(mapper: (Double) -> Double)
            = this.mapIndexed { _, _, value ->
                mapper(value)
            }

    fun mapWith(other: Matrix, mapper: (Double, Double) -> Double)
            = this.mapIndexed { rowIndex, colIndex, thisValue -> 
                mapper(thisValue, other[rowIndex, colIndex])
            }
    
    private fun mapIndexed(mapper: (Int, Int, Double) -> Double): Matrix {
        val rows = Array(this.rowSize, { DoubleArray(this.colSize) })

        this.rows.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, value ->
                rows[rowIndex][colIndex] = mapper(rowIndex, colIndex, value)
            }
        }

        return Matrix(*rows)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Matrix) return false
        if (this.rowSize != other.rowSize) return false
        
        var i = 0
        return this.rows.all { row ->
            Arrays.equals(row, other.rows[i++])
        }
    }

    override fun hashCode() = Arrays.deepHashCode(this.rows)

    override fun toString()
        = this.rows.joinToString(separator = "\n", transform = { row ->
            row.joinToString(prefix = "[", separator = ", ", postfix = "]")
        })
}

private fun toDoubleArray(intRows: Array<out IntArray>): Array<DoubleArray> {
    val array = Array(intRows.size, {DoubleArray(intRows[0].size)})
    
    intRows.forEachIndexed { rowIndex, rows -> 
        rows.forEachIndexed { colIndex, value -> 
            array[rowIndex][colIndex] = value.toDouble()
        }
    }
    
    return array
}
