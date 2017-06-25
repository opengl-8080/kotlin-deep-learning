package kondol.function.loss

import kondol.matrix.Matrix


interface LossFunction {
    
    operator fun invoke(y: Matrix, t: Matrix): Double
}