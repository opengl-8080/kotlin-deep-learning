package kondol.matrix.operators

import kondol.matrix.Matrix

operator fun Double.plus(matrix: Matrix) = matrix.map { this + it }
operator fun Double.minus(matrix: Matrix) = matrix.map { this - it }
operator fun Double.times(matrix: Matrix) = matrix.map { this * it }
operator fun Double.div(matrix: Matrix) = matrix.map { this / it }

operator fun Float.plus(matrix: Matrix) = matrix.map { this + it }
operator fun Float.minus(matrix: Matrix) = matrix.map { this - it }
operator fun Float.times(matrix: Matrix) = matrix.map { this * it }
operator fun Float.div(matrix: Matrix) = matrix.map { this / it }

operator fun Long.plus(matrix: Matrix) = matrix.map { this + it }
operator fun Long.minus(matrix: Matrix) = matrix.map { this - it }
operator fun Long.times(matrix: Matrix) = matrix.map { this * it }
operator fun Long.div(matrix: Matrix) = matrix.map { this / it }

operator fun Int.plus(matrix: Matrix) = matrix.map { this + it }
operator fun Int.minus(matrix: Matrix) = matrix.map { this - it }
operator fun Int.times(matrix: Matrix) = matrix.map { this * it }
operator fun Int.div(matrix: Matrix) = matrix.map { this / it }
