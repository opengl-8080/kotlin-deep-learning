package kondol.function.loss

import kondol.matrix.Matrix


class CrossEntropyError: LossFunction {
    
    override fun invoke(y: Matrix, t: Matrix): Double
        = -(t * (y + 1e-7).log()).sum()
}
