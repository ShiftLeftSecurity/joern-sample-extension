package io.shiftleft

import io.shiftleft.codepropertygraph.Cpg
import io.shiftleft.semanticcpg.layers.{
  LayerCreator,
  LayerCreatorContext,
  LayerCreatorOptions
}

object MyExtension {
  val overlayName = "My extension"
  val description = "My description"
}

class MyExtension extends LayerCreator {
  override val overlayName: String = MyExtension.overlayName
  override val description: String = MyExtension.description

  override def create(context: LayerCreatorContext,
                      options: Option[LayerCreatorOptions],
                      serializeInverse: Boolean): Unit = {
    println("Hello Joern")
  }

  override def probe(cpg: Cpg): Boolean = false
}
