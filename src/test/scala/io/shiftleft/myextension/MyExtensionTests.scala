package io.shiftleft.myextension

import io.shiftleft.MyExtension
import io.shiftleft.semanticcpg.layers.{
  LayerCreatorContext,
  LayerCreatorOptions
}
import io.shiftleft.semanticcpg.testfixtures.CodeToCpgFixture
import org.scalatest.{Matchers, WordSpec}

class MyExtensionTests extends WordSpec with Matchers {

  private val code =
    """
      |int main(int argc, char **argv) {
      |
      |}
      |""".stripMargin

  CodeToCpgFixture(code) { cpg =>
    val context = new LayerCreatorContext(cpg)
    val options = new LayerCreatorOptions()
    new MyExtension().create(context, Some(options))

    "should do something useful" in {}

  }

}
