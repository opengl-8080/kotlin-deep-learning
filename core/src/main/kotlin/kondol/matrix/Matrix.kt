package kondol.matrix

import java.util.*

class Matrix internal constructor (copyArray: Boolean = true, vararg originalRows: DoubleArray) {
    companion object {
        private val COPY = true
        private val NO_COPY = false
    }
    
    constructor(vararg rows: DoubleArray): this(COPY, *rows)
    constructor(vararg elements: Double): this(elements)
    constructor(vararg intRows: IntArray): this(NO_COPY, *toDoubleArray(intRows))
    constructor(vararg elements: Int): this(elements)

    private val rows: Array<out DoubleArray>
    val rowSize: Int
    val colSize: Int
    
    init {
        if (originalRows.isEmpty()) {
            throw IllegalArgumentException("rows must have any row.")
        }
        if (originalRows[0].isEmpty()) {
            throw IllegalArgumentException("row must have any elements.")
        }

        this.rowSize = originalRows.size
        this.colSize = originalRows[0].size
        
        val tmpArray = if (copyArray) {
            Array(this.rowSize, {DoubleArray(this.colSize)})
        } else {
            emptyArray()
        }
        
        originalRows.forEachIndexed { i, row ->
            if (row.size != this.colSize) {
                throw IllegalArgumentException("Expected length is ${this.colSize}. However row[$i].length is ${row.size}.")
            }
            if (copyArray) {
                tmpArray[i] = Arrays.copyOf(originalRows[i], row.size)
            }
        }

        this.rows = if (copyArray) {
            tmpArray
        } else {
            originalRows
        }
    }

    // matrix always has any elements.
    fun max(): Double = this.rows.flatMap { it.toList() }.max()!!
    
    fun sum(): Double = this.rows.sumByDouble(DoubleArray::sum)
    
    fun exp(): Matrix = this.map { Math.exp(it) }
    
    fun log(): Matrix = this.map { Math.log(it) }
    
    fun flatten(): Matrix {
        val rows = Array(1, { DoubleArray(this.rowSize * this.colSize) })

        this.rows.forEachIndexed { rowIndex, row ->
            val baseColIndex = rowIndex * this.rowSize
            row.forEachIndexed { colIndex, value ->
                rows[0][baseColIndex + colIndex] = value
            }
        }

        return Matrix(NO_COPY, *rows)
    }

    operator fun unaryMinus(): Matrix = this.map { -it }

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
    operator fun times(other: Matrix) = this.mapWith(other, { thisValue, otherValue -> thisValue * otherValue})
    operator fun div(other: Matrix) = this.mapWith(other, { thisValue, otherValue -> thisValue / otherValue})
    
    
    
    fun dot(other: Matrix): Matrix {
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
        
        return Matrix(NO_COPY, *rows)
    }
    
    internal fun map(mapper: (Double) -> Double)
            = this.mapIndexed { _, _, value ->
                mapper(value)
            }

    private fun mapWith(other: Matrix, mapper: (Double, Double) -> Double)
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

        return Matrix(NO_COPY, *rows)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Matrix) return false
        if (this.rowSize != other.rowSize) return false
        
        var i = 0
        return this.rows.all { row ->
            Arrays.equals(row, other.rows[i++])
        }
    }

    override fun hashCode(): Int = Arrays.deepHashCode(this.rows)

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
