package kondol.network

import kondol.matrix.Matrix

class NeuralNetwork (
    private vararg val layers: Layer
) {
    constructor(layerList: List<Layer>): this(*layerList.toTypedArray())
    
    fun forward(x: Matrix) = this.layers.fold(x) {accumulation, layer -> layer.forward(accumulation)}
}

