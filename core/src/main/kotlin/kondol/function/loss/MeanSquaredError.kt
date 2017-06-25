package kondol.function.loss

import kondol.matrix.Matrix


class MeanSquaredError: LossFunction {
    
    override fun invoke(y: Matrix, t: Matrix): Double 
        = 0.5 * (y - t).pow(2.0).sum()
}